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

    Judge(BoardAPI boardAPI, Sign defaultSign, Integer winCondition) {
        this.boardAPI = boardAPI;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
    }

    Judge(BoardAPI boardAPI, Integer winCondition) {
        this(boardAPI, Sign.DEFAULT, winCondition);
    }

    Boolean isFreeSpaceOnBoard() {
        return boardAPI.isFreeSpaceOnBoard(defaultSign);
    }

    Boolean isPlayerActionWithinBoard(Coordinates coordinates) {
        return boardAPI.isCoordinatesWithinBoard(coordinates);
    }

    Boolean isPlayerSignSetOnFreeSpace(Coordinates coordinates) {
        return boardAPI.isCoordinatesPointsToDefaultSign(defaultSign, coordinates);
    }

    Boolean isPlayerWon(Sign playerSign, Coordinates coordinates) {
        return boardAPI.isSignNumberMeetWinCondition(playerSign, coordinates, winCondition);
    }
}
