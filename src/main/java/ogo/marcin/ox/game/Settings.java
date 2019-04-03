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

    private Settings(int winCondition) {
        this(Sign.DEFAULT, winCondition, 3);
    }

    private Settings(SettingsBuilder settingsBuilder) {
        this(settingsBuilder.defaultSign, settingsBuilder.winCondition, settingsBuilder.numberOfRounds);
    }

    public Sign getDefaultSign() {
        return defaultSign;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public static class SettingsBuilder {
        private Input input;

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
        public SettingsBuilder withWinCondition() {
            System.out.println("Give win condition for game");
            this.winCondition = input.getIntegerInput();
            return this;
        }

        public SettingsBuilder withNumberOfRounds() {
            this.numberOfRounds = 3;
            return this;
        }

        public SettingsBuilder withNumberOfRounds(boolean nonDefault) {
            if(!nonDefault) {
                return withNumberOfRounds();
            }
            System.out.println("Give number of rounds");
            this.numberOfRounds = input.getIntegerInput();
            return this;
        }

        public SettingsBuilder withDefaultSign(boolean nonDefault) {
            if(!nonDefault) {
                return withDefaultSign();
            }
            System.out.println("Give default sign ");
            String signString = null;
            do {
                signString = input.getStringInput();
            } while (validateSign(signString));
            this.defaultSign = Sign.valueOf(signString);
            return this;
        }

        public SettingsBuilder withDefaultSign() {
            this.defaultSign = Sign.DEFAULT;
            return this;
        }

        private boolean validateSign(String signString) {
            try {
                Sign.valueOf(signString);
            } catch (IllegalArgumentException e) {
                return false;
            }
            return true;
        }
    }
}
