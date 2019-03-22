package ogo.marcin.ox.io;

import ogo.marcin.ox.FactoryAPI;
import ogo.marcin.ox.board.Coordinates;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class Input {
    private final Scanner scanner;
    private final FactoryAPI factoryAPI;

    public Input(Scanner scanner, FactoryAPI factoryAPI) {
        this.scanner = scanner;
        this.factoryAPI = factoryAPI;
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
        return factoryAPI.createCoordinates(x, y);
    }
}
