package app.movableItems;

import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;
import app.interaction.Verb;
import app.level.implementations.Kitchen;

public class CoffeePot extends Item {
    public CoffeePot() {
        super(Kitchen.class.getSimpleName(), Noun.COFFEE_POT, "coffee pot");
        legalActions.put(Command.of(Verb.EXAMINE, itemNoun), game -> UserFeedback.of("The coffee pot is full of hot coffee. It smells wonderful."));
    }
}
