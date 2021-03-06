package ogo.marcin.ox.game;

import ogo.marcin.ox.io.Localization;

/**
 * Represents a user move on board
 *
 * @author Marcin Ogorzalek
 */
public class Coordinates {

  private static final int MIN_COMPONENT_OF_MOVE_INDEX = 0;
  static private int MAX_COMPONENT_OF_MOVE_INDEX;

  private final int xOfMove;
  private final int yOfMove;

  private Coordinates(int xOfMove, int yOfMove) {
    this.xOfMove = xOfMove;
    this.yOfMove = yOfMove;
  }

  private Coordinates(CoordinatesBuilder coordinatesBuilder) {
    this(coordinatesBuilder.xOfMove, coordinatesBuilder.yOfMove);
  }

  /**
   * Set upper bound for coordinates.
   *
   * @param boardEdge size of board edge
   */
  public static void setCoordinatesMaxXY(int boardEdge) {
    Coordinates.MAX_COMPONENT_OF_MOVE_INDEX = boardEdge;
  }

  public int getXOfMove() {
    return xOfMove;
  }

  public int getYOfMove() {
    return yOfMove;
  }

  /**
   * Build Coordinates object based on data provided by user.
   *
   * @author Marcin Ogorzalek
   */
  public static class CoordinatesBuilder {

    private int xOfMove;
    private int yOfMove;

    /**
     * Build Coordinates object based on data provided by user.
     *
     * @return Coordinates
     */
    public Coordinates build() {
      return new Coordinates(this);
    }

    /**
     * Provide user move position on board
     *
     * @return CoordinatesBuilder
     */
    public CoordinatesBuilder withMovePosition(int move) throws PlayerMoveOutOfBoardException {
      setXOfMove(move);
      setYOfMove(move);
      return this;
    }

    private void setXOfMove(int move) throws PlayerMoveOutOfBoardException {
      int xOfMove = recalculateUserInputToX(move);
      if (isComponentOfMoveNotCorrect(xOfMove)) {
        throw new PlayerMoveOutOfBoardException(
            Localization.getLocalizedText(Localization.LanguageKey.PLAYER_MOVE_EXCEPTION
            ));
      }
      this.xOfMove = xOfMove;
    }

    private int recalculateUserInputToX(int move) {
      return (move - 1) % MAX_COMPONENT_OF_MOVE_INDEX;
    }

    private boolean isComponentOfMoveNotCorrect(int componentOfMove) {
      return MIN_COMPONENT_OF_MOVE_INDEX > componentOfMove
          || componentOfMove >= MAX_COMPONENT_OF_MOVE_INDEX;
    }

    private void setYOfMove(int move) throws PlayerMoveOutOfBoardException {
      int yOfMove = recalculateUserInputToY(move);
      if (isComponentOfMoveNotCorrect(yOfMove)) {
        throw new PlayerMoveOutOfBoardException(
            Localization.getLocalizedText(Localization.LanguageKey.PLAYER_MOVE_EXCEPTION
            ));
      }
      this.yOfMove = yOfMove;
    }

    private int recalculateUserInputToY(int move) {
      return (move - 1) / MAX_COMPONENT_OF_MOVE_INDEX;
    }

  }
}