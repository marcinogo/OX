package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardApi;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public class Judge {

  private final BoardApi boardAPI;
  private final Settings settings;

  Judge(BoardApi boardAPI, Settings settings) {
    this.boardAPI = boardAPI;
    this.settings = settings;
  }

  boolean isFreeSpaceOnBoard() {
    return boardAPI.isFreeSpaceOnBoard();
  }

  public boolean isPlayerSignSetOnFreeSpace(Coordinates coordinates) {
    return boardAPI.isCoordinatesPointsToDefaultSign(settings.getDefaultSign(), coordinates);
  }

  boolean isPlayerWon(Sign playerSign, Coordinates coordinates) {
    return boardAPI
        .whetherWinningConditionHasBeenMet(playerSign, coordinates, settings.getWinCondition());
  }
}
