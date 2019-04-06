package ogo.marcin.ox.io;

import java.io.PrintStream;
import java.util.ResourceBundle;

/**
 * @author Marcin Ogorzalek
 */
public class Output {
    private final PrintStream printStream;
    private final ResourceBundle resourceBundle;

    public Output(PrintStream printStream, ResourceBundle resourceBundle) {
        this.printStream = printStream;
        this.resourceBundle = resourceBundle;
    }

    public void print(String message) {
        printStream.println(message);
    }

    public void print(Localization.Key key) {
        printStream.println(resourceBundle.getString(key.name()));
    }

    public void print(PrintStream newPrintStream, String message) {
        newPrintStream.println(message);
    }

    public void printf(Localization.Key key, Object... args) {
        printStream.printf(resourceBundle.getString(key.name()), args);
    }
}
