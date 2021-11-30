package app;

import app.interaction.DramaticEffect;
import app.interaction.Narrator;
import app.interaction.UserFeedback;
import app.level.implementations.Level;
import app.level.LevelService;

public class Main {

    public static void main(String[] args) {
        final LevelService levelService = LevelService.getInstance();
        final Player player = new Player();

        levelService.loadLevels();

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
       /* Narrator.pause(3000);
        Narrator.newLine(3);
        boolean nameConfirmed = false;
        Narrator.tell(UserFeedback.of(genie));
        player.setName(Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Hello there I am Lars. Who be thou?")));


        while (!nameConfirmed) {
            String confirmation = Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Would you like me to call you "+ player.getName() + "?"));
            nameConfirmed = confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yupp") || confirmation.equalsIgnoreCase("sure");
            if (!nameConfirmed) {
                player.setName(Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Well then what is thou name?")));
            }
        }
        Narrator.askForEnter(UserFeedback.of(DramaticEffect.LETTER, "Are you ready " + player.getName() + "?",
                "You can always call for help by yelling \"help\"."));
        Narrator.askForEnter(UserFeedback.of(DramaticEffect.LETTER, "If you wish to end everything you just say the word \"quit\" and I will put you out of your misery."));
        Narrator.askForEnter(UserFeedback.of(DramaticEffect.LETTER,"After we part ways you will need to do the heavy lifting. You are in control. I have one final tip; Always examine your surroundings."));
        Narrator.askForEnter(UserFeedback.of(DramaticEffect.LETTER,
                "From ash you have risen to complete this final quest...",
                "The meaning of it you will seek. In the place I will transfer you.",
                "Good look explorer",
                "Good luck and good bye"));
        Narrator.newLine(10);
        Narrator.tell(UserFeedback.of(
                        "   ____ _                 _                                   ____             _                        \n" +
                        "  / ___| |__   __ _ _ __ | |_ ___ _ __    ___  _ __   ___ _  |  _ \\  __ _ _ __| | ___ __   ___  ___ ___ \n" +
                        " | |   | '_ \\ / _` | '_ \\| __/ _ \\ '__|  / _ \\| '_ \\ / _ (_) | | | |/ _` | '__| |/ / '_ \\ / _ \\/ __/ __|\n" +
                        " | |___| | | | (_| | |_) | ||  __/ |    | (_) | | | |  __/_  | |_| | (_| | |  |   <| | | |  __/\\__ \\__ \\\n" +
                        "  \\____|_| |_|\\__,_| .__/ \\__\\___|_|     \\___/|_| |_|\\___(_) |____/ \\__,_|_|  |_|\\_\\_| |_|\\___||___/___/\n" +
                        "                   |_|                                                                                 "
                ));
        Narrator.newLine(5);
        Narrator.pause(2000);*/
        game.start();
    }


    static final String genie =
                    "                          .-=-.\n" +
                    "                         /  ! )\\\n" +
                    "                      __ \\__/__/\n" +
                    "                     / _<( ^.^ )\n" +
                    "                    / /   \\ c /O\n" +
                    "                    \\ \\_.-./=\\.-._     _\n" +
                    "                     `-._  `~`    `-,./_<\n" +
                    "                         `\\' \\'\\`'----'\n" +
                    "                       *   \\  . \\          *\n" +
                    "                            `-~~~\\   .\n" +
                    "                       .      `-._`-._   *\n" +
                    "                             *    `~~~-,      *\n" +
                    "                   ()                   * )\n" +
                    "                  <^^>             *     (   .\n" +
                    "                 .-\"\"-.                    )\n" +
                    "      .---.    .\"-....-\"-._     _...---''`,. '\n" +
                    "     ( (`\\ \\ .'            ``-''    _.-\"'`\n" +
                    "      \\ \\ \\ : :.                 .-'\n" +
                    "       `\\`.\\: `:.             _.'\n" +
                    "       (  .'`.`            _.'\n" +
                    "        ``    `-..______.-'\n" +
                    "                  ):.  (\n" +
                    "                .\"-....-\".\n" +
                    "              .':.        `.\n" +
                    "              \"-..______..-\"";
}
