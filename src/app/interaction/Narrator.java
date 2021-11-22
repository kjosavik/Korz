package app.interaction;

import java.util.Arrays;
import java.util.Scanner;

public class Narrator {
    private static final Scanner scanner = new Scanner(System.in);

    private Narrator() {}

    public static Command askForCommand(String... question) {
        tell(question);
        Command answer = null;
        while(answer == null) {
            String input = scanner.nextLine();
            Verb verb = Verb.LOOK;
            Noun noun = Nouns.find(input);
            if (verb != null  && noun != null) {
                answer = new Command(verb, noun);
            } else {
                tell("I don't understand what you want.");
            }
        }
        return answer;
    }

    public static String askForString(String... question) {
        tell(question);
        return scanner.nextLine();
    }

    private static String legalVerbs() {
        return null;
    }

    public static void tell(String... textLines) {
        Arrays.stream(textLines).forEach(System.out::println);
    }
}
