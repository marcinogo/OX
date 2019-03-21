package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    Board createBoard(int width, int height, Sign defaultSign);
    Field[][] getBoardContent(Board board);
    Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign);
    Boolean isFieldSignEqualsPlayerSign(Board board, Field field, Sign playerSign);
    Board setField(Board board, int widthToUpdate, int heightToUpdate, Sign sign);
}
