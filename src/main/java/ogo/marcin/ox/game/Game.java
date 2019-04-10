package ogo.marcin.ox.game;

import java.util.Collections;
import ogo.marcin.ox.automation.AutoMatchSetting;
import ogo.marcin.ox.board.BoardApi;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.player.PlayerApi;

/**
 * @author Marcin Ogorzalek
 */
public class Game {

  private final Setting setting;
  private final BoardApi boardAPI;
  private final PlayerApi playerAPI;
  private final Input input;
  private final Output output;
  private final AutoMatchSetting autoMatchSetting;

  public Game(Setting setting, BoardApi boardAPI, PlayerApi playerAPI,
      Input input, Output output, AutoMatchSetting autoMatchSetting) {
    this.setting = setting;
    this.boardAPI = boardAPI;
    this.playerAPI = playerAPI;
    this.input = input;
    this.output = output;
    this.autoMatchSetting = autoMatchSetting;
  }

  public void play() {
    for (int i = 0; i < setting.getNumberOfRounds(); i++) {
      Judge judge = new Judge(boardAPI, setting);
      Match match;
      if (autoMatchSetting.isAutomated()) {
        autoMatchSetting.setXAiMoves(i);
        output.print(autoMatchSetting.getPatternName(i));
      }
      match = new Match(input, output, boardAPI, playerAPI, judge, autoMatchSetting);
      match.play();
      boardAPI.clearBoard(setting.getDefaultSign());
      if (!autoMatchSetting.isAutomated()) {
        Collections.reverse(playerAPI.getPlayers());
      }
    }
    if (!autoMatchSetting.isAutomated()) {
      printResult();
    }
  }

  private void printResult() {
    if (playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) == 0) {
      output.print(Localization.LanguageKey.DRAW_OF_GAME);
    } else if (playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) > 0) {
      output.printf(Localization.LanguageKey.WINNER_OF_GAME,
          playerAPI.getPlayerName(playerAPI.getPlayerOnIndex(0)),
          playerAPI.getPlayerPoints(playerAPI.getPlayerOnIndex(0)));
    } else {
      output.printf(Localization.LanguageKey.WINNER_OF_GAME,
          playerAPI.getPlayerName(playerAPI.getPlayerOnIndex(1)),
          playerAPI.getPlayerPoints(playerAPI.getPlayerOnIndex(1)));
    }
  }
}
