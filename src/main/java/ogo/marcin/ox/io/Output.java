package ogo.marcin.ox.io;

import java.io.PrintStream;

/**
 * Display messages to a user
 *
 * @author Marcin Ogorzalek
 */
public interface Output {

  /**
   * Display given string message
   *
   * @param content String with message
   */
  void print(String content);

  /**
   * Display a message from proper .properties file based on key given in parameter.
   *
   * @param languageKey key to .properties file
   */
  void print(Localization.LanguageKey languageKey);

  /**
   * Display a message from proper .properties file based on key given in parameter in print stream
   * passed in method call.
   *
   * @param newPrintStream different print stream
   * @param languageKey key to .properties file
   */
  void print(PrintStream newPrintStream, Localization.LanguageKey languageKey);

  /**
   * Display a message from parameter in print stream passed in method call.
   *
   * @param newPrintStream different print stream
   * @param message String with message
   */
  void print(PrintStream newPrintStream, String message);

  /**
   * Display formatted string based on text from proper .properties file under given key. Take array
   * of objects to represent.
   *
   * @param languageKey key to .properties file
   * @param args object to display
   */
  void printf(Localization.LanguageKey languageKey, Object... args);

  /**
   * Display formatted string based on text from proper .properties file under given key and it in
   * print stream passed in method call. Take array of objects to represent.
   *
   * @param newPrintStream different print stream
   * @param languageKey key to .properties file
   * @param args object to display
   */
  void printf(PrintStream newPrintStream, Localization.LanguageKey languageKey,
      Object... args);
}
