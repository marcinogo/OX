package ogo.marcin.ox.game;

import java.util.Arrays;
import java.util.Scanner;
import ogo.marcin.ox.automation.AutoMatchSettings;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardApi;
import ogo.marcin.ox.board.BoardApiImpl;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.InputImpl;
import ogo.marcin.ox.io.InputValidation;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.io.QuitGameException;
import ogo.marcin.ox.player.Player.PlayerBuilder;
import ogo.marcin.ox.player.PlayerApi;
import ogo.marcin.ox.player.PlayerApiImpl;
import ogo.marcin.ox.player.PlayerListCreator;
import org.mockito.Mockito;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class GameTest {

  private PlayerApi playerAPI;

  public void testEndToEndQuitGame() {
    try (Scanner scanner = new Scanner("X\nX\nO\nquit")) {
      setupGame(scanner).play();
      assert playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) == 0 :
          "Game should end";
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }

  public void testEndToEndXWin() {
    String userSequence = String.format(
        "X%nX%nO%n3%n3%n"
            + "1%n2%n3%n4%n5%n6%n7%n"
            + "1%n2%n3%n4%n5%n6%n7%n"
            + "1%n2%n3%n4%n5%n6%n7"
    );
    try (Scanner scanner = new Scanner(userSequence)) {
      setupGame(scanner).play();
      assert playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) < 0 :
          "X should win";
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }

  public void testEndToEndOWin() {
    String userSequence = String.format(
        "X%nX%nO%n3%n3%n"
            + "2%n1%n4%n3%n6%n5%n8%n7%n"
            + "2%n1%n4%n3%n6%n5%n8%n7%n"
            + "2%n1%n4%n3%n6%n5%n8%n7"
    );
    try (Scanner scanner = new Scanner(userSequence)) {
      setupGame(scanner).play();
      assert playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) > 0 :
          "O should win";
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }

  public void testEndToEndDraw() {
    String userSequence = String.format(
        "X%nX%nO%n3%n3%n"
            + "1%n3%n2%n4%n5%n8%n6%n9%n7%n"
            + "1%n3%n2%n4%n5%n8%n6%n9%n7%n"
            + "1%n3%n2%n4%n5%n8%n6%n9%n7"
    );
    try (Scanner scanner = new Scanner(userSequence)) {
      setupGame(scanner).play();
      assert playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) == 0 :
          "There should be draw";
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }

  private Game setupGame(Scanner scanner) {
    Output output = Mockito.mock(Output.class);
    InputValidation inputValidation = new InputValidation();
    Input input = new InputImpl(scanner, output, inputValidation);
    if (PlayerBuilder.getUnusedSigns().isEmpty()) {
      PlayerBuilder.getUnusedSigns().addAll(Arrays.asList(Sign.values()));
    }
    playerAPI = new PlayerApiImpl(new PlayerListCreator(input, output)
        .createPlayers());
    Board board = new Board.BoardBuilder()
        .withDimension(input.getBoardDimensions())
        .build();
    BoardApi boardAPI = new BoardApiImpl(board);

    Settings settings = new Settings.SettingsBuilder()
        .withWinConditionInRange(
            input.getWinConditionInRange(3, boardAPI.getBoardDimension()))
        .build();

    AutoMatchSettings autoMatchSettings = new AutoMatchSettings(boardAPI, false);

    return new Game(settings, boardAPI, playerAPI, input, output, autoMatchSettings);
  }
}