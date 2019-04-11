package ogo.marcin.ox.game;

/**
 * Thrown if user desired move is not inside board.
 *
 * @author Marcin Ogorzalek
 */
public class PlayerMoveOutOfBoardException extends RuntimeException {

  PlayerMoveOutOfBoardException(String message) {
    super(message);
  }
}
