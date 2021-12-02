package app.level.implementations;

import app.Game;
import app.interaction.*;

public class Death extends Level{

    @Override
    public UserFeedback levelDescription(Game game) {
        Narrator.tell( UserFeedback.of(DramaticEffect.LETTER, "You weave into oncoming traffic and get hit by a car."));
        throw new NullPointerException("Cannot invoke life because oxygen flow was null");
    }
}
