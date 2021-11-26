package app;

import app.level.Level;

public class Game {
    private final Player player;
    private Level currentLevel;

    public Game(Player player, Level currentLevel) {
        this.player = player;
        this.currentLevel = currentLevel;
    }

    public void start() {
        currentLevel.start(this);
    }

    public void changeLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
        start();
    }

    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
