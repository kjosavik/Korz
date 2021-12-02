package app.level.implementations;

import app.Game;
import app.interaction.*;

public class Pinball extends Level {
    private boolean doorOpen = false;

    public Pinball() {
        super(Bar.class.getSimpleName(), Outside.class.getSimpleName(), null, null);
        legalActions.put(Command.of(Verb.USE, Noun.PINBALL)  , game -> UserFeedback.of("You press the start button and play some pinball. You suck!",
                "There is no way you can beat \"V.S.\". He has all the high scores. What a geek."));
        legalActions.put(Command.of(Verb.EXAMINE, Noun.PINBALL)  , game -> UserFeedback.of("One of the machines are on. The start button blinks green",
                "All of the high scores are by one person."));
        legalActions.put(Command.of(Verb.USE, Noun.DOOR), game -> {
            doorOpen = !doorOpen;
            return UserFeedback.of("You " + (doorOpen ? "open" : "close") + " the door");
        });
        legalActions.put(Command.of(Verb.EXAMINE, Noun.CLOTHES), game -> UserFeedback.of("You find nothing in the pockets."));
        legalActions.put(Command.of(Verb.PICK_UP, Noun.CLOTHES), game -> UserFeedback.of("Your kleptomaniac fingers grab the coats and shoes.",
                "You become encumbered and carefully hang them back up."));
    }

    @Override
     public UserFeedback levelDescription(Game game) {
        return UserFeedback.of(
                "There are three pinball tables in this room. It is a strange room that looks like a combined game room and entrance.",
                "The reason you think this might be an entrance is because of the heavy door to the east and clothes hanging on the wall.",
                "There are two pairs of shoes and two coats hanging on the wall. One of them looks very familiar.",
                "You can hear one of the pinball tables repeat the words \"The path of the dead\" over and over again.",
                cameFrom(game));
    }
}
