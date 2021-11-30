package app.level.implementations;

import app.Game;
import app.interaction.*;
import app.level.Direction;
import app.level.LevelService;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class Level {
    private final EnumMap<Direction, String> borderingLevels = new EnumMap<>(Direction.class);
    protected final HashMap<Command, Function<Game, UserFeedback>> legalActions = new HashMap<>();
    protected final LevelService levelService = LevelService.getInstance();

    protected UserFeedback undefinedDirection = UserFeedback.of("You can't go there");
    protected UserFeedback nothingInThatDirection = UserFeedback.of("There is nothing in that direction");
    protected UserFeedback canTDoThat = UserFeedback.of("I can't do that");

    protected Level () {
        addDefaultHelp();
    }

    protected Level (String levelUp, String levelRight, String levelDown, String levelLeft) {
        this.borderingLevels.put(Direction.UP, levelUp);
        this.borderingLevels.put(Direction.RIGHT, levelRight);
        this.borderingLevels.put(Direction.DOWN, levelDown);
        this.borderingLevels.put(Direction.LEFT, levelLeft);
        addDefaultHelp();
    }

    public final void start(Game game) {
        Command command = Narrator.askForCommand(levelDescription(game));
        while (!command.quit()) {
            UserFeedback feedback = actOnCommand(command, game);
            command = Narrator.askForCommand(feedback);
        }
        if (command.quit()) {
            Narrator.tell(UserFeedback.of(DramaticEffect.LETTER,"You are now dead. Goodbye"));
            throw new NullPointerException("Cannot invoke life because brain was null.");
        }
    }

    public abstract UserFeedback levelDescription(Game game);

    protected UserFeedback actOnCommand(Command command, Game game) {
        if (command.verb().equals(Verb.GO)) {
            Direction direction = getDirection(command.noun());
            if (direction == null) {
                return undefinedDirection;
            }
            String directionString = getBorderingLevel(direction);
            if (directionString == null) {
                return nothingInThatDirection;
            }
            Level nextLevel = levelService.getLevel(directionString);
            game.changeLevel(nextLevel);
        }
        Function<Game, UserFeedback> legalAction = legalActions.containsKey(command) ? legalActions.get(command) : game.getItemAction(command);
        if (legalAction == null) {
            return canTDoThat;
        }
        return legalAction.apply(game);
    }

    protected String cameFrom(Game game) {
        Level previousLevel = game.getPreviousLevel();
        Optional<Direction> directionOfPreviousLevel = borderingLevels.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> entry.getValue().equals(previousLevel.getClass().getSimpleName()))
                .map(Map.Entry::getKey)
                .findFirst();
        return directionOfPreviousLevel.map(direction -> "You came from the " + direction.getCompassDirection() + ". ").orElse("");
    }

    private Direction getDirection(Noun noun) {
        try {
            return Direction.valueOf(noun.name());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private String getBorderingLevel(Direction direction) {
        return borderingLevels.get(direction);
    }

    protected void setBorderingLevel(Direction direction, String levelName) {
        borderingLevels.put(direction, levelName);
    }

    private void addDefaultHelp() {
        legalActions.put(Command.of(Verb.HELP, Noun.NONE), (game) -> UserFeedback.of("Remember what Lars said. Examine your surroundings.",
                "Try the command \"examine room\" or for example \"Look at the knife\"",
                "For simplicities sake you are always facing north. Let's say you are drawn to Santa or something"));
    }
}
