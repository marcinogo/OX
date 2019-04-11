package ogo.marcin.ox.game;

import ogo.marcin.ox.board.Sign;

/**
 * Setting of a game.
 *
 * @author Marcin Ogorzalek
 */
public class Setting {

  private final Sign defaultSign;
  private final int winCondition;
  private final int numberOfRounds;

  private Setting(Sign defaultSign, int winCondition, int numberOfRounds) {
    this.defaultSign = defaultSign;
    this.winCondition = winCondition;
    this.numberOfRounds = numberOfRounds;
  }

  private Setting(SettingsBuilder settingsBuilder) {
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

  /**
   * Build Settings based on data provided by user.
   *
   * @author Marcin Ogorzalek
   */
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

    /**
     * Build Settings based on data provided by user.
     *
     * @return settings of a game.
     */
    public Setting build() {
      if (!isAutomated) {
        defaultSetup();
      }
      return new Setting(this);
    }

    /**
     * Provide win condition based on user input.
     *
     * @return SettingsBuilder
     */
    public SettingsBuilder withWinConditionInRange(int winCondition) {
      this.winCondition = winCondition;
      return this;
    }

    /**
     * Provide number of game rounds based on user input.
     *
     * @return SettingsBuilder
     */
    public SettingsBuilder withNumberOfRounds(int numberOfRounds) {
      this.numberOfRounds = numberOfRounds;
      return this;
    }

    /**
     * Provide default sign of board's fields based on user input.
     *
     * @return SettingsBuilder
     */
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
