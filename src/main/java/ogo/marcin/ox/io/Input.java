package ogo.marcin.ox.io;

import ogo.marcin.ox.board.BoardDimension;
import ogo.marcin.ox.game.Coordinates;
import ogo.marcin.ox.game.Judge;

/**
 * Define methods for gathering user input.
 *
 * @author Marcin Ogorzalek
 */
public interface Input {

  /**
   * Gather input from user in form of string.
   */
  String getStringInput();

  /**
   * Gather an input in from of int and return coordinate on game board.
   *
   * @param judge use to validate if coordinate will be within board
   * @return coordinate based on user input
   */
  Coordinates getCoordinates(Judge judge);

  /**
   * Gather an input in from of int and return game board dimension (edge).
   * Min of the edge is 3 and max is 30.
   *
   * @return size of board edge enclosed in object
   */
  BoardDimension getBoardDimensions();

  /**
   * Gather an input in from of int and validate it.
   * Min of the win condition is 3 and max is 30. Max depends on board size.
   *
   * @return size of board edge enclosed in object
   */
  int getWinConditionInRange(int minWinCondition, int maxWinCondition);
}
