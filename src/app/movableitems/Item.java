package app.movableitems;

import app.Game;
import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;
import app.interaction.Verb;

import java.util.HashMap;
import java.util.function.Function;

public class Item {
    protected final HashMap<Command, Function<Game, UserFeedback>> legalActions = new HashMap<>();
    private final String name;
    private String position;
    private boolean hasBeenMoved = false;
    protected Noun itemNoun;

    public Item(String startPosition, Noun itemNoun, String name) {
        position = startPosition;
        this.itemNoun = itemNoun;
        this.name = name;
        this.legalActions.put(Command.of(Verb.PICK_UP, itemNoun), game -> this.pickUp());
    }

    public Function<Game, UserFeedback> getLegalAction(Command command, String currentLevel) {
        if (currentLevel.equals(position) || position.equals("player")) {
            return legalActions.get(command);
        }
        return null;
    }

    public UserFeedback pickUp() {
        hasBeenMoved = true;
        position = "player";
        legalActions.put(Command.of(Verb.DROP, itemNoun), game -> this.drop(game.getCurrentLevel().getClass().getSimpleName()));
        legalActions.remove(Command.of(Verb.PICK_UP, itemNoun));
        return UserFeedback.of("You pick up " + name + ". You can check your inventory by typing \"inventory\"");
    }

    public UserFeedback drop(String position) {
        this.position = position;
        legalActions.put(Command.of(Verb.PICK_UP, itemNoun), game -> this.pickUp());
        legalActions.remove(Command.of(Verb.DROP, itemNoun));
        return UserFeedback.of("You drop " + name + ". ");
    }

    public String getName() {
        return name;
    }

    public boolean hasBeenMoved() {
        return hasBeenMoved;
    }

    public String getPosition() {
        return position;
    }

    public Noun getItemNoun() {
        return itemNoun;
    }
}
