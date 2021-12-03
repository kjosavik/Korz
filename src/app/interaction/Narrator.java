package app.interaction;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Narrator {
    private static final Scanner scanner = new Scanner(System.in);

    private Narrator() {}

    public static Command askForCommand(UserFeedback userFeedback) {
        tell(userFeedback);
        newLine();
        Command answer = null;
        while(answer == null || answer.incomprehensible()) {
            String input = scanner.nextLine();
            String lowerCaseInput = input.toLowerCase(Locale.ROOT);
            VerbResult verbResult = Verbs.find(lowerCaseInput);
            Noun noun = Nouns.find(verbResult.inputSubString());
            answer = new Command(verbResult.verb(), noun);
            if (answer.incomprehensible()) {
                tell(UserFeedback.of("I don't understand what you want. Try the command \"help\" or \"quit\""));
            }
        }
        newLine();
        return answer;
    }


    public static Command askForTerminalCommand(UserFeedback userFeedback, UserFeedback preFix) {
        tell(userFeedback);
        System.out.print(preFix.question()[0]);
        Command answer = null;
        while(answer == null || answer.incomprehensible()) {
            String input = scanner.nextLine();
            String lowerCaseInput = input.toLowerCase(Locale.ROOT);
            VerbResult verbResult = Verbs.find(lowerCaseInput);
            Noun noun = Nouns.find(verbResult.inputSubString());
            answer = new Command(verbResult.verb(), noun);
            if (answer.incomprehensible()) {
                tell(UserFeedback.of("I don't understand what you want. You can exit the terminal with \"exit\""));
            }
        }
        return answer;
    }

    public static String askForString(UserFeedback userFeedback) {
        tell(userFeedback);
        return scanner.nextLine();
    }

    public static void askForEnter(UserFeedback userFeedback) {
        tell(userFeedback);
        tell(UserFeedback.of("press ⏎"));
        scanner.nextLine();
    }

    public static void tell(UserFeedback userFeedback) {
        String[] textLines = userFeedback.question();
        if (textLines.length == 1 && textLines[0].equals("")) {
            return;
        }
        switch (userFeedback.effect()) {
            case NONE -> Arrays.stream(textLines).forEach(System.out::println);
            case LETTER -> {
                Arrays.stream(textLines).forEach(line -> {
                    Arrays.stream(line.split("")).forEach(character -> {
                        System.out.print(character);
                        pause(70);
                    });
                    newLine();
                });
            }
            case WORD -> {
                Arrays.stream(textLines).forEach(line -> {
                    Arrays.stream(line.split(" ")).forEach(word -> {
                        System.out.print(word + " ");
                        pause(500);
                    });
                    newLine();
                });
            }
            case LINE -> Arrays.stream(textLines).forEach(line -> {
                System.out.println(line);
                pause(200);
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
