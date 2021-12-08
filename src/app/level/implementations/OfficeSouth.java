package app.level.implementations;

import app.Game;
import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;
import app.interaction.Verb;

public class OfficeSouth extends Level {
    private boolean drawnOnWhiteBoard = false;
    public OfficeSouth() {
        super(OfficeNorth.class.getSimpleName(), Bar.class.getSimpleName(), null, null);
        legalActions.put(Command.of(Verb.USE, Noun.WHITEBOARD), game -> {
            drawnOnWhiteBoard = true;
            return  UserFeedback.of("You draw a phallic shape on the board and chuckle");
        });
        legalActions.put(Command.of(Verb.EXAMINE, Noun.WHITEBOARD), game -> {
            if (drawnOnWhiteBoard) {
                return UserFeedback.of("There is a drawing of a penis on the whiteboard.");
            }
            return UserFeedback.of("The whiteboard is empty");
        });
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        return UserFeedback.of(
                "You enter a large open space there is nothing here but a whiteboard and some chairs.",
                "The larger space extends to the north. That looks like an office area.",
                "To east is a bar.",
                cameFrom(game));
    }
}
