package ogo.marcin.ox.game;

import ogo.marcin.ox.board.Sign;

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
        private Sign defaultSign;
        private int winCondition;
        private int numberOfRounds;

        private boolean isAutomated;

        public SettingsBuilder() {
        }

        public SettingsBuilder(boolean isAutomated) {
            this.isAutomated = isAutomated;
        }

        public Settings build() {
            if(!isAutomated) defaultSetup();
            return new Settings(this);
        }

        public SettingsBuilder withWinConditionInRange(int winCondition) {
            this.winCondition = winCondition;
            return this;
        }

        public SettingsBuilder withNumberOfRounds(int numberOfRounds) {
            this.numberOfRounds = numberOfRounds;
            return this;
        }

        public SettingsBuilder withDefaultSing(Sign sign) {
            this.defaultSign = sign;
            return this;
        }

        private void defaultSetup() {
            this.defaultSign = Sign.DEFAULT;
            this.numberOfRounds = 3;
        }
    }
}
