package app.interaction;

import java.util.Objects;

public class Command {
    private final Verb verb;
    private final Noun noun;

    public Command(Verb verb, Noun noun) {
        this.verb = verb;
        this.noun = noun;
    }

    public boolean quit() {
        return verb.equals(Verb.QUIT) && noun.equals(Noun.NONE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return verb == command.verb && noun == command.noun;
    }

    @Override
    public int hashCode() {
        return Objects.hash(verb, noun);
    }
}
