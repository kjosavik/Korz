package app.movableitems;

import app.interaction.*;
import app.level.LevelService;
import app.level.implementations.Level;
import app.level.implementations.OfficeNorth;
import app.level.implementations.Terminal;

public class Computer extends Item {
    private final LevelService levelService = LevelService.getInstance();
    private String passwordEnteredByUser;
    private boolean computerOpen = false;

    public Computer() {
        super(OfficeNorth.class.getSimpleName(), Noun.COMPUTER, "laptop");
        legalActions.put(Command.of(Verb.EXAMINE, itemNoun), game -> UserFeedback.of("This is a nice looking computer. It has a sticker on it with the acronym \"TDC\" on it."));
        legalActions.put(Command.of(Verb.USE, itemNoun), game -> {
            if (computerOpen) {
                return UserFeedback.of("The computer is unlocked. You can access the terminal with the command \"Use the terminal\"");
            }
            String password = Narrator.askForString(UserFeedback.of("Your fat finger grazes the touchpad. The screen brightens.",
                    "To use this you must enter the password.",
                    "What do you enter?"));
            if (password.equals(passwordEnteredByUser) || passwordEnteredByUser == null) {
                passwordEnteredByUser = password;
                computerOpen = true;
                return UserFeedback.of("CORRECT. The machine unlocks and you are faced with a terminal window.",
                        "You can now access the terminal.");
            }
            return UserFeedback.of("That password is incorrect");
        });
        legalActions.put(Command.of(Verb.LOCK, itemNoun), game -> {
            computerOpen = false;
            return UserFeedback.of("You lock the computer. The screen dims");
        });

        legalActions.put(Command.of(Verb.USE, Noun.TERMINAL), game -> {
            if (computerOpen) {
                game.changeLevel(levelService.getLevel(Terminal.class.getSimpleName()));
            }
            computerOpen = false;
            return UserFeedback.of("The computer is locked");
        });
    }

    private void terminal() {

    }
}
