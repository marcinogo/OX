package ogo.marcin.ox.io;

import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.board.Coordinates;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class Input {
    private final Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public Integer getIntegerInput() {
        Integer result = null;
        boolean inputIsCorrect = true;
        do {
            inputIsCorrect = true;
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                inputIsCorrect = false;
                scanner.next();
//                TODO message that it accepts only Integers
            }
        } while (!inputIsCorrect);
        return result;
    }

    public Coordinates getCoordinates() {
        System.out.println("Enter x coordinate");
        Integer x = getIntegerInput();
        System.out.println("Enter y coordinate");
        Integer y = getIntegerInput();
        return new BoardAPIImpl().createCoordinates(x, y);
    }
}
