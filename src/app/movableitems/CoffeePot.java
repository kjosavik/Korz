package app.movableitems;

import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;
import app.interaction.Verb;
import app.level.implementations.Kitchen;
import app.level.implementations.OfficeNorth;

public class CoffeePot extends Item {
    public CoffeePot() {
        super(Kitchen.class.getSimpleName(), Noun.COFFEE_POT, "coffee pot");
        legalActions.put(Command.of(Verb.EXAMINE, itemNoun), game -> UserFeedback.of("The coffee pot is full of hot coffee. It smells wonderful."));
        legalActions.put(Command.of(Verb.USE, itemNoun), game -> UserFeedback.of("You drink coffee straight from the jug you filthy animal."));
        legalActions.put(Command.of(Verb.GIVE, itemNoun), game -> {
            if (game.getCurrentLevel().getClass().equals(OfficeNorth.class)) {
                if(game.isNpcAlive()) {
                    return UserFeedback.of("You give the man some coffee. \"Thanks dude!\"");
                }
                return UserFeedback.of("You through coffee at his corpse.");
            } else {
                return UserFeedback.of("There is no one here to give coffee to.");
            }
        });
    }
}
