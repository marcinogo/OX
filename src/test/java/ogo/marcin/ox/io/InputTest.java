package ogo.marcin.ox.io;


import java.util.Scanner;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardDimension;
import ogo.marcin.ox.game.Coordinates;
import ogo.marcin.ox.game.Judge;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class InputTest {

  @DataProvider
  public static Object[][] testString(){
    return new Object[][] {
        {"Tomek"},
        {"Marcin"},
        {"Tom"},
        {"Wojciech"},
        {"Bartosz"},
        {"Agnieszka"},
    };
  }

  @Test(dataProvider = "testString")
  public void testGetStringInput(String testString) {
    Scanner scanner = new Scanner(testString);
    Output output = Mockito.mock(Output.class);
    InputValidation inputValidation = new InputValidation();

    Input input = new InputImpl(scanner, output, inputValidation);

    String userInput = input.getStringInput();

    assert userInput.equals(testString) : "Input should be the same";
  }

  @DataProvider
  public static Object[][] boardWinConditionAndSize(){
    return new Object[][] {
        {3, 3},
        {3, 30},
        {10, 10},
        {9, 10},
        {15, 25},
        {30, 30},
    };
  }

  @Test(dataProvider = "boardWinConditionAndSize")
  public void testGetWinConditionInRange(Integer testWinCondition, Integer upperBond) {
    Scanner scanner = new Scanner(Integer.toString(testWinCondition));
    Output output = Mockito.mock(Output.class);
    InputValidation inputValidation = new InputValidation();

    Input input = new InputImpl(scanner, output, inputValidation);

    int userInput = input.getWinConditionInRange(3, upperBond);
    assert testWinCondition == userInput;
  }
}