package app.level.implementations;

import app.Game;
import app.interaction.*;

public class Pinball extends Level {

    public Pinball() {
        super(Bar.class.getSimpleName(), null/*OuterHallway.class*/, null, null);
        legalActions.put( Command.of(Verb.EXAMINE, Noun.KNIFE) , game -> UserFeedback.of("The knife appears to be rusty"));
    }

    @Override
     public UserFeedback levelDescription(Game game) {
        return UserFeedback.of(
                "There is armor and garments hanging on the wall.",
                "A strange object consisting of two circles with a rubber shell held together with metal tubes rest against the wall in between some oddly shaped tables.",
                "The tables are slanted and flash in different colors.",
                "You can hear the table repeat the words \"The path of the dead\"");
    }
}
