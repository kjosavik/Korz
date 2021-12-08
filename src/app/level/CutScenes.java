package app.level;

import app.Player;
import app.interaction.DramaticEffect;
import app.interaction.Narrator;
import app.interaction.UserFeedback;

public class CutScenes {
    public static void intro(Player player) {
        Narrator.pause(3000);
        Narrator.newLine(3);
        boolean nameConfirmed = false;
        Narrator.tell(UserFeedback.of(genie));
        player.setName(Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "Hello there I am Linus. Who be thou?")));


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
        Narrator.askForEnter(UserFeedback.of(DramaticEffect.LETTER,"After we part ways you will need to do the heavy lifting. You are in control. I have one final tip to add; Always examine your surroundings."));
        Narrator.askForEnter(UserFeedback.of(DramaticEffect.LETTER,
                "From ash you have risen to complete this final quest...",
                "Stay committed and push everything that comes in your way.",
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
        Narrator.pause(2000);
    }

    public static void gameEnding(Player player) {
        Narrator.pause(2000);
        Narrator.newLine(10);
        Narrator.tell(UserFeedback.of(DramaticEffect.LETTER,"CONGRATULATIONS"));
        Narrator.pause(2000);
        Narrator.tell(UserFeedback.of(genie));
        Narrator.tell(UserFeedback.of(DramaticEffect.WORD, "You did well "+ player.getName()));
        Narrator.askForEnter(UserFeedback.of( "I see you are fluent in git.",
                "You have now become a Software Developer.",
                "Actually you didn't even use Stack Overflow. You are a Senior Software Developer",
                "I know of a cool company you should get in touch with.",
                "Have a look: https://kodeworks.no"));
        Narrator.askForEnter(UserFeedback.of("I have to go now. A retard just sent something retarded to the Linux Kernel Mailing List."));
        Narrator.pause(1000);
        Narrator.tell(UserFeedback.of(DramaticEffect.LETTER, "OMG"));
        Narrator.newLine(3);
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
