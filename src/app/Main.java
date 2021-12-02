package app;

import app.interaction.DramaticEffect;
import app.interaction.Narrator;
import app.interaction.UserFeedback;
import app.level.Intro;
import app.level.implementations.Level;
import app.level.LevelService;
import app.movableitems.ItemService;

public class Main {

    public static void main(String[] args) {
        final LevelService levelService = LevelService.getInstance();
        final ItemService itemService = ItemService.getInstance();
        final Player player = new Player();
        final Intro intro = new Intro();

        levelService.loadLevels();
        itemService.loadItems();

        final Level startLevel = levelService.getStartLevel();
        final Game game = new Game(player, startLevel);

        Narrator.tell(UserFeedback.of(
                "_   _____________ ______",
                "| | / /  _  | ___ \\___ \\/",
                "| |/ /| | | | |_/ /  / / ",
                "|    \\| | | |    /  / /",
                "| |\\  \\ \\_/ / |\\ \\./ /___",
                "\\_| \\_/\\___/\\_| \\_\\_____/"));
        Narrator.tell(UserFeedback.of(DramaticEffect.WORD, "A game by Erik Kjosaik"));

        String answer = Narrator.askForString(UserFeedback.of("Skip intro? [y/n]"));
        if (!answer.equals("y")) {
            intro.play(player);
        }

        game.start();
    }



}
