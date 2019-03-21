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
    private Board board;

    Judge(BoardAPI boardAPI, Sign defaultSign, Integer winCondition, Board board) {
        this.boardAPI = boardAPI;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    Boolean isFreeSpaceOnBoard() {
        return boardAPI.isFreeSpaceOnBoard(board, defaultSign);
    }

    Boolean isPlayerActionWithinBoard(Coordinates coordinates) {
        return boardAPI.isCoordinatesWithinBoard(board, coordinates);
    }

    Boolean isPlayerSignSetOnFreeSpace(Coordinates coordinates) {
        return boardAPI.isCoordinatesPointsToDefaultSign(board, defaultSign, coordinates);
    }

    Boolean isPlayerWon(Sign playerSign, Coordinates coordinates) {
        return boardAPI.isSignNumberMeetRequirements(board, playerSign, coordinates, winCondition);
    }
}
