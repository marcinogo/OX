package ogo.marcin.ox.board;

/**
 * Thrown in case when desired board edge is smaller than 3 or grater than 30
 *
 * @author Marcin Ogorzalek
 */
public class BoardSizeException extends RuntimeException {

  BoardSizeException(String message) {
    super(message);
  }
}
