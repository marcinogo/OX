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

    Boolean isFreeSpaceOnBoard() {
        return boardAPI.isFreeSpaceOnBoard(board, defaultSign);
    }

    Boolean isPlayerActionWithinBoard(int x, int y) {
        return boardAPI.isCoordinatesWithinBoard(board, x, y);
    }

    Boolean isPLayerSignSetOnFreeSpace(int x, int y) {
        return boardAPI.isCoordinatesPointsToDefaultSign(board, defaultSign, x, y);
    }

    Boolean isPlayerWon(Sign playerSign, int x, int y, Integer winCondition) {
        return boardAPI.isSignNumberMeet(board, playerSign, x, y, winCondition);
    }
}
