package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    void setField(Coordinates coordinates, Sign sign);
    Boolean isFreeSpaceOnBoard(Sign defaultSign);
    Boolean isCoordinatesWithinBoard(Coordinates coordinates);
    Boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates);
    Boolean isSignNumberMeetWinCondition(Sign playerSign, Coordinates coordinates, Integer winCondition);

    Board getBoard();
    void setBoard(Board board);
}
