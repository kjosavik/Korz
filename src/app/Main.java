package app;

import app.interaction.Narrator;
import app.level.Level;
import app.level.LevelService;

public class Main {

    public static void main(String[] args) {
        final Player player = new Player();
        final Level startLevel = LevelService.getInstance().getStartLevel();
        final Game game = new Game(player, startLevel);

        Narrator.tell("Welcome to KORZ.");
        boolean nameConfirmed = false;
        String playerName = Narrator.askForString("Hello explorer! Who be thou?");
        while (!nameConfirmed) {
            String confirmation = Narrator.askForString("Should I call you "+ playerName + "?");
            nameConfirmed = confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yupp");
            if (!nameConfirmed) {
                playerName = Narrator.askForString("Well then what is thou name?");
            }
        }
        Narrator.tell("That will be all! Goodbye!");
        game.start();
    }
}
