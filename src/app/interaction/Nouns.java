package app.interaction;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Nouns {
    private static final HashMap<String, Noun> map;

    static {
        map = new HashMap();

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
       keys.forEach(key -> map.put(key, value)); ;
    }

    public static String legalNouns() {
        String test = String.join("|", map.keySet());
        return test;
    }

    public static Noun find(String input) {
        List<String> matches= Pattern.compile(legalNouns()).matcher(input).results().map(MatchResult::group).collect(Collectors.toList()); // Save list in case I want to get several matches
        if (matches.isEmpty()) {
            return Noun.NONE;
        }
        return map.get(matches.get(0));
    }
}