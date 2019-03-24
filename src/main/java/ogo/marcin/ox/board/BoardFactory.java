package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.BoardDimension;

/**
 * @author Marcin Ogorzalek
 */
public class BoardFactory{
    public Board createBoard(BoardDimension boardDimension, Sign sign) {
        Board board = new Board(boardDimension);
        return board.setBoardMatrixCells(sign);
    }
}
