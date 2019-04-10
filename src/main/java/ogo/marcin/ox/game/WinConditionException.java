package ogo.marcin.ox.game;

/**
 * Thrown if user desired win condition is lover than 3 and greater than board edge.
 *
 * @author Marcin Ogorzalek
 */
public class WinConditionException extends RuntimeException {

  public WinConditionException(String message) {
    super(message);
  }
}
