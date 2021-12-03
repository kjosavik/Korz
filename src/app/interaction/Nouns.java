package app.interaction;

import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Nouns {
    private static final HashMap<String, Noun> map = new HashMap<>();

    static {
        insert(List.of("push"), Noun.PUSH);
        insert(List.of("knife"), Noun.KNIFE);
        insert(List.of("coffee", "pot"), Noun.COFFEE_POT);
        insert(List.of("beer", "bottle", "drink", "beverage", "alcohol"), Noun.BEER_BOTTLE);
        insert(List.of("left", "west"), Noun.LEFT);
        insert(List.of("right", "east"), Noun.RIGHT);
        insert(List.of("north"), Noun.UP);
        insert(List.of("south"), Noun.DOWN);
        insert(List.of("button"), Noun.BUTTON);
        insert(List.of("wall", "walls", "room"), Noun.ROOM);
        insert(List.of("door"), Noun.DOOR);
        insert(List.of("light"), Noun.LIGHT);
        insert(List.of("mirror"), Noun.MIRROR);
        insert(List.of("toilet", "shit", "piss", "poo", "poop"), Noun.TOILET);
        insert(List.of("faucet", "sink"), Noun.FAUCET);
        insert(List.of("self", "yourself"), Noun.SELF);
        insert(List.of("cabinets", "cupboards", "cabinet", "kitchen"), Noun.CABINETS);
        insert(List.of("bar", "counter"), Noun.BAR);
        insert(List.of("fridge", "refrigerator"), Noun.FRIDGE);
        insert(List.of("table", "pinball"), Noun.PINBALL);
        insert(List.of("clothes", "coats", "shoes", "coat", "shoe"), Noun.CLOTHES);
        insert(List.of("board", "whiteboard"), Noun.WHITEBOARD);
        insert(List.of("man", "guy", "person"), Noun.MAN);
        insert(List.of("laptop", "computer"), Noun.COMPUTER);
        insert(List.of("terminal", "cmd", "command line", "iTerm"), Noun.TERMINAL);
        insert(List.of("status", "commit"), Noun.STATUS);
        insert(List.of("out"), Noun.OUT);
        insert(List.of("src"), Noun.SRC);
        insert(List.of("log"), Noun.LOG);
    }

    private Nouns() {}

    private static void insert(List<String> keys, Noun value) {
       keys.forEach(key -> map.put(key, value));
    }

    public static String pattern() {
        return String.join("|", map.keySet());
    }

    public static Noun find(String input) {
        if (input.equals(" ..")) {
            return Noun.PREV_FOLDER;
        }
        List<String> matches= Pattern.compile(pattern()).matcher(input).results().map(MatchResult::group).toList();
        if (matches.isEmpty()) {
            return Noun.NONE;
        }
        return map.get(matches.get(matches.size()-1));
    }
}