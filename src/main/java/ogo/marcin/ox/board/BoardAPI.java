package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    Board createBoard(Coordinates coordinates, Sign defaultSign);
    Board setField(Board board, Coordinates coordinates, Sign sign);
    Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign);
    Boolean isCoordinatesWithinBoard(Board board, Coordinates coordinates);
    Boolean isCoordinatesPointsToDefaultSign(Board board, Sign defaultSign, Coordinates coordinates);
    Boolean isSignNumberMeetRequirements(Board board, Sign playerSign,
                                         Coordinates coordinates, Integer requiredSignNumber);
}
