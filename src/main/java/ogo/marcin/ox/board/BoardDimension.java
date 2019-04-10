package ogo.marcin.ox.board;

import ogo.marcin.ox.game.Coordinates;
import ogo.marcin.ox.io.Localization;

/**
 * Enclose board edge size.
 *
 * @author Marcin Ogorzalek
 */
public class BoardDimension {

  private static final int MIN_EDGE_SIZE = 3;
  private static final int MAX_EDGE_SIZE = 30;

  private final int boardEdge;

  private BoardDimension(int boardEdge) {
    this.boardEdge = boardEdge;
    Coordinates.setCoordinatesMaxXY(this.boardEdge);
  }

  private BoardDimension(BoardDimensionBuilder boardDimensionBuilder) {
    this(boardDimensionBuilder.boardEdge);
  }

  int getBoardEdge() {
    return boardEdge;
  }

  /**
   * Build BoardDimension based on data provided by user.
   *
   * @author Marcin Ogorzalek
   */
  public static class BoardDimensionBuilder {

    private int boardEdge;

    /**
     * Build BoardDimension based on data provided by user.
     *
     * @return representation of board edge
     */
    public BoardDimension build() {
      return new BoardDimension(this);
    }

    /**
     * Provide size of board edge for BoardDimension creation
     *
     * @return PlayerBuilder
     */
    public BoardDimensionBuilder withBoardEdgeSize(int boardEdge) throws BoardSizeException {
      if (!isEdgeCorrect(boardEdge)) {
        throw new BoardSizeException(String.format(
            Localization.getLocalizedText(Localization.LanguageKey.BOARD_SIZE_EXCEPTION),
            MIN_EDGE_SIZE, MAX_EDGE_SIZE
        ));
      }
      this.boardEdge = boardEdge;
      return this;
    }

    private boolean isEdgeCorrect(int boardEdge) {
      return boardEdge >= MIN_EDGE_SIZE && boardEdge <= MAX_EDGE_SIZE;
    }
  }
}
