package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.dimension.Dimension;

/**
 * @author Marcin Ogorzalek
 */
public class Judge {
    private final BoardAPI boardAPI;
    private final Settings settings;

    Judge(BoardAPI boardAPI, Settings settings) {
        this.boardAPI = boardAPI;
        this.settings = settings;
    }

    boolean isFreeSpaceOnBoard() {
        return boardAPI.isFreeSpaceOnBoard();
    }

    public boolean isPlayerSignSetOnFreeSpace(Dimension coordinates) {
        return boardAPI.isCoordinatesPointsToDefaultSign(settings.getDefaultSign(), coordinates);
    }

    boolean isPlayerWon(Sign playerSign, Dimension coordinates) {
        return boardAPI.whetherWinningConditionHasBeenMet(playerSign, coordinates, settings.getWinCondition());
    }
}
