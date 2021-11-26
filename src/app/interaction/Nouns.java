package app.interaction;

import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Nouns {
    private static final HashMap<String, Noun> map = new HashMap<>();

    static {
        insert(List.of("knife"), Noun.KNIFE);
        insert(List.of("coffee", "pot"), Noun.COFFEE_POT);
        insert(List.of("beer", "bottle"), Noun.BEER_BOTTLE);
        insert(List.of("left", "west"), Noun.LEFT);
        insert(List.of("right", "east"), Noun.RIGHT);
        insert(List.of("up", "north"), Noun.UP);
        insert(List.of("down", "south"), Noun.DOWN);
    }

    private Nouns() {}

    private static void insert(List<String> keys, Noun value) {
       keys.forEach(key -> map.put(key, value));
    }

    public static String pattern() {
        return String.join("|", map.keySet());
    }

    public static Noun find(String input) {
        List<String> matches= Pattern.compile(pattern()).matcher(input).results().map(MatchResult::group).toList();
        if (matches.isEmpty()) {
            return Noun.NONE;
        }
        return map.get(matches.get(0));
    }
}