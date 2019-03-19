package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardFactoryImpl implements BoardFactory {
    public Board createBoard(int width, int height, Sign sign) {
        Board board = new Board(width, height);
        return board.setBoardMatrixCells(sign);
    }
}
