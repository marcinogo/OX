package ogo.marcin.ox.board;

import ogo.marcin.ox.game.Coordinates;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {

  void setField(Coordinates coordinates, Sign sign);

  boolean isFreeSpaceOnBoard();

  boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates);

  boolean whetherWinningConditionHasBeenMet(Sign playerSign, Coordinates coordinates,
      int winCondition);

  Board getBoard();

  void clearBoard(Sign sign);

  int getBoardDimension();
}
