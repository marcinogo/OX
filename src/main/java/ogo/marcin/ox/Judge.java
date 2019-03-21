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

    Boolean isPlayerActionWithinBoard(int width, int height) {
        return boardAPI.isCoordinatesWithinBoard(board, width, height);
    }

    Boolean isPLayerSignSetOnFreeSpace(int width, int height) {
        return boardAPI.isCoordinatesPointsToDefaultSign(board, defaultSign, width, height);
    }
}
