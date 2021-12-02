package app.interaction;

import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Verbs {
    private static final HashMap<String, Verb> map = new HashMap<>();

     static {
         insert(List.of("git"), Verb.GIT);
         insert(List.of("go", "walk", "run", "prance", "move", "slide"), Verb.GO);
         insert(List.of("use", "interact", "push", "open", "close", "press", "click", "play", "drink", "draw", "write"), Verb.USE);
         insert(List.of("look", "inspect", "see", "examine", "explore", "search"), Verb.EXAMINE);
         insert(List.of("pick up", "take", "grab", "carry"), Verb.PICK_UP);
         insert(List.of("drop", "throw away", "throw"), Verb.DROP);
         insert(List.of("quit", "!q", "exit" ), Verb.QUIT);
         insert(List.of("help"), Verb.HELP);
         insert(List.of("wash", "rinse"), Verb.WASH);
         insert(List.of("kill", "stab", "cut", "slice"), Verb.KILL);
         insert(List.of("say", "talk", "speak"), Verb.TALK);
         insert(List.of("inventory"), Verb.INVENTORY);
         insert(List.of("lock",  "exit"), Verb.LOCK);
         insert(List.of("cd"), Verb.CD);
    }

    private Verbs() {}

    private static void insert(List<String> keys, Verb value) {
        keys.forEach(key -> map.put(key, value));
    }

    public static String pattern() {
        return String.join("|", map.keySet());
    }

    public static Verb find(String input) {
        List<String> matches= Pattern.compile(pattern()).matcher(input).results().map(MatchResult::group).toList();
        if (matches.isEmpty()) {
            return Verb.NONE;
        }
        input = input.replaceFirst(matches.get(0), "");
        return map.get(matches.get(0));
    }
}
