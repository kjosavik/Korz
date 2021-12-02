package app.level.implementations;

import app.Game;
import app.interaction.*;

public class Terminal extends Level {
    private String folder = "";

    public Terminal() {
        UserFeedback cantGo = UserFeedback.of("You are in the terminal. If you wish to leave just type \"exit\" or \"quit\".");
        undefinedDirection = cantGo;
        nothingInThatDirection = cantGo;
        canTDoThat = UserFeedback.of("Your fat fingers misspell the command and nothing happens.",
                "Seriously... Do you really think the creator of this game had the time to implement that?",
                "Stop waisting your time and focus on the task.");

        legalActions.put(Command.of(Verb.GIT, Noun.PUSH), game -> );
    }

    @Override
    public void start(Game game) {
        Narrator.tell(levelDescription(game));
        Command command = Narrator.askForCommand(itemService.itemDescriptionForLevel(game.getCurrentLevel().getClass().getSimpleName()));
        while (!command.quit()) {
            UserFeedback feedback = actOnCommand(command, game);
            command = Narrator.askForCommand(feedback);
        }
        if (command.quit()) {
            game.changeLevel(game.getPreviousLevel());
        }
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        Narrator.tell(UserFeedback.of(DramaticEffect.WORD, "You move the cursor to the terminal."));
        Narrator.newLine(10);
        return UserFeedback.of("");
    }

    private void preFix(String playerName) {
        Narrator.tell(UserFeedback.of(playerName + "@"+ "linux-laptop:~"+ folder + " "));
    }
}
