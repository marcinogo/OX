package ogo.marcin.ox.io;

import java.io.PrintStream;

/**
 * Display messages to a user
 *
 * @author Marcin Ogorzalek
 */
public class OutputImpl implements Output {

  private final PrintStream printStream;

  public OutputImpl(PrintStream printStream) {
    this.printStream = printStream;
  }

  /**
   * Display given string message
   *
   * @param content String with message
   */
  @Override
  public void print(String content) {
    printStream.println(content);
  }

  /**
   * Display a message from proper .properties file based on key given in parameter.
   *
   * @param languageKey key to .properties file
   */
  @Override
  public void print(Localization.LanguageKey languageKey) {
    printStream.println(Localization.getLocalizedText(languageKey));
  }

  /**
   * Display a message from proper .properties file based on key given in parameter in print stream
   * passed in method call.
   *
   * @param newPrintStream different print stream
   * @param languageKey key to .properties file
   */
  @Override
  public void print(PrintStream newPrintStream, Localization.LanguageKey languageKey) {
    newPrintStream.println(Localization.getLocalizedText(languageKey));
  }

  /**
   * Display a message from parameter in print stream passed in method call.
   *
   * @param newPrintStream different print stream
   * @param message String with message
   */
  @Override
  public void print(PrintStream newPrintStream, String message) {
    newPrintStream.println(message);
  }

  /**
   * Display formatted string based on text from proper .properties file under given key. Take array
   * of objects to represent.
   *
   * @param languageKey key to .properties file
   * @param args object to display
   */
  @Override
  public void printf(Localization.LanguageKey languageKey, Object... args) {
    printStream.printf(Localization.getLocalizedText(languageKey), args);
  }

  /**
   * Display formatted string based on text from proper .properties file under given key and it in
   * print stream passed in method call. Take array of objects to represent.
   *
   * @param newPrintStream different print stream
   * @param languageKey key to .properties file
   * @param args object to display
   */
  @Override
  public void printf(PrintStream newPrintStream, Localization.LanguageKey languageKey,
      Object... args) {
    newPrintStream.printf(Localization.getLocalizedText(languageKey), args);
  }
}
