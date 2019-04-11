package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardApi;
import ogo.marcin.ox.board.Sign;

/**
 * Provide various validation for game.
 *
 * @author Marcin Ogorzalek
 */
public class Judge {

  private final BoardApi boardAPI;
  private final Setting setting;

  Judge(BoardApi boardAPI, Setting setting) {
    this.boardAPI = boardAPI;
    this.setting = setting;
  }

  boolean isFreeSpaceOnBoard() {
    return boardAPI.isFreeSpaceOnBoard();
  }

  /**
   * Check if field under given coordinates have default value.
   *
   * @param coordinates coordinates of field on board.
   */
  public boolean isPlayerSignSetOnFreeSpace(Coordinates coordinates) {
    return boardAPI.isCoordinatesPointsToDefaultSign(setting.getDefaultSign(), coordinates);
  }

  boolean isPlayerWon(Sign playerSign, Coordinates coordinates) {
    return boardAPI
        .whetherWinningConditionHasBeenMet(playerSign, coordinates, setting.getWinCondition());
  }
}
