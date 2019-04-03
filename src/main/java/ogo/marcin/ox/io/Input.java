package ogo.marcin.ox.io;

import ogo.marcin.ox.dimension.Coordinates;
import ogo.marcin.ox.dimension.CoordinatesBuilder;
import ogo.marcin.ox.dimension.DimensionBuilder;

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

    public int getIntegerInput() {
        int result = 0;
        boolean inputIsCorrect;
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

    public Coordinates getCoordinates(int boardDimension) {
        DimensionBuilder<Coordinates> coordinatesDimensionBuilder = new CoordinatesBuilder();
        int dimension = getIntegerInput();
        return coordinatesDimensionBuilder.withXDimension(recalculateUserInputToX(boardDimension, dimension))
                .withYDimension(recalculateUserInputToY(boardDimension, dimension))
                .build();
    }

    private int recalculateUserInputToX(int boardDimension, int dimension) {
        return (dimension - 1) % boardDimension;
    }

    private int recalculateUserInputToY(int boardDimension, int dimension) {
        return (dimension - 1) / boardDimension;
    }
}
