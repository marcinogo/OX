package ogo.marcin.ox.game;

/**
 * Thrown if user try to pick field with non default sign.
 *
 * @author Marcin Ogorzalek
 */
public class CoordinateNotFreeException extends RuntimeException {

  public CoordinateNotFreeException(String message) {
    super(message);
  }
}
