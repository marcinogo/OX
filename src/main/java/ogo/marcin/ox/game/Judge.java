package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Judge {
    private final BoardAPI boardAPI;
    private final Sign defaultSign;
    private final int winCondition;

    Judge(BoardAPI boardAPI, Sign defaultSign, int winCondition) {
        this.boardAPI = boardAPI;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
    }

    Judge(BoardAPI boardAPI, int winCondition) {
        this(boardAPI, Sign.DEFAULT, winCondition);
    }

    boolean isFreeSpaceOnBoard() {
        return boardAPI.isFreeSpaceOnBoard(defaultSign);
    }

    boolean isPlayerActionWithinBoard(Coordinates coordinates) {
        return boardAPI.isCoordinatesWithinBoard(coordinates);
    }

    boolean isPlayerSignSetOnFreeSpace(Coordinates coordinates) {
        return boardAPI.isCoordinatesPointsToDefaultSign(defaultSign, coordinates);
    }

    boolean isPlayerWon(Sign playerSign, Coordinates coordinates) {
        return boardAPI.isSignNumberMeetWinCondition(playerSign, coordinates, winCondition);
    }
}
