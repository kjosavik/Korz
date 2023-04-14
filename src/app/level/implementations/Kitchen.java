package app.level.implementations;

import app.Game;
import app.interaction.*;
import app.movableitems.Knife;

public class Kitchen extends Level {
    private boolean firstVisit = true;

    public Kitchen() {
        super(null, null, Hallway.class.getSimpleName(), null);
        legalActions.put(Command.of(Verb.EXAMINE, Noun.CABINETS), game -> {
            itemService.addItem(new Knife());
            return UserFeedback.of(
                    "You rumble through the kitchen. There is nothing here. No food. "
                            + (game.isItemInSpawn(Noun.KNIFE) ? "You do however discover a knife in the top drawer." : "No more knives here.")
            );
        });
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        Narrator.tell(
        UserFeedback.of("You enter what might resemble a kitchen. Though there isn't much here. There are cabinets and a stove placed along the walls.",
                "It has seen better days. There is a coffee machine in the the corner of the room. You could sure go for some hot joe right now."));

        if (firstVisit) {
            firstVisit = false;
            Narrator.tell(UserFeedback.of(
                    "It might cheer you up, but is that what you should be doing? Maybe time would be better spent searching for the quest that Linus sent you on?"));
        }
        return UserFeedback.of("Maybe you'll find something in here?", cameFrom(game));
    }
}
