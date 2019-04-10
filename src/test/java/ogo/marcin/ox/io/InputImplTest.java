package ogo.marcin.ox.io;


import java.util.Scanner;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class InputImplTest {

  public void testGetStringInput() {
    String TEST_STRING = "test my string";
    Scanner scanner = new Scanner(TEST_STRING);
    Output output = Mockito.mock(Output.class);
    InputValidation inputValidation = new InputValidation();

    Input input = new InputImpl(scanner, output, inputValidation);

    String userInput = input.getStringInput();

    assert userInput.equals(TEST_STRING) : "Input should be the same";
  }

  @DataProvider
  public static Object[][] testCoordinate(){
    return new Object[][] {
        {3, 3},
        {3, 30},
        {10, 10},
        {9, 10},
        {15, 25},
        {30, 30},
    };
  }

  @Test(dataProvider = "testCoordinate")
  public void testGetWinConditionInRange(Integer testCoordinate, Integer upperBond) {
    Scanner scanner = new Scanner(Integer.toString(testCoordinate));
    Output output = Mockito.mock(Output.class);
    InputValidation inputValidation = new InputValidation();

    Input input = new InputImpl(scanner, output, inputValidation);

    int userInput = input.getWinConditionInRange(3, upperBond);
    assert testCoordinate == userInput;
  }
}