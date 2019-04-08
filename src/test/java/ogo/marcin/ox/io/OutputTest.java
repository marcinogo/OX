package ogo.marcin.ox.io;


import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.eq;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class OutputTest {
    public void testPrintWithStingParam() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        Output output = new Output(printStream);
        output.print(Mockito.anyString());
        Mockito.verify(printStream).println(Mockito.anyString());
    }

    public void testPrintWithLocalizationKeyParam() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        Output output = new Output(printStream);
        output.print(Localization.LanguageKey.BOARD_SIZE);
        Mockito.verify(printStream).println(Localization.getLocalizedText(Localization.LanguageKey.BOARD_SIZE));
    }

    public void testPrintWithNewPrintStreamParam() {
        PrintStream printStream = Mockito.mock(PrintStream.class);
        PrintStream newPrintStream = Mockito.mock(PrintStream.class);
        Output output = new Output(printStream);
        output.print(newPrintStream, Mockito.anyString());
        Mockito.verify(newPrintStream).println(Mockito.anyString());
    }
}