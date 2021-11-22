package app.level;

import app.Game;
import app.interaction.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Level {
    private final Map<Direction, String> borderingLevels;
    protected Map<Command, Function<Game, String>> legalActions;

    protected Level (String levelUp, String levelRight, String levelDown, String levelLeft) {
        this.borderingLevels = new HashMap<>();
        this.borderingLevels.put(Direction.UP, levelUp);
        this.borderingLevels.put(Direction.RIGHT, levelRight);
        this.borderingLevels.put(Direction.DOWN, levelDown);
        this.borderingLevels.put(Direction.LEFT, levelLeft);
    }

    public String actOnCommand(Command command, Game game) {
        return legalActions.get(command).apply(game);
    }

    public abstract String levelDescription();

    public String getBorderingLevel(Direction direction) {
        return borderingLevels.get(direction);
    }
}
