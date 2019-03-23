package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;

import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public class Settings {
    private final Sign defaultSign;
    private final int winCondition;
    private final int numberOfRounds;

    public Settings(Sign defaultSign, int winCondition, int numberOfRounds) {
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.numberOfRounds = numberOfRounds;
    }

    public Settings(int winCondition) {
        this(Sign.DEFAULT, winCondition, 3);
    }

    public Sign getDefaultSign() {
        return defaultSign;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }
}
