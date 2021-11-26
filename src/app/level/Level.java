package app.level;

import app.Game;
import app.interaction.Command;
import app.interaction.Narrator;
import app.interaction.UserFeedback;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Level {
    private final Map<Direction, String> borderingLevels;
    protected Map<Command, Function<Game, UserFeedback>> legalActions;

    protected Level (String levelUp, String levelRight, String levelDown, String levelLeft) {
        this.borderingLevels = new EnumMap<>(Direction.class);
        this.borderingLevels.put(Direction.UP, levelUp);
        this.borderingLevels.put(Direction.RIGHT, levelRight);
        this.borderingLevels.put(Direction.DOWN, levelDown);
        this.borderingLevels.put(Direction.LEFT, levelLeft);
    }

    public void start(Game game) {
        Command command = Narrator.askForCommand(levelDescription());
        while (!command.quit()) {
            UserFeedback feedback = actOnCommand(command, game);
            command = Narrator.askForCommand(feedback);
        }
    }

    public abstract UserFeedback levelDescription();

    private UserFeedback actOnCommand(Command command, Game game) {
        return legalActions.get(command).apply(game);
    }

    private String getBorderingLevel(Direction direction) {
        return borderingLevels.get(direction);
    }
}
