package app.level.implementations;

import app.Game;
import app.interaction.UserFeedback;

public class Bar extends Level {
    public Bar() {
        super(null, null, Pinball.class.getSimpleName(), null);
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        return null;
    }
}
