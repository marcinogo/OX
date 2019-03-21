package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Judge {
    private BoardAPI boardAPI;
    private Board board;
    private Sign defaultSign;

    Judge(BoardAPI boardAPI, Board board, Sign defaultSign) {
        this.boardAPI = boardAPI;
        this.board = board;
        this.defaultSign = defaultSign;
    }

    Boolean checkFreeSpaceOnBoard() {
        return boardAPI.isFreeSpaceOnBoard(board, defaultSign);
    }
}
