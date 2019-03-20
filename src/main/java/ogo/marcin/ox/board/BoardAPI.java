package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardAPI {
    Board createBoard(int width, int height, Sign defaultSign);
    Board setField(Board board, int widthToUpdate, int heightToUpdate, Sign sign);
    Field[][] getBoardContent(Board board);
    Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign);
    Boolean isFieldSignEqualsPlayerSign(Field field, Sign playerSign);
}
