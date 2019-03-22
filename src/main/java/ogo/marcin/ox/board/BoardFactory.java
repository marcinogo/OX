package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardFactory{
    public Board createBoard(Coordinates coordinates, Sign sign) {
        Board board = new Board(coordinates);
        return board.setBoardMatrixCells(sign);
    }
}
