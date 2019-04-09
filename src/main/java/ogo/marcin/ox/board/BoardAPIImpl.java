package ogo.marcin.ox.board;

import ogo.marcin.ox.game.Coordinates;

/**
 * @author Marcin Ogorzalek
 */
public class BoardAPIImpl implements BoardAPI {

  private Board board;
  private int occupiedFields;

  public BoardAPIImpl(Board board) {
    this.board = board;
    this.occupiedFields = 0;
  }

  @Override
  public Board getBoard() {
    return board;
  }

  @Override
  public void setField(Coordinates coordinates, Sign sign) {
    board = board.setField(coordinates, sign);
    occupiedFields++;
  }

  @Override
  public boolean isFreeSpaceOnBoard() {
    return occupiedFields != board.getBoardEdge() * board.getBoardEdge();
  }

  @Override
  public int getBoardDimension() {
    return this.board.getBoardEdge();
  }

  @Override
  public boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates) {
    return board.matrix[coordinates.getYOfMove()][coordinates.getXOfMove()].sign
        .equals(defaultSign);
  }

  public boolean whetherWinningConditionHasBeenMet(Sign playerSign, Coordinates coordinates,
      int winCondition) {
    return horizontalCheck(playerSign, coordinates, winCondition) ||
        verticalCheck(playerSign, coordinates, winCondition) ||
        diagonalCheck(playerSign, coordinates, winCondition) ||
        antidiagonalCheck(playerSign, coordinates, winCondition);
  }

  private boolean horizontalCheck(Sign playerSign, Coordinates coordinates, int winCondition) {
    int count = 1 + numberOfPlayerFieldsRight(playerSign, coordinates)
        + numberOfPlayerFieldsLeft(playerSign, coordinates);
    return count == winCondition;
  }

  private int numberOfPlayerFieldsRight(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;
    Field[] row = board.matrix[y];
    for (int i = x + 1; i < row.length; i++) {
      if (row[i].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private int numberOfPlayerFieldsLeft(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;
    Field[] row = board.matrix[y];
    for (int i = x - 1; i >= 0; i--) {
      if (row[i].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private boolean verticalCheck(Sign playerSign, Coordinates coordinates, int winCondition) {
    int count = 1 + numberOfPlayerFieldsDown(playerSign, coordinates)
        + numberOfPlayerFieldsUp(playerSign, coordinates);
    return count == winCondition;
  }

  private int numberOfPlayerFieldsDown(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;

    for (int i = y + 1; i < getBoardDimension(); i++) {
      if (board.matrix[i][x].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private int numberOfPlayerFieldsUp(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;

    for (int i = y - 1; i >= 0; i--) {
      if (board.matrix[i][x].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private boolean diagonalCheck(Sign playerSign, Coordinates coordinates, int winCondition) {
    int count = 1 + numberOfPlayerFieldsDownRight(playerSign, coordinates)
        + numberOfPlayerFieldsUpLeft(playerSign, coordinates);
    return count == winCondition;
  }

  private int numberOfPlayerFieldsDownRight(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;

    for (int i = y + 1, j = x + 1; i < getBoardDimension() && j < board.matrix[i].length;
        i++, j++) {
      if (board.matrix[i][j].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private int numberOfPlayerFieldsUpLeft(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;

    for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
      if (board.matrix[i][j].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private boolean antidiagonalCheck(Sign playerSign, Coordinates coordinates, int winCondition) {
    int count = 1 + numberOfPlayerFieldsDownLeft(playerSign, coordinates)
        + numberOfPlayerFieldsUpRight(playerSign, coordinates);
    return count == winCondition;
  }

  private int numberOfPlayerFieldsDownLeft(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;

    for (int i = y + 1, j = x - 1; i < getBoardDimension() && j >= 0; i++, j--) {
      if (board.matrix[i][j].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  private int numberOfPlayerFieldsUpRight(Sign playerSign, Coordinates coordinates) {
    int x = coordinates.getXOfMove();
    int y = coordinates.getYOfMove();
    int count = 0;

    for (int i = y - 1, j = x + 1; i >= 0 && j < getBoardDimension(); i--, j++) {
      if (board.matrix[i][j].sign.equals(playerSign)) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  @Override
  public void clearBoard(Sign sign) {
    this.board = board.setBoardMatrixCells(sign);
    this.occupiedFields = 0;
  }
}
