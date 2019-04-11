package ogo.marcin.ox.io;

/**
 * Exception thrown when user type quit to stop execution of application
 *
 * @author Marcin Ogorzalek
 */
public class QuitGameException extends RuntimeException {

  QuitGameException(String message) {
    super(message);
  }
}
