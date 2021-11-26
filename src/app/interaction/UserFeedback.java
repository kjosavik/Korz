package app.interaction;

public record UserFeedback(DramaticEffect effect, String... question) {

    public static UserFeedback of(String... question) {
        return of(DramaticEffect.NONE, question);
    }

    public static UserFeedback of(DramaticEffect effect, String... question) {
        return new UserFeedback(effect, question);
    }
}
