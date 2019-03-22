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
    private final Integer winCondition;
    private final Integer numberOfRounds;

    public Settings(Sign defaultSign, Integer winCondition, Integer numberOfRounds) {
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.numberOfRounds = numberOfRounds;
    }

    public Settings(Integer winCondition) {
        this(Sign.DEFAULT, winCondition, 3);
    }

    public Sign getDefaultSign() {
        return defaultSign;
    }

    public Integer getWinCondition() {
        return winCondition;
    }

    public Integer getNumberOfRounds() {
        return numberOfRounds;
    }
}
