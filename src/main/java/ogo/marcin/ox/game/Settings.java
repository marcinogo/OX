package ogo.marcin.ox.game;

import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;

/**
 * @author Marcin Ogorzalek
 */
public class Settings {
    private final Sign defaultSign;
    private final int winCondition;
    private final int numberOfRounds;

    private Settings(Sign defaultSign, int winCondition, int numberOfRounds) {
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.numberOfRounds = numberOfRounds;
    }

    private Settings(SettingsBuilder settingsBuilder) {
        this(settingsBuilder.defaultSign, settingsBuilder.winCondition, settingsBuilder.numberOfRounds);
    }

    Sign getDefaultSign() {
        return defaultSign;
    }

    int getWinCondition() {
        return winCondition;
    }

    int getNumberOfRounds() {
        return numberOfRounds;
    }

    public static class SettingsBuilder {
        private final Input input;

        private Sign defaultSign;
        private int winCondition;
        private int numberOfRounds;

        public SettingsBuilder(Input input) {
            this.input = input;
        }

        public Settings build() {
            return new Settings(this);
        }

//        TODO: win condition <= height && xin condition <= width
        public SettingsBuilder withWinCondition(int boardDimension) {
            System.out.println("Give win condition for game");
            this.winCondition = input.getWinCondition(boardDimension);
            return this;
        }

        public SettingsBuilder withNumberOfRounds() {
            this.numberOfRounds = 3;
            return this;
        }

        public SettingsBuilder withDefaultSign() {
            this.defaultSign = Sign.DEFAULT;
            return this;
        }
    }
}
