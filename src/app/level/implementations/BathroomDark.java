package app.level.implementations;

import app.Game;
import app.interaction.*;

public class BathroomDark extends Level {
    public BathroomDark() {
        undefinedDirection = UserFeedback.of("You are unable to navigate in this darkness.");
        nothingInThatDirection = UserFeedback.of("You can't go anywhere.", "You could try examining the room.");
        UserFeedback examine = UserFeedback.of(
                "Although you cannot see anything you rub your fingers along the wall.",
                "The room is small and rectangular. You are not able to extend your arms out to your sides fully when facing something that feels like a door.",
                "There is something to the right of the door mounted to the wall. A wire extends from it and disappears into the wall. This might be a button"
        );
        legalActions.put(Command.of(Verb.EXAMINE, Noun.LIGHT), game -> examine);
        legalActions.put(Command.of(Verb.EXAMINE, Noun.ROOM), game -> examine);
        legalActions.put(Command.of(Verb.USE, Noun.LIGHT), game -> examine);
        legalActions.put(Command.of(Verb.USE, Noun.DOOR), game -> UserFeedback.of(
                "You pull the handle.",
                "Nothing happens.",
                "It won't budge."));
        legalActions.put(Command.of(Verb.USE, Noun.BUTTON), game -> {
                    Narrator.tell(UserFeedback.of("Light! The room begins to take form as your eyes settle.",
                            "The pain caused by the sudden brightness subsides.",
                            "You are able to see the room"));
                    game.changeLevel(levelService.getLevel(Bathroom.class.getSimpleName()));
                    return null;
                }
        );
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        Narrator.tell(UserFeedback.of("You awake on the floor. Everything is dark. Your head hurts.",
                "You have no memory of what happened. The last thing you remember was the Genie. Linus? Was that his name?",
                "That is in fact the only thing you remember."));
        Narrator.pause(10000);
        return UserFeedback.of("Your vision is reappearing. You can see a sliver of light at floor level.",
                "It seems there is a small draft coming from the crack where the light is shining through.",
                "One thing is certain, you must escape",
                " ",
                "You are now in control. Try typing a command.");
    }
}
