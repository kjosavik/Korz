package app.interaction;

public record Command(Verb verb, Noun noun) {

    public static Command of(Verb verb, Noun noun) {
        return new Command(verb, noun);
    }

    public boolean quit() {
        return verb.equals(Verb.QUIT) && noun.equals(Noun.NONE) || verb.equals(Verb.KILL) && noun.equals(Noun.SELF);
    }

    public boolean incomprehensible() {
        return verb.equals(Verb.NONE) && noun.equals(Noun.NONE);
    }
}
