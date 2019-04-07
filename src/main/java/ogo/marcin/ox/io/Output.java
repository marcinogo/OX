package ogo.marcin.ox.io;

import java.io.PrintStream;

/**
 * @author Marcin Ogorzalek
 */
public class Output {
    private final PrintStream printStream;

    public Output(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void print(Localization.LanguageKey languageKey) {
        printStream.println(Localization.getLocalizedText(languageKey));
    }

    public void print(PrintStream newPrintStream, String message) {
        newPrintStream.println(message);
    }

    public void printf(Localization.LanguageKey languageKey, Object... args) {
        printStream.printf(Localization.getLocalizedText(languageKey), args);
    }
}
