package ogo.marcin.ox.automation;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardApi;
import ogo.marcin.ox.board.BoardApiImpl;
import ogo.marcin.ox.board.BoardDimension;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.game.Setting;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.InputImpl;
import ogo.marcin.ox.io.InputValidation;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.io.OutputImpl;
import ogo.marcin.ox.io.QuitGameException;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerApi;
import ogo.marcin.ox.player.PlayerApiImpl;

/**
 * Starting point for automated tests. After configuration
 * results are saved to file "automated_test.txt".
 *
 * @author Marcin Ogorzalek
 */
class AutoMain {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

      Output output = new OutputImpl(
          new PrintStream(new File("automated_test.txt"), StandardCharsets.UTF_8));
      InputValidation inputValidation = new InputValidation();
      Input input = new InputImpl(scanner, output, inputValidation);
      Localization.setResourceBundleLanguage("ENGLISH");

      BoardDimension boardDimension = input.getBoardDimensions();

      Board board = new Board.BoardBuilder()
          .withDimension(boardDimension)
          .build();
      BoardApi boardApi = new BoardApiImpl(board);

      int MIN_BOARD_SIZE = 3;
      int winCondition = input.getWinConditionInRange(MIN_BOARD_SIZE, boardApi.getBoardDimension());

      WinConditionGenerator winConditionGenerator = new WinConditionGenerator(
          boardApi.getBoardDimension(),
          winCondition);
      winConditionGenerator.generateWinPatternRows()
          .generateWinPatternColumns()
          .generateWinPatternDiagonal()
          .generateWinPatternAntidiagonal();

      int numberOfRounds = winConditionGenerator.winPatterns.size();

      List<Player> players = new LinkedList<>();
      PlayerApi playerApi = new PlayerApiImpl(players);
      players.add(new Player.PlayerBuilder().withName("X-AI").withSign("X").build());
      players.add(new Player.PlayerBuilder().withName("O-AI").withSign("O").build());

      Setting setting = new Setting.SettingsBuilder(true)
          .withWinConditionInRange(winCondition)
          .withNumberOfRounds(numberOfRounds)
          .withDefaultSing(Sign.DEFAULT)
          .build();

      AutoMatchSetting autoMatchSetting = new AutoMatchSetting(boardApi,
          true, winConditionGenerator.winPatterns);

      Game game = new Game(setting, boardApi, playerApi, input, output, autoMatchSetting);
      game.play();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }
}