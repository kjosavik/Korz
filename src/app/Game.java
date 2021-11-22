package app;

import app.interaction.Command;
import app.interaction.Narrator;
import app.level.Level;

public class Game {
    private final Player player;
    private Level currentLevel;

    public Game(Player player, Level currentLevel) {
        this.player = player;
        this.currentLevel = currentLevel;
    }

    public void start() {
        Command command = Narrator.askForCommand(currentLevel.levelDescription());
        while (!command.quit()) {
            String feedback = currentLevel.actOnCommand(command, this);
            command = Narrator.askForCommand(feedback);
        }
    }



    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
