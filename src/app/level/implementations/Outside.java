package app.level.implementations;

import app.Game;
import app.interaction.*;
import app.level.Direction;

public class Outside extends Level {
    private boolean doorOpen = false;

    public Outside() {
        super(null, Death.class.getSimpleName(), Death.class.getSimpleName(), Death.class.getSimpleName());
        legalActions.put(Command.of(Verb.USE, Noun.DOOR), game -> {
            if (doorOpen) {
                return UserFeedback.of("Door is jammed open. It won't shut");
            }
            Narrator.tell(UserFeedback.of("There is a lock on the door with a number pad." +
                    "The number pad includes all numbers 0-9 and the symbols # and *."));
            String code = Narrator.askForString(UserFeedback.of(DramaticEffect.LETTER, "What is the code you enter?"));
            if (isCorrectCode(code)) {
                doorOpen = true;
                setBorderingLevel(Direction.UP, Pinball.class.getSimpleName());
                return UserFeedback.of("The door opens. You can now go north.");
            }
            return UserFeedback.of("Beep beep. Wrong combination");
        });
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        return UserFeedback.of(
                "The door slams shut behind you",
                "You smell the fresh air.",
                "Although it is tempting something tells you it is not time to leave yet.",
                "To the north is the door from which you came.",
                "To the east, south and west there are streets. This building is apparently in a city.",
                "It is winter time and everyone seems rushed. It must be cold because everyone has covered their faces.",
                "You ponder at that thought as you do not feel cold. You are only wearing a button down shirt and jeans.");
    }

    private boolean isCorrectCode(String code) {
        return code.hashCode() == -377373471;
    }
}
