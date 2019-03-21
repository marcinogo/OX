package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    Board createBoard(int width, int height, Sign defaultSign);
    Field[][] getBoardContent(Board board);
    Board setField(Board board, int widthToUpdate, int heightToUpdate, Sign sign);
    Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign);
    Boolean isCoordinatesWithinBoard(Board board, int width, int height);
    Boolean isCoordinatesPointsToDefaultSign(Board board, Sign defaultSign, int width, int height);
}
