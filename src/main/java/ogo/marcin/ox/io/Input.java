package ogo.marcin.ox.io;

import ogo.marcin.ox.board.BoardDimension;
import ogo.marcin.ox.game.Coordinates;
import ogo.marcin.ox.game.Judge;

/**
 * @author Marcin Ogorzalek
 */
public interface Input {

  String getStringInput();

  Coordinates getCoordinates(Judge judge);

  BoardDimension getBoardDimensions();

  int getWinConditionInRange(int minWinCondition, int maxWinCondition);
}
