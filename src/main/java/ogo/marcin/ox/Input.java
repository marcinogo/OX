package ogo.marcin.ox;

import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class Input {
    private final Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    String getStringInput() {
        return scanner.nextLine();
    }
}
