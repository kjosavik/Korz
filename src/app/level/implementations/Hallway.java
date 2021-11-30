package app.level.implementations;

import app.Game;
import app.interaction.DramaticEffect;
import app.interaction.Narrator;
import app.interaction.UserFeedback;

public class Hallway extends Level {
    private boolean firstVisit = true;

    public Hallway() {
        super(Kitchen.class.getSimpleName(), Bathroom.class.getSimpleName(), Bar.class.getSimpleName(), null);
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        if (firstVisit) {
            Narrator.tell(UserFeedback.of(DramaticEffect.WORD,
                    "FREEDOM! Your anxiety subsides and you catch your breath."));
            Narrator.tell(UserFeedback.of(
                    "Where do I go now? What was it that Lars wanted med to do?",
                    "I must find out what he wanted"
                    ));
            firstVisit = false;
        }
        return UserFeedback.of(
                "You are in a hallway. It has wooden floors and white walls. Kind of dull. There isn't much here except the exits.",
                "To the north is a wooden table and a couple of chairs. Beyond that there is another larger room.",
                "To the east is the bathroom.",
                "There is a small step down that leads to a larger room to the south and to the east.",
                cameFrom(game));
    }
}
