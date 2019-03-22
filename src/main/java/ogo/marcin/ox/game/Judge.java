package ogo.marcin.ox.game;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Judge {
    private final BoardAPI boardAPI;
    private final Sign defaultSign;
    private final Integer winCondition;

    private Board board;

    Judge(BoardAPI boardAPI, Sign defaultSign, Integer winCondition, Board board) {
        this.boardAPI = boardAPI;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.board = board;
    }

    Judge(BoardAPI boardAPI, Integer winCondition, Board board) {
        this(boardAPI, Sign.DEFAULT, winCondition, board);
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
        return boardAPI.isSignNumberMeetWinCondition(board, playerSign, coordinates, winCondition);
    }
}
