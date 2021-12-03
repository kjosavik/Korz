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
        legalActions.put(Command.of(Verb.EXAMINE, Noun.MAN), game -> {
            if (game.isNpcAlive()) {
                return UserFeedback.of("The man is sitting with his back to you.");
            }
            return UserFeedback.of("The man is laying on the ground in his own blood.");
        });
        legalActions.put(Command.of(Verb.TALK, Noun.MAN), game -> {
            if (game.isNpcAlive()) {
                return UserFeedback.of("You greet the man with a \"Yo dude!\". He removes his headphones and opens his mouth, \"Hello "+ game.getPlayer().getName() +"\". ",
                        "\"You forgot to push your last commit. Also is the coffee ready? You were back there quite some time.\"",
                        "You are caught off guard. What do you answer?. \"Uhhh...\"",
                        "You close your mouth and can't figure out what to say.",
                        "You look away and the conversation ends awkwardly.",
                        "That was uncomfortable.");
            }
            return UserFeedback.of("You yell at the man, \"Yo dude!\".",
                    "He doesn't answer. He just lies there.");
            });
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        if (game.isNpcAlive()) {
            Narrator.tell(UserFeedback.of(DramaticEffect.LETTER, "There is a person sitting at a desk!"));
            return UserFeedback.of("The young man doesn't seem to notice you. He is sitting with his back to you.",
                    "You move closer to him. The floor beneath you creeks, \"creek\". He doesn't move at all.",
                    "As you move closer you notice his ears are covered. He can't hear you.",
                    "There is a laptop on one of the desks. You will be able to access it without the man noticing.",
                    "The large room you are in continues to the south.",
                    "To the east is a step up into the hallway from where you came.",
                    cameFrom(game));
        }
        return UserFeedback.of(
                "The man is still dead",
                "There is a laptop on one of the desks. You will be able to access it without the man noticing.",
                "The large room you are in continues to the south.",
                "To the east is a step up into the hallway from where you came.",
                cameFrom(game));
    }
}
