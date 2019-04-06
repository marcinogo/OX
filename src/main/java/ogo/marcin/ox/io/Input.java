package ogo.marcin.ox.io;

import ogo.marcin.ox.board.BoardDimension;
import ogo.marcin.ox.game.Coordinates;
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

    public Coordinates getCoordinates(Judge judge) {
        Coordinates coordinates = null;
        boolean coordinatesWithinBoard;
        do {
            try {
                coordinates = createCoordinates();
                coordinatesWithinBoard = true;
            } catch (IllegalArgumentException e) {
//                TODO change to output one
//                TODO different serr for coordinate on non default field
                System.err.println(e.getMessage());
                coordinatesWithinBoard = false;
            }
        } while (!coordinatesWithinBoard ||
                !judge.isPlayerSignSetOnFreeSpace(coordinates));
        return coordinates;
    }

    private Coordinates createCoordinates() {
        int move = getIntegerInput(Localization.Key.ENTER_COORDINATE);
        return new Coordinates.CoordinatesBuilder()
                .withMovePosition(move)
                .build();
    }


    public BoardDimension getBoardDimensions() {
        BoardDimension boardDimension = null;
        boolean dimensionsCreated;
        do {
            try {
                boardDimension = createBoard();
                dimensionsCreated = true;
            } catch (IllegalArgumentException e) {
//                TODO change to output one
                System.err.println(e.getMessage());
                dimensionsCreated = false;
            }
        } while (!dimensionsCreated);
        return boardDimension;
    }

    private BoardDimension createBoard() {
        int dimension = getIntegerInput(Localization.Key.BOARD_SIZE);
        return new BoardDimension.BoardDimensionBuilder()
                .withBoardEdgeSize(dimension)
                .build();
    }

    public int getWinConditionInRange(int minWinCondition, int maxWinCondition) {
        int winCondition;
        do {
            winCondition = getIntegerInput(Localization.Key.WIN_CONDITION);
//            TODO move validation to method add print of wrong length of this
        } while (!(winCondition >= minWinCondition && winCondition <= maxWinCondition));
        return winCondition;
    }
}
