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
        for (Field[] row: board.matrix) {
            for(Field field: row) {
                if(field.sign.equals(defaultSign)) return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean isCoordinatesWithinBoard(Board board, int width, int height) {
        if(width < 0 || width >= board.width) return Boolean.FALSE;
        if(height < 0 || height >= board.height) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    @Override
    public Boolean isCoordinatesPointsToDefaultSign(Board board, Sign defaultSign, int width, int height) {
        return board.matrix[height][width].sign == defaultSign;
    }
}
