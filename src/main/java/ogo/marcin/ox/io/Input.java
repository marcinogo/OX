package ogo.marcin.ox.io;

import ogo.marcin.ox.dimension.*;

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

    private int getIntegerInput() {
        int result = 0;
        boolean inputIsCorrect;
        do {
            inputIsCorrect = true;
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                inputIsCorrect = false;
                scanner.next();
                System.out.println("Please enter only number");
            }
        } while (!inputIsCorrect);
        return result;
    }

    public Coordinates createCoordinates(int boardDimension) {
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

    public BoardDimension getBoardDimensions() {
        BoardDimension boardDimension = null;
        boolean dimensionsCreated;
        do {
            try {
                System.out.println("Enter width and height");
                boardDimension = createBoard();
                dimensionsCreated = true;
            } catch (IllegalArgumentException e) {
                dimensionsCreated = false;
                System.out.println("Board dimensions must be between 3 and 30");
            }
        } while (!dimensionsCreated);
        return boardDimension;
    }

    private BoardDimension createBoard() {
        DimensionBuilder<BoardDimension> boardDimensionDimensionBuilder = new BoardDimensionBuilder();
        int dimension = getIntegerInput();
        return boardDimensionDimensionBuilder
                .withDimension(dimension)
                .build();
    }

    public int getWinCondition(int boardDimension) {
        int winCondition;
        do {
            winCondition = getIntegerInput();
        } while (winCondition < 3 || winCondition > boardDimension);
        return winCondition;
    }
}
