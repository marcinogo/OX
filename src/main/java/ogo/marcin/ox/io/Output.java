package ogo.marcin.ox.io;

import java.io.PrintStream;

/**
 * @author Marcin Ogorzalek
 */
public interface Output {

  void print(String content);

  void print(Localization.LanguageKey languageKey);

  void print(PrintStream newPrintStream, Localization.LanguageKey languageKey);

  void print(PrintStream newPrintStream, String message);

  void printf(Localization.LanguageKey languageKey, Object... args);

  void printf(PrintStream newPrintStream, Localization.LanguageKey languageKey,
      Object... args);
}
