package app;

import app.interaction.Command;
import app.interaction.UserFeedback;
import app.level.implementations.Level;
import app.movableItems.CoffeePot;
import app.movableItems.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Game {
    private final Player player;
    private Level currentLevel;
    private Level previousLevel;
    private List<Item> gameItems;

    public Game(Player player, Level currentLevel) {
        this.player = player;
        this.currentLevel = currentLevel;
        initItems();
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

    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    private void initItems() {
        gameItems = new ArrayList<>();
        gameItems.add(new CoffeePot());
    }

    public Function<Game, UserFeedback> getItemAction(Command command) {
        return gameItems.stream().map(item -> item.getLegalAction(command, getCurrentLevel().getClass().getSimpleName())).toList().get(0);
    }
}
