package ogo.marcin.ox.game;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerMoveOutOfBoardException extends RuntimeException {
    public PlayerMoveOutOfBoardException(String message) {
        super(message);
    }
}
