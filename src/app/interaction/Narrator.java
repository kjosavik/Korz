package app.interaction;

import java.util.Arrays;
import java.util.Scanner;

public class Narrator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int dramaticEffectMs = 150;

    private Narrator() {}

    public static Command askForCommand(UserFeedback userFeedback) {
        tell(userFeedback);
        Command answer = null;
        while(answer == null || answer.incomprehensible()) {
            String input = scanner.nextLine();
            Verb verb = Verbs.find(input);
            Noun noun = Nouns.find(input);
            answer = new Command(verb, noun);
            if (answer.incomprehensible()) {
                tell(UserFeedback.of("I don't understand what you want."));
            }
        }
        return answer;
    }

    public static String askForString(UserFeedback userFeedback) {
        tell(userFeedback);
        return scanner.nextLine();
    }

    public static void tell(UserFeedback userFeedback) {
        String[] textLines = userFeedback.question();
        switch (userFeedback.effect()) {
            case NONE -> Arrays.stream(textLines).forEach(System.out::println);
            case LETTER -> {
                Arrays.stream(String.join(" ", textLines).split("")).forEach(character -> {
                    System.out.print(character);
                    pause(dramaticEffectMs);
                });
                newLine();
            }
            case WORD -> {
                Arrays.stream(String.join(" ", textLines).split(" ")).forEach(character -> {
                    System.out.print(character);
                    pause(dramaticEffectMs);
                });
                newLine();
            }
            case LINE -> Arrays.stream(textLines).forEach(line -> {
                System.out.println(line);
                pause(dramaticEffectMs);
            });
        }
    }

    public static void newLine() {
        newLine(1);
    }

    public static void newLine(int amount) {
        for ( int i = 0; i < amount; i++ ) {
            System.out.println("");
        }
    }

    public static void pause(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
