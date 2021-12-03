package app.level.implementations;

import app.Game;
import app.interaction.*;
import app.level.CutScenes;

public class Terminal extends Level {
    private static final String korz = "~/korz_game";
    private static final String out = "~/korz_game/out";
    private static final String src = "~/korz_game/src";
    private String currentFolder = korz;
    private boolean hasWonGame = false;

    public Terminal() {
        UserFeedback cantGo = UserFeedback.of("You are in the terminal. If you wish to leave just type \"exit\" or \"quit\".");
        undefinedDirection = cantGo;
        nothingInThatDirection = cantGo;
        canTDoThat = UserFeedback.of("must be superuser.");

        legalActions.put(Command.of(Verb.GIT, Noun.PUSH), game -> {
            if (hasWonGame) {
                return UserFeedback.of("Everything up-to-date");
            }
            Narrator.tell(UserFeedback.of(DramaticEffect.LINE,"Enumerating objects: 57, done.",
                    "Counting objects: 100% (57/57), done.",
                    "Delta compression using up to 12 threads",
                    "Compressing objects: 100% (34/34), done.",
                    "Writing objects: 100% (35/35), 13.28 KiB | 3.32 MiB/s, done.",
                    "Total 35 (delta 15), reused 0 (delta 0), pack-reused 0",
                    "remote: Resolving deltas: 100% (15/15), completed with 15 local objects.",
                    "To github.com:kjosavik/Korz.git",
                    "   3368ef7..5cafd16  master -> master"));



            hasWonGame = true;
            CutScenes.gameEnding(game.getPlayer());
            return UserFeedback.of("You can keep playing if you like. There is still much to explore.");
        });
        legalActions.put(Command.of(Verb.GIT, Noun.STATUS), game -> {
            if (hasWonGame) {
                return UserFeedback.of("On branch master",
                        "Your branch is up to date with 'origin/master'.",
                        "",
                        "nothing to commit, working tree clean");
            }
            return UserFeedback.of("On branch master",
                    "Your branch is ahead of 'origin/master' by 1 commit.",
                    "  (use \"git push\" to publish your local commits)");
        });
        legalActions.put(Command.of(Verb.GIT, Noun.LOG), game -> UserFeedback.of(
                "commit 5cafd16b0da5ef1e329bcd0002327a0a87baa003 (HEAD -> master" + (hasWonGame ? ", origin/master, origin/HEAD" : "") +")",
                "Author: "+ game.getPlayer().getName() + " <"+ game.getPlayer().getName()+"@kodeworks.no>",
                "Date:   Thu Dec 2 09:32:29 2021 +0100",
                "",
                "    Initial commit"));
        legalActions.put(Command.of(Verb.LS, Noun.NONE), game -> {
            if (currentFolder.equals(korz)) {
                return UserFeedback.of("Project.iml /out /src");
            }
            return UserFeedback.of("");
        });

        legalActions.put(Command.of(Verb.LS, Noun.OUT), game -> UserFeedback.of(""));
        legalActions.put(Command.of(Verb.LS, Noun.SRC), game -> UserFeedback.of(""));
        legalActions.put(Command.of(Verb.SUDO, Noun.NONE), game -> {
            Narrator.askForString(UserFeedback.of("Password: "));
            return UserFeedback.of("Sorry, try again");
        });
        legalActions.put(Command.of(Verb.CD, Noun.OUT), game -> {
            if (currentFolder.equals(korz)) {
                currentFolder = out;
                return UserFeedback.of("");
            }
            return noSuchFolder();
        });
        legalActions.put(Command.of(Verb.CD, Noun.SRC), game -> {
            if (currentFolder.equals(korz)) {
                currentFolder = src;
                return UserFeedback.of("");
            }
            return noSuchFolder();
        });
        legalActions.put(Command.of(Verb.CD, Noun.PREV_FOLDER), game -> {
            if (currentFolder.equals(out) || currentFolder.equals(src)) {
                currentFolder = korz;
                return UserFeedback.of("");
            }
            return canTDoThat;
        });
    }

    @Override
    public void start(Game game) {
        Narrator.tell(levelDescription(game));
        Command command = Narrator.askForTerminalCommand(UserFeedback.of(""), preFix(game.getPlayer().getName()));
        while (!command.quit()) {
            UserFeedback feedback = actOnCommand(command, game);
            command = Narrator.askForTerminalCommand(feedback, preFix(game.getPlayer().getName()));
        }
        if (command.quit()) {
            game.changeLevel(game.getPreviousLevel());
        }
    }

    @Override
    public UserFeedback levelDescription(Game game) {
        Narrator.tell(UserFeedback.of(DramaticEffect.WORD, "You move the cursor to the terminal."));
        Narrator.newLine(10);
        return UserFeedback.of("You can leave the terminal by typing \"exit\"");
    }

    private UserFeedback preFix(String playerName) {
        return UserFeedback.of(playerName + "@"+ "linux-laptop:"+ currentFolder + "$ ");
    }

    private UserFeedback noSuchFolder() {
        return UserFeedback.of("cd: no such file or directory:");
    }
}
