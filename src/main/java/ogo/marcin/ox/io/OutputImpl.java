package ogo.marcin.ox.io;

import java.io.PrintStream;

/**
 * @author Marcin Ogorzalek
 */
public class OutputImpl implements Output {

  private final PrintStream printStream;

  public OutputImpl(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void print(String content) {
    printStream.println(content);
  }

  @Override
  public void print(Localization.LanguageKey languageKey) {
    printStream.println(Localization.getLocalizedText(languageKey));
  }

  @Override
  public void print(PrintStream newPrintStream, Localization.LanguageKey languageKey) {
    newPrintStream.println(Localization.getLocalizedText(languageKey));
  }

  @Override
  public void print(PrintStream newPrintStream, String message) {
    newPrintStream.println(message);
  }

  @Override
  public void printf(Localization.LanguageKey languageKey, Object... args) {
    printStream.printf(Localization.getLocalizedText(languageKey), args);
  }

  @Override
  public void printf(PrintStream newPrintStream, Localization.LanguageKey languageKey,
      Object... args) {
    newPrintStream.printf(Localization.getLocalizedText(languageKey), args);
  }
}
