package app.level.implementations;

import app.Game;
import app.interaction.*;
import app.level.Direction;

public class Bathroom extends Level {
    private boolean doorOpen = false;
    final UserFeedback openDoorFeedback = nothingInThatDirection;
    final UserFeedback closedDoorFeedback = UserFeedback.of("There is no where to go in this room except a closed door to the west.");
    public Bathroom() {
        nothingInThatDirection = closedDoorFeedback;
        UserFeedback faucet = UserFeedback.of("You wash your hands, but for no good. You are still filthy.");
        UserFeedback toilet = UserFeedback.of("You feel no need to empty your bowels. But you flush the toilet because you feel like it. Swoooosh. What a waste of water.");
        legalActions.put(Command.of(Verb.USE, Noun.DOOR), game -> {
            if (doorOpen) {
                nothingInThatDirection = closedDoorFeedback;
                doorOpen = false;
                setBorderingLevel(Direction.LEFT, null);
                return UserFeedback.of("You close the door.");
            }
            nothingInThatDirection = openDoorFeedback;
            setBorderingLevel(Direction.LEFT, Hallway.class.getSimpleName());
            doorOpen = true;
            Narrator.tell(UserFeedback.of("You try to open the door..."));
            Narrator.pause(1500);
            return UserFeedback.of("It's locked.", "But wait! Duh..",
                    "You notice a lock on the door. You feel stupid for not noticing it earlier.",
                    "You open the door. You are now able to go west if you wish to leave the room.");
        });
        legalActions.put(Command.of(Verb.EXAMINE, Noun.MIRROR), game -> UserFeedback.of(
                "You take a hard long look in the mirror.",
                "You look terrible. It is apparent that last night was tough.",
                "Maybe you shouldn't have had that one last shot of Moonshine."));
        legalActions.put(Command.of(Verb.USE, Noun.TOILET), game -> toilet);
        legalActions.put(Command.of(Verb.GO, Noun.TOILET), game -> toilet);
        legalActions.put(Command.of(Verb.PICK_UP, Noun.TOILET), game -> toilet);
        legalActions.put(Command.of(Verb.USE, Noun.FAUCET), game -> faucet);
        legalActions.put(Command.of(Verb.GO, Noun.FAUCET), game -> faucet);
        legalActions.put(Command.of(Verb.WASH, Noun.FAUCET), game -> faucet);
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        return UserFeedback.of("You seem to be standing in a bathroom. There is a single toilet here and a small faucet. Above the faucet there is a small mirror.",
                "To the west of the room there is a door.");
    }
}
