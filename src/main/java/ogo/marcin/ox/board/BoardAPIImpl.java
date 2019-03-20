package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardAPIImpl implements BoardAPI {
    @Override
    public Board createBoard(int width, int height, Sign defaultSign) {
        BoardFactory boardFactory = new BoardFactory();
        return boardFactory.createBoard(width, height, defaultSign);
    }

    @Override
    public Board setField(Board board, int widthToUpdate, int heightToUpdate, Sign sign) {
        Field field = new Field(sign);
        return board.setField(widthToUpdate, heightToUpdate, field);
    }

    @Override
    public Field[][] getBoardContent(Board board) {
        return board.matrix;
    }
}
