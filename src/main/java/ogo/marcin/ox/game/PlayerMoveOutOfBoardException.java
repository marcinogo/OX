package ogo.marcin.ox.game;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerMoveOutOfBoardException extends RuntimeException {

  PlayerMoveOutOfBoardException(String message) {
    super(message);
  }
}
