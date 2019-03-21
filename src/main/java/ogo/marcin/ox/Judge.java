package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Judge {
    private BoardAPI boardAPI;
    private Sign defaultSign;
    private Integer winCondition;

    public Judge(BoardAPI boardAPI, Sign defaultSign, Integer winCondition) {
        this.boardAPI = boardAPI;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
    }

    Boolean isFreeSpaceOnBoard(Board board) {
        return boardAPI.isFreeSpaceOnBoard(board, defaultSign);
    }

    Boolean isPlayerActionWithinBoard(Board board, Coordinates coordinates) {
        return boardAPI.isCoordinatesWithinBoard(board, coordinates);
    }

    Boolean isPlayerSignSetOnFreeSpace(Board board, Coordinates coordinates) {
        return boardAPI.isCoordinatesPointsToDefaultSign(board, defaultSign, coordinates);
    }

    Boolean isPlayerWon(Board board, Sign playerSign, Coordinates coordinates) {
        return boardAPI.isSignNumberMeetRequirements(board, playerSign, coordinates, winCondition);
    }
}
