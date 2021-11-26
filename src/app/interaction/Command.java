package app.interaction;

public record Command(Verb verb, Noun noun) {

    public boolean quit() {
        return verb.equals(Verb.QUIT) && noun.equals(Noun.NONE);
    }

    public boolean incomprehensible() {
        return verb.equals(Verb.NONE) && noun.equals(Noun.NONE);
    }
}
