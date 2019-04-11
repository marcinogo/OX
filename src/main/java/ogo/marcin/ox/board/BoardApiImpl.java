package ogo.marcin.ox.board;

import ogo.marcin.ox.game.Coordinates;

/**
 * Provide methods to manipulate game board and get data about it.
 *
 * @author Marcin Ogorzalek
 */
public class BoardApiImpl implements BoardApi {

  private Board board;
  private int occupiedFields;

  public BoardApiImpl(Board board) {
    this.board = board;
    this.occupiedFields = 0;
  }

  @Override
  public Board getBoard() {
    return board;
  }

  /**
   * Set board field to provided sign under given coordinate.
   *
   * @param coordinates field on board.
   * @param sign field value.
   */
  @Override
  public void setField(Coordinates coordinates, Sign sign) {
    board = board.setField(coordinates, sign);
    occupiedFields++;
  }

  /**
   * Return information if any board filed have default value.
   *
   * @return true if any fields with default sing left, false otherwise
   */
  @Override
  public boolean isFreeSpaceOnBoard() {
    return occupiedFields != board.getBoardEdge() * board.getBoardEdge();
  }

  /**
   * Return size of board edge as int.
   *
   * @return board edge size.
   */
  @Override
  public int getBoardDimension() {
    return this.board.getBoardEdge();
  }

  /**
   * Check if field under given coordinates have default value.
   *
   * @param defaultSign default sign of board.
   * @param coordinates field on board.
   * @return tree if there is free space, false otherwise
   */
  @Override
  public boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates) {
    return board.matrix[coordinates.getYOfMove()][coordinates.getXOfMove()].sign
        .equals(defaultSign);
  }

  /**
   * Check if number and order of player sign on board meet winning condition.
   *
   * @param playerSign player representation on board.
   * @param coordinates field on board.
   * @param winCondition number of sign in combination necessary to win.
   * @return true if player meet win condition, false otherwise.
   */
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

  /**
   * Set all fields on board to given sign.
   */
  @Override
  public void clearBoard(Sign sign) {
    this.board = board.setBoardMatrixCells(sign);
    this.occupiedFields = 0;
  }
}
