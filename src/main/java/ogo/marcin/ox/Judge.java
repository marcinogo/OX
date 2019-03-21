package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Judge {
    private BoardAPI boardAPI;
    private Sign defaultSign;

    Judge(BoardAPI boardAPI, Sign defaultSign) {
        this.boardAPI = boardAPI;
        this.defaultSign = defaultSign;
    }

    Boolean isFreeSpaceOnBoard(Board board) {
        return boardAPI.isFreeSpaceOnBoard(board, defaultSign);
    }

    Boolean isPlayerActionWithinBoard(Board board, int x, int y) {
        return boardAPI.isCoordinatesWithinBoard(board, x, y);
    }

    Boolean isPlayerSignSetOnFreeSpace(Board board, int x, int y) {
        return boardAPI.isCoordinatesPointsToDefaultSign(board, defaultSign, x, y);
    }

    Boolean isPlayerWon(Board board, Sign playerSign, int x, int y, Integer winCondition) {
        return boardAPI.isSignNumberMeet(board, playerSign, x, y, winCondition);
    }
}
