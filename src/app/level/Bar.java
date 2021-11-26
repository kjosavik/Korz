package app.level;

import app.interaction.UserFeedback;

public class Bar extends Level {
    public Bar() {
        super(null, null, Pinball.class.getSimpleName(), null);
    }

    @Override
    public UserFeedback levelDescription() {
        return null;
    }
}
