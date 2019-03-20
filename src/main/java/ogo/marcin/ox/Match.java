package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;

/**
 * @author Marcin Ogorzalek
 */
class Match {
    Integer winCondition;
    Board board;

    Match(Integer winCondition, Board board) {
        this.winCondition = winCondition;
        this.board = board;
    }
}
