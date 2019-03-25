package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;

/**
 * @author Marcin Ogorzalek
 */
public class BoardFactory{
    public Board createBoard(Dimension boardDimension, Sign sign) {
        Board board = new Board(boardDimension);
        return board.setBoardMatrixCells(sign);
    }
}
