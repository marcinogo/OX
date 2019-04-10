package ogo.marcin.ox.board;

import ogo.marcin.ox.game.Coordinates;

/**
 * Provide methods to manipulate game board and get data about it.
 *
 * @author Marcin Ogorzalek
 */
public interface BoardApi {

  Board getBoard();

  /**
   * Set board field to provided sign under given coordinate.
   *
   * @param coordinates field on board.
   * @param sign field value.
   */
  void setField(Coordinates coordinates, Sign sign);

  /**
   * Return information if any board filed have default value.
   *
   * @return true if any fields with default sing left, false otherwise
   */
  boolean isFreeSpaceOnBoard();

  /**
   * Return size of board edge as int.
   *
   * @return board edge size.
   */
  int getBoardDimension();

  /**
   * Check if field under given coordinates have default value.
   *
   * @param defaultSign default sign of board.
   * @param coordinates field on board..
   */
  boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates);

  /**
   * Check if number and order of player sign on board meet winning condition.
   *
   * @param playerSign player representation on board.
   * @param coordinates field on board.
   * @param winCondition number of sign in combination necessary to win.
   * @return true if player meet win condition, false otherwise.
   */
  boolean whetherWinningConditionHasBeenMet(Sign playerSign, Coordinates coordinates,
      int winCondition);

  /**
   * Set all fields on board to given sign.
   */
  void clearBoard(Sign sign);
}
