package ogo.marcin.ox.io;

import ogo.marcin.ox.FactoryAPI;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.dimension.Dimension;

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

    public int getIntegerInput() {
        int result = 0;
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

//    change this do more generic
//    public <T extends Dimension> T getCoordinates() {
//        System.out.println("Enter x coordinate");
//        Integer x = getIntegerInput();
//        System.out.println("Enter y coordinate");
//        Integer y = getIntegerInput();
//        Class<T> cls;
//        return cls.newIn;
//    }
}
