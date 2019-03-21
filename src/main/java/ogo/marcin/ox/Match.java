package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Match {
    Integer winCondition;
    BoardAPI boardAPI;
    Board board;

    Match(Integer winCondition, BoardAPI boardAPI, Board board) {
        this.winCondition = winCondition;
        this.boardAPI = boardAPI;
        this.board = board;
    }

    Boolean checkFreeSpaceOnBoard(Sign deafualSign) {
        return boardAPI.isFreeSpaceOnBoard(board, deafualSign);
    }
}
