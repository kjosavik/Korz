package app.movableitems;

import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;
import app.interaction.Verb;
import app.level.implementations.Kitchen;

public class Knife extends Item {
    public Knife() {
        super(Kitchen.class.getSimpleName(), Noun.KNIFE, "knife");
        legalActions.put(Command.of(Verb.EXAMINE, itemNoun), game -> UserFeedback.of("The knife is old and rusty with a long blade. You could really hurt someone with this."));
    }
}
