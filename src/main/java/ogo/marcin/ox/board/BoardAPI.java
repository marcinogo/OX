package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    Board createBoard(int width, int height, Sign defaultSign);
    Field[][] getBoardContent(Board board);
    Board setField(Board board, int x, int y, Sign sign);
    Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign);
    Boolean isCoordinatesWithinBoard(Board board, int x, int y);
    Boolean isCoordinatesPointsToDefaultSign(Board board, Sign defaultSign, int x, int y);
    Boolean isSignNumberMeet(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber);
}
