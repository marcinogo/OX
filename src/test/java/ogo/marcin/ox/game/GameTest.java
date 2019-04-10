package ogo.marcin.ox.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import ogo.marcin.ox.automation.AutoMatchSettings;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.InputImpl;
import ogo.marcin.ox.io.InputValidation;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.io.QuitGameException;
import ogo.marcin.ox.player.Player.PlayerBuilder;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;
import ogo.marcin.ox.player.PlayerListCreator;
import org.mockito.Mockito;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class GameTest {

  public void testEndToEndQuitGame() {
    try (Scanner scanner = new Scanner("X\nX\nO\nquit")){
      setupGame(scanner).play();
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
    try (Scanner scanner = new Scanner(userSequence)){
      setupGame(scanner).play();
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
    try (Scanner scanner = new Scanner(userSequence)){
      setupGame(scanner).play();
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
    try (Scanner scanner = new Scanner(userSequence)){
      setupGame(scanner).play();
    } catch (QuitGameException e) {
      System.out.println(e.getMessage());
    }
  }

  private Game setupGame(Scanner scanner) {
    Output output = Mockito.mock(Output.class);
    InputValidation inputValidation = new InputValidation();
    Input input = new InputImpl(scanner, output, inputValidation);
    if(PlayerBuilder.unusedSigns.isEmpty()) {
      PlayerBuilder.unusedSigns = new LinkedList<>(Arrays.asList(Sign.values()));
    }
    PlayerAPI playerAPI = new PlayerAPIImpl(new PlayerListCreator(input, output)
        .createPlayers());
    Board board = new Board.BoardBuilder()
        .withDimension(input.getBoardDimensions())
        .build();
    BoardAPI boardAPI = new BoardAPIImpl(board);

    Settings settings = new Settings.SettingsBuilder()
        .withWinConditionInRange(
            input.getWinConditionInRange(3, boardAPI.getBoardDimension()))
        .build();

    AutoMatchSettings autoMatchSettings = new AutoMatchSettings(boardAPI, false);

    return new Game(settings, boardAPI, playerAPI, input, output, autoMatchSettings);
  }
}