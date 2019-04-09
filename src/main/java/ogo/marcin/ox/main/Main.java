package ogo.marcin.ox.main;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import ogo.marcin.ox.automation.AutoMatchSettings;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.game.Settings;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.InputImpl;
import ogo.marcin.ox.io.InputValidation;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.io.OutputImpl;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;
import ogo.marcin.ox.player.PlayerListCreator;

/**
 * @author Marcin Ogorzalek
 */
class Main {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
      Output output = new OutputImpl(System.out);
      InputValidation inputValidation = new InputValidation();
      Input input = new InputImpl(scanner, output, inputValidation);
      chooseLanguage(input, output);

      PlayerAPI playerAPI = new PlayerAPIImpl(new PlayerListCreator(input, output).createPlayers());

      Board board = new Board.BoardBuilder()
          .withDimension(input.getBoardDimensions())
          .build();
      BoardAPI boardAPI = new BoardAPIImpl(board);

      int MIN_WIN_CONDITION = 3;
      int MAX_WIN_CONDITION = boardAPI.getBoardDimension();

      Settings settings = new Settings.SettingsBuilder()
          .withWinConditionInRange(
              input.getWinConditionInRange(MIN_WIN_CONDITION, MAX_WIN_CONDITION))
          .build();

      AutoMatchSettings autoMatchSettings = new AutoMatchSettings(boardAPI, false);

      Game game = new Game(settings, boardAPI, playerAPI, input, output, autoMatchSettings);
      game.play();
    }
  }

  private static void chooseLanguage(Input input, Output output) {
    output.print(Localization.LanguageKey.CHOOSE_LANGUAGE);
    Localization.setResourceBundleLanguage(input.getStringInput());
  }
}
