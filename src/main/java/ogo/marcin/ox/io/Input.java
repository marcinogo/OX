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

    public Coordinates getCoordinates(Judge judge) {
        Coordinates coordinates = null;
        boolean coordinatesWithinBoardAndOnFreeSpace;
        do {
            try {
                coordinates = createCoordinates();
                coordinatesWithinBoardAndOnFreeSpace = isCoordinateOnFreeSpace(judge, coordinates);
            } catch (IllegalArgumentException e) {
                output.print(System.err, e.getMessage());
                coordinatesWithinBoardAndOnFreeSpace = false;
            }
        } while (!coordinatesWithinBoardAndOnFreeSpace);
        return coordinates;
    }

    private Coordinates createCoordinates() {
        int move = getIntegerInput(Localization.LanguageKey.ENTER_COORDINATE);
        return new Coordinates.CoordinatesBuilder()
                .withMovePosition(move)
                .build();
    }

    private int getIntegerInput(Localization.LanguageKey localizationLanguageKey) {
        int result = 0;
        boolean inputIsCorrect;
        do {
            output.print(localizationLanguageKey);
            inputIsCorrect = true;
            try {
                result = scanner.nextInt();
            } catch (InputMismatchException e) {
                output.print(System.err, Localization.getLocalizedText(
                        Localization.LanguageKey.NOT_INTEGER_EXCEPTION
                ));
                inputIsCorrect = false;
                scanner.next();
            }
        } while (!inputIsCorrect);
        return result;
    }

    private boolean isCoordinateOnFreeSpace(Judge judge, Coordinates coordinates) {
        if(!judge.isPlayerSignSetOnFreeSpace(coordinates)) {
            output.print(System.err, Localization.getLocalizedText(
                    Localization.LanguageKey.COORDINATE_NOT_FREE_EXCEPTION
            ));
            return false;
        }
        return true;
    }

    public BoardDimension getBoardDimensions() {
        BoardDimension boardDimension = null;
        boolean dimensionsCreated;
        do {
            try {
                boardDimension = createBoard();
                dimensionsCreated = true;
            } catch (IllegalArgumentException e) {
                output.print(System.err, e.getMessage());
                dimensionsCreated = false;
            }
        } while (!dimensionsCreated);
        return boardDimension;
    }

    private BoardDimension createBoard() {
        int dimension = getIntegerInput(Localization.LanguageKey.BOARD_SIZE);
        return new BoardDimension.BoardDimensionBuilder()
                .withBoardEdgeSize(dimension)
                .build();
    }

    public int getWinConditionInRange(int minWinCondition, int maxWinCondition) {
        return getIntegerInputBetween(minWinCondition, maxWinCondition);
    }

    private int getIntegerInputBetween(int minWinCondition, int maxWinCondition) {
        int winCondition = 0;
        boolean inputIsCorrect;
        do {
            output.printf(Localization.LanguageKey.WIN_CONDITION, minWinCondition, maxWinCondition);
            try {
                winCondition = scanner.nextInt();
                if(!isIntegerBetween(winCondition, minWinCondition, maxWinCondition)) {
                    throw new IllegalArgumentException(Localization.getLocalizedText(
                            Localization.LanguageKey.WIN_CONDITION_EXCEPTION
                    ));
                }
                inputIsCorrect = true;
            } catch (InputMismatchException e) {
                output.print(System.err, Localization.getLocalizedText(
                        Localization.LanguageKey.NOT_INTEGER_EXCEPTION
                ));
                inputIsCorrect = false;
                scanner.next();
            } catch (IllegalArgumentException e) {
                output.print(System.err, e.getMessage());
                inputIsCorrect = false;
            }
        } while (!inputIsCorrect);
        return winCondition;
    }

    private boolean isIntegerBetween(int number, int minNumber, int maxNumber) {
        return number >= minNumber && number <= maxNumber;
    }
}
