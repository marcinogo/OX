package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    void setField(Dimension coordinates, Sign sign);
    boolean isFreeSpaceOnBoard(Sign defaultSign);
    boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Dimension coordinates);
    boolean isSignNumberMeetWinCondition(Sign playerSign, Dimension coordinates, int winCondition);

    Board getBoard();
    void clearBoard(Sign sign);
    int getBoardDimension();
}
