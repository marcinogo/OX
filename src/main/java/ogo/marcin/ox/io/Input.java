package ogo.marcin.ox.io;

import ogo.marcin.ox.dimension.*;
import ogo.marcin.ox.game.Judge;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class Input {
    private final Scanner scanner;
    private final Output output;

    public Input(Scanner scanner, Output output) {
        this.scanner = scanner;
        this.output = output;
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    private int getIntegerInput(Localization.Key localizationKey) {
        int result = 0;
        boolean inputIsCorrect;
        do {
            output.print(localizationKey);
            inputIsCorrect = true;
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                inputIsCorrect = false;
                scanner.next();
            }
        } while (!inputIsCorrect);
        return result;
    }

    public Coordinates getCoordinates(Judge judge, int boardDimension) {
        Coordinates coordinates = null;
        boolean coordinatesWithinBoard;
        do {
            try {
                coordinates = createCoordinates(boardDimension);
                coordinatesWithinBoard = true;
            } catch (IllegalArgumentException e) {
                coordinatesWithinBoard = false;
            }
        } while (!coordinatesWithinBoard ||
                !judge.isPlayerSignSetOnFreeSpace(coordinates));
        return coordinates;
    }

    private Coordinates createCoordinates(int boardDimension) {
        DimensionBuilder<Coordinates> coordinatesDimensionBuilder = new CoordinatesBuilder();
        int dimension = getIntegerInput(Localization.Key.ENTER_COORDINATE);
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
                boardDimension = createBoard();
                dimensionsCreated = true;
            } catch (IllegalArgumentException e) {
                dimensionsCreated = false;
            }
        } while (!dimensionsCreated);
        return boardDimension;
    }

    private BoardDimension createBoard() {
        DimensionBuilder<BoardDimension> boardDimensionDimensionBuilder = new BoardDimensionBuilder();
        int dimension = getIntegerInput(Localization.Key.BOARD_SIZE);
        return boardDimensionDimensionBuilder
                .withDimension(dimension)
                .build();
    }

    public int getWinConditionInRange(int minWinCondition, int maxWinCondition) {
        int winCondition;
        do {
            winCondition = getIntegerInput(Localization.Key.WIN_CONDITION);
        } while (!(winCondition >= minWinCondition || winCondition <= maxWinCondition));
        return winCondition;
    }
}
