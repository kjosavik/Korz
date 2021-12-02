package app;

import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;
import app.level.implementations.Level;
import app.movableitems.ItemService;

import java.util.function.Function;

public class Game {
    private final Player player;
    private Level currentLevel;
    private Level previousLevel;
    private final ItemService itemService = ItemService.getInstance();
    private boolean npcAlive = true;

    public Game(Player player, Level currentLevel) {
        this.player = player;
        this.currentLevel = currentLevel;
    }

    public void start() {
        currentLevel.start(this);
    }

    public void changeLevel(Level nextLevel) {
        previousLevel = currentLevel;
        currentLevel = nextLevel;
        start();
    }

    public Level getPreviousLevel() {
        return previousLevel;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }


    public Function<Game, UserFeedback> getItemAction(Command command) {
        return itemService.getItemAction(command, currentLevel.getClass().getSimpleName());
    }

    public boolean isItemInSpawn(Noun item) {
        return itemService.isItemInSpawn(item);
    }

    public boolean isItemOnPlayer(Noun item) {
        return itemService.isItemOnPlayer(item);
    }

    public boolean isNpcAlive() {
        return npcAlive;
    }

    public void setNpcAlive(boolean npcAlive) {
        this.npcAlive = npcAlive;
    }

    public Player getPlayer() {
        return player;
    }
}
