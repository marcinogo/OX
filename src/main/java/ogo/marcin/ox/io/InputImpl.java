package ogo.marcin.ox.io;

import java.util.Scanner;
import ogo.marcin.ox.board.BoardDimension;
import ogo.marcin.ox.board.BoardSizeException;
import ogo.marcin.ox.game.CoordinateNotFreeException;
import ogo.marcin.ox.game.Coordinates;
import ogo.marcin.ox.game.Judge;
import ogo.marcin.ox.game.PlayerMoveOutOfBoardException;
import ogo.marcin.ox.game.WinConditionException;

/**
 * Gather an input from user.
 *
 * @author Marcin Ogorzalek
 */
public class InputImpl implements Input {

  private final Scanner scanner;
  private final Output output;
  private final InputValidation inputValidation;

  public InputImpl(Scanner scanner, Output output, InputValidation inputValidation) {
    this.scanner = scanner;
    this.output = output;
    this.inputValidation = inputValidation;
  }

  /**
   * Gather input from user in form of string.
   */
  @Override
  public String getStringInput() {
    String userInput = scanner.nextLine();
    inputValidation.ifUserInputEqualsQuitEndApp(userInput);
    return userInput;
  }

  /**
   * Gather an input in from of int and return coordinate on game board.
   *
   * @param judge use to validate if coordinate will be within board
   * @return coordinate based on user input
   */
  @Override
  public Coordinates getCoordinates(Judge judge) {
    Coordinates coordinates = null;
    boolean coordinatesWithinBoardAndOnFreeSpace = false;
    do {
      try {
        coordinates = createCoordinates();
        inputValidation.validateCoordinate(judge, coordinates);
        coordinatesWithinBoardAndOnFreeSpace = true;
      } catch (PlayerMoveOutOfBoardException | CoordinateNotFreeException e) {
        output.print(System.err, e.getMessage());
      }
    } while (!coordinatesWithinBoardAndOnFreeSpace);
    return coordinates;
  }

  private Coordinates createCoordinates() throws PlayerMoveOutOfBoardException {
    int move = getIntegerInput(Localization.LanguageKey.ENTER_COORDINATE);
    return new Coordinates.CoordinatesBuilder()
        .withMovePosition(move)
        .build();
  }

  private int getIntegerInput(Localization.LanguageKey localizationLanguageKey) {
    int result = 0;
    boolean inputIsCorrect = false;
    do {
      output.print(System.out, localizationLanguageKey);
      String userInput = getStringInput();
      try {
        result = Integer.parseInt(userInput);
        inputIsCorrect = true;
      } catch (NumberFormatException e) {
        output.print(System.err, Localization.getLocalizedText(
            Localization.LanguageKey.NOT_INTEGER_EXCEPTION
        ));
      }
    } while (!inputIsCorrect);
    return result;
  }

  /**
   * Gather an input in from of int and return game board dimension (edge).
   * Min of the edge is 3 and max is 30.
   *
   * @return size of board edge enclosed in object
   */
  @Override
  public BoardDimension getBoardDimensions() {
    BoardDimension boardDimension = null;
    boolean dimensionsCreated = false;
    do {
      try {
        boardDimension = createBoard();
        dimensionsCreated = true;
      } catch (BoardSizeException e) {
        output.print(System.err, e.getMessage());
      }
    } while (!dimensionsCreated);
    return boardDimension;
  }

  private BoardDimension createBoard() throws BoardSizeException {
    int dimension = getIntegerInput(Localization.LanguageKey.BOARD_SIZE);
    return new BoardDimension.BoardDimensionBuilder()
        .withBoardEdgeSize(dimension)
        .build();
  }

  /**
   * Gather an input in from of int and validate it.
   * Min of the win condition is 3 and max is 30. Max depends on board size.
   *
   * @return size of board edge enclosed in object
   */
  @Override
  public int getWinConditionInRange(int minWinCondition, int maxWinCondition) {
    return getIntegerInputBetween(minWinCondition, maxWinCondition);
  }

  private int getIntegerInputBetween(int minWinCondition, int maxWinCondition) {
    int winCondition = 0;
    boolean inputIsCorrect = false;
    do {
      output.printf(System.out, Localization.LanguageKey.WIN_CONDITION,
          minWinCondition, maxWinCondition);
      try {
        winCondition = getIntegerInput(Localization.LanguageKey.ENTER_INT);
        if (!inputValidation.isIntegerBetween(winCondition, minWinCondition, maxWinCondition)) {
          throw new WinConditionException(Localization.getLocalizedText(
              Localization.LanguageKey.WIN_CONDITION_EXCEPTION
          ));
        }
        inputIsCorrect = true;
      } catch (WinConditionException e) {
        output.print(System.err, e.getMessage());
      }
    } while (!inputIsCorrect);
    return winCondition;
  }
}
