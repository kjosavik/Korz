package app.level.implementations;

import app.Game;
import app.interaction.*;

public class Bar extends Level {
    private int beerDrank;
    private boolean hasPickedUpBefore = false;

    public Bar() {
        super(Hallway.class.getSimpleName(), null, Pinball.class.getSimpleName(), OfficeSouth.class.getSimpleName());
        legalActions.put(Command.of(Verb.PICK_UP, Noun.BEER_BOTTLE), game -> {
            if (!hasPickedUpBefore) {
                beerDrank++;
                hasPickedUpBefore = true;
                return UserFeedback.of(
                        "You pick up a beer and empty it's content. No use carrying this around except in your stomach.",
                        "Your alcoholism is so bad you can't even hold a bottle without devouring it's content. You are somewhat happy.");
            }
            return reactToBeer();
        });
        legalActions.put(Command.of(Verb.USE, Noun.BEER_BOTTLE), game -> reactToBeer());
        legalActions.put(Command.of(Verb.EXAMINE, Noun.BAR), game -> UserFeedback.of("There is nothing in the bar except beer"));
    }

    private UserFeedback reactToBeer() {
        switch (beerDrank) {
            case 2 -> Narrator.tell(UserFeedback.of("Your begining to like this."));
            case 4 -> Narrator.tell(UserFeedback.of("You begin to feel tipsy"));
            case 5 -> Narrator.tell(UserFeedback.of("U R noww druuunk"));
            case 6 -> Narrator.tell(UserFeedback.of("iF Youuuuuu keEp goinnnnnnng youll DIE"));
            case 7 -> {
                Narrator.tell(UserFeedback.of(DramaticEffect.WORD, "U diE fRomm Alchooohll  poisening ☠️"));
                throw new NullPointerException("Cannot invoke life because liver was null");
            }
            default -> Narrator.tell(UserFeedback.of("You chug a beer."));
        }
        beerDrank++;
        return UserFeedback.of("You feel good inside.");
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        beerDrank = 0;
        return UserFeedback.of(
                "You enter the bar. No sign of people, but there you can see beverages through the glass door of the fridge.",
                "There might be something useful behind the bar counter.",
                cameFrom(game));
    }
}
