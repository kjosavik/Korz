package app.interaction;

import java.util.EnumMap;
import java.util.List;

public class Verbs {
    private final EnumMap<Verb, List<String>> map;

    public Verbs() {
        this.map = new EnumMap<>(Verb.class);
        this.map.put(Verb.GO, List.of("go", "walk", "run", "prance", "move", "slide"));
        this.map.put(Verb.USE, List.of("use", "interact", "push", "open", "close"));
        this.map.put(Verb.LOOK, List.of("look", "inspect", "see", "examine"));
        this.map.put(Verb.PICK_UP, List.of("pick up", "take"));
        this.map.put(Verb.DROP, List.of("drop", "throw away", "throw"));
        this.map.put(Verb.QUIT, List.of("quit", "!q", "exit"));
    }

}
