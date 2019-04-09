package ogo.marcin.ox.board;

import java.util.Arrays;
import java.util.Objects;
import ogo.marcin.ox.game.Coordinates;

/**
 * @author Marcin Ogorzalek
 */
public class Board {

  final Field[][] matrix;
  private final int edge;

  Board(BoardDimension boardDimension) {
    this.edge = boardDimension.getBoardEdge();
    this.matrix = new Field[edge][edge];
  }

  private Board(Board board) {
    this.edge = board.edge;
    this.matrix = new Field[board.edge][];
    copyMatrixContent(this.matrix, board.matrix);
  }

  private void copyMatrixContent(Field[][] newMatrix, Field[][] oldMatrix) {
    for (int i = 0; i < oldMatrix.length; i++) {
      newMatrix[i] = oldMatrix[i].clone();
    }
  }

  Board setField(Coordinates coordinates, Sign sign) {
    Board newBoard = new Board(this);
    Field fieldToChange = newBoard.matrix[coordinates.getYOfMove()][coordinates.getXOfMove()];
    newBoard.matrix[coordinates.getYOfMove()][coordinates.getXOfMove()] = fieldToChange
        .changeSign(sign);
    return newBoard;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        stringBuilder.append(matrix[i][j]);
      }
      if (i < matrix.length - 1) {
        stringBuilder.append(System.lineSeparator());
      }
    }
    return stringBuilder.toString();
  }

  Board setBoardMatrixCells(Sign sign) {
    Board board = new Board(this);
    int count = 0;
    for (int i = 0; i < board.matrix.length; i++) {
      for (int j = 0; j < board.matrix[i].length; j++) {
        count++;
        board.matrix[i][j] = new Field(sign, count);
      }
    }
    return board;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Board board = (Board) o;
    if (edge != board.edge) {
      return false;
    }
    return Arrays.deepEquals(matrix, board.matrix);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(edge);
    result = 31 * result;
    result += Arrays.deepHashCode(matrix);
    return result;
  }

  int getBoardEdge() {
    return edge;
  }

  public static class BoardBuilder {

    private BoardDimension dimension;

    public Board build() {
      Board board = new Board(dimension);
      return board.setBoardMatrixCells(Sign.DEFAULT);
    }

    public BoardBuilder withDimension(BoardDimension dimension) {
      this.dimension = dimension;
      return this;
    }
  }
}
