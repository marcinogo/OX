package ogo.marcin.ox.main;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import ogo.marcin.ox.automation.AutoMatchSetting;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardApi;
import ogo.marcin.ox.board.BoardApiImpl;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.game.Setting;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.InputImpl;
import ogo.marcin.ox.io.InputValidation;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.io.OutputImpl;
import ogo.marcin.ox.io.QuitGameException;
import ogo.marcin.ox.player.PlayerApi;
import ogo.marcin.ox.player.PlayerApiImpl;
import ogo.marcin.ox.player.PlayerListCreator;

/**
 * Tic Tac Toe game.
 *
 * @author Marcin Ogorzalek
 */
class Main {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
      Output output = new OutputImpl(System.out);
      InputValidation inputValidation = new InputValidation();
      Input input = new InputImpl(scanner, output, inputValidation);
      chooseLanguage(input, output);

      PlayerApi playerAPI = new PlayerApiImpl(new PlayerListCreator(input, output).createPlayers());

      Board board = new Board.BoardBuilder()
          .withDimension(input.getBoardDimensions())
          .build();
      BoardApi boardAPI = new BoardApiImpl(board);

      int MIN_WIN_CONDITION = 3;
      int MAX_WIN_CONDITION = boardAPI.getBoardDimension();

      Setting setting = new Setting.SettingsBuilder()
          .withWinConditionInRange(
              input.getWinConditionInRange(MIN_WIN_CONDITION, MAX_WIN_CONDITION))
          .build();

      AutoMatchSetting autoMatchSetting = new AutoMatchSetting(boardAPI, false);

      Game game = new Game(setting, boardAPI, playerAPI, input, output, autoMatchSetting);
      game.play();
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void chooseLanguage(Input input, Output output) {
    output.print(Localization.LanguageKey.CHOOSE_LANGUAGE);
    Localization.setResourceBundleLanguage(input.getStringInput());
  }
}
