package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    void setField(Coordinates coordinates, Sign sign);
    boolean isFreeSpaceOnBoard(Sign defaultSign);
    boolean isCoordinatesWithinBoard(Coordinates coordinates);
    boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates);
    boolean isSignNumberMeetWinCondition(Sign playerSign, Coordinates coordinates, int winCondition);

    Board getBoard();
    void setBoard(Board board);

    Board clearBoard(Sign sign);
}
