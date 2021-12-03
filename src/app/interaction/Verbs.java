package app.interaction;

import javax.naming.NameClassPair;
import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Verbs {
    private static final HashMap<String, Verb> map = new HashMap<>();

     static {
         insert(List.of("git"), Verb.GIT);
         insert(List.of("go", "walk", "run", "prance", "move", "slide"), Verb.GO);
         insert(List.of("use", "interact", "push", "open", "close", "press", "click", "play", "drink", "draw", "write", "access"), Verb.USE);
         insert(List.of("look", "inspect", "see", "examine", "explore", "search"), Verb.EXAMINE);
         insert(List.of("pick up", "take", "grab", "carry"), Verb.PICK_UP);
         insert(List.of("drop", "throw away", "throw"), Verb.DROP);
         insert(List.of("quit", "!q", "exit" ), Verb.QUIT);
         insert(List.of("help"), Verb.HELP);
         insert(List.of("wash", "rinse"), Verb.WASH);
         insert(List.of("kill", "stab", "cut", "slice", "attack", "punch", "beat"), Verb.KILL);
         insert(List.of("say", "talk", "speak"), Verb.TALK);
         insert(List.of("inventory"), Verb.INVENTORY);
         insert(List.of("lock"), Verb.LOCK);
         insert(List.of("cd"), Verb.CD);
         insert(List.of("sudo"), Verb.SUDO);
         insert(List.of("ls"), Verb.LS);
         insert(List.of("give"), Verb.GIVE);
    }

    private Verbs() {}

    private static void insert(List<String> keys, Verb value) {
        keys.forEach(key -> map.put(key, value));
    }

    public static String pattern() {
        return String.join("|", map.keySet());
    }

    public static VerbResult find(String input) {
        List<String> matches= Pattern.compile(pattern()).matcher(input).results().map(MatchResult::group).toList();
        if (matches.isEmpty()) {
            return new VerbResult(Verb.NONE, input);
        }
        return new VerbResult(map.get(matches.get(0)),input.replaceFirst(matches.get(0), ""));
    }
}
