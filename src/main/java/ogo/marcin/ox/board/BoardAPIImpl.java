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
        return board.setField(widthToUpdate, heightToUpdate, sign);
    }

    @Override
    public Field[][] getBoardContent(Board board) {
        return board.matrix;
    }

    @Override
    public Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign) {
        for (int i = 0; i < board.height; i++) {
            for(Field field: board.matrix[i]) {
                if(field.sign.equals(defaultSign)) return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean isFieldSignEqualsPlayerSign(Board board, Field field, Sign playerSign) {
        return field.sign.equals(playerSign);
    }
}
