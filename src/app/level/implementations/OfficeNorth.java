package app.level.implementations;

import app.Game;
import app.interaction.*;

public class OfficeNorth extends Level {

    public OfficeNorth() {
        super(null, Hallway.class.getSimpleName(), OfficeSouth.class.getSimpleName(), null);
        legalActions.put(Command.of(Verb.KILL, Noun.MAN), game -> {
            if (game.isItemOnPlayer(Noun.KNIFE)) {
                game.setNpcAlive(false);
                Narrator.tell(UserFeedback.of("You stick the man. He falls to the floor and gasps in a great pile of blood. You watch as life diminishes from his eyes."));
                return UserFeedback.of(DramaticEffect.WORD, "He is dead.");
            } else {
              return UserFeedback.of("You have no weapons. It would be unwise to attack this man. He is much larger than you.");
            }
        });
        legalActions.put(Command.of(Verb.EXAMINE, Noun.MAN), game -> UserFeedback.of("The man is sitting with his back to you."));
        legalActions.put(Command.of(Verb.TALK, Noun.MAN), game -> UserFeedback.of("You greet the man with a \"Hello + "+ game.getPlayer().getName() +"\". He removes his headphones and opens his mouth.",
                "\"You forgot to push your last commit. Also is the coffee ready? You were back there quite some time.\""));
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        Narrator.tell(UserFeedback.of(DramaticEffect.LETTER, "There is a person sitting at a desk!"));
        return UserFeedback.of("The young man doesn't seem to notice you. He is sitting with his back to you.",
                "You move closer to him. The floor beneath you creeks, \"creek\". He doesn't move a bit.",
                "As you move closer you notice his ears are covered. He can't hear you.",
                "There is a laptop on one of the desks. It's screen is on.",
                "The large room you are in continues to the south.",
                "To the east is a step up into the hallway from where you came.",
                cameFrom(game));
    }
}
