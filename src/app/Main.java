package app;

import app.interaction.DramaticEffect;
import app.interaction.Narrator;
import app.interaction.UserFeedback;
import app.level.Level;
import app.level.LevelService;

public class Main {

    public static void main(String[] args) {
        final Player player = new Player();
        final Level startLevel = LevelService.getInstance().getStartLevel();
        final Game game = new Game(player, startLevel);

        Narrator.tell(UserFeedback.of(
                "_   _____________ ______",
                "| | / /  _  | ___ \\___ \\/",
                "| |/ /| | | | |_/ /  / / ",
                "|    \\| | | |    /  / /",
                "| |\\  \\ \\_/ / |\\ \\./ /___",
                "\\_| \\_/\\___/\\_| \\_\\_____/"));
        Narrator.pause(3000);
        Narrator.newLine(3);
        boolean nameConfirmed = false;
        Narrator.tell(UserFeedback.of(genie));
        String playerName = Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Hello there I am Lars. Who be thou?"));
        // Gartner that has a magic quadrent


        while (!nameConfirmed) {
            String confirmation = Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Would you like me to call you "+ playerName + "?"));
            nameConfirmed = confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yupp");
            if (!nameConfirmed) {
                playerName = Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Well then what is thou name?"));
            }
        }
        Narrator.tell(UserFeedback.of(DramaticEffect.WORD, "Are you read " + playerName + "?",
                "You can always call for help by yelling \"help\".",
                "If you wish to end everything you just say the word quit and I wil put you out of your misery.",
                "From ash you have risen to complete this final quest.,",
                "The meaning of it you will seek in the place I will put you.",
                "Good look explorer",
                "Good luck and good bye"));
        Narrator.pause(3000);
        Narrator.newLine(5);
        Narrator.tell(UserFeedback.of("You awake on the floor. Everything is dark. Your head hurts.",
                "You have no memory of what happened. The last thing you remember was the Genie. Lars? Was that his name?",
                "That is in fact the only thing you remember."));
        Narrator.pause(2000);
        Narrator.tell(UserFeedback.of("Your vision is reappearing. It is still dark but you are abel to "));
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
