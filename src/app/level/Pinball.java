package app.level;

import app.Game;
import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.Verb;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Pinball extends Level {

    public Pinball() {
        super(Bar.class.getSimpleName(), null/*OuterHallway.class*/, null, null);
        this.legalActions = legalAction();

    }

    private Map<Command, Function<Game, String>> legalAction() {
        Map<Command, Function<Game, String>> legalActions = new HashMap<>();
        legalActions.put(new Command(Verb.LOOK, Noun.KNIFE), game -> "The knife appears to be rusty");
        return legalActions;
    }

    @Override
     public String levelDescription() {
        return "There is armor and garments hanging on the wall." +
                "A strange object consisting of two circles with a rubber shell held together with metal tubes rest against the wall in between some oddly shaped tables." +
                "The tables are slanted and flash in different colors. You can here the table repeat the words \"The path of the dead\"";
    }
}
