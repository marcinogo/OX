package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;

import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public class Settings {
    private Sign defaultSign;
    private Integer winCondition;
    private Board board;
    private Integer numberOfRounds;

    public Settings(Sign defaultSign, Integer winCondition, Board board, Integer numberOfRounds) {
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.board = board;
        this.numberOfRounds = numberOfRounds;
    }

    public Settings(Integer winCondition, Board board) {
        this(Sign.DEFAULT, winCondition, board, 3);
    }

    public Sign getDefaultSign() {
        return defaultSign;
    }

    public Integer getWinCondition() {
        return winCondition;
    }

    public Board getBoard() {
        return board;
    }

    public Integer getNumberOfRounds() {
        return numberOfRounds;
    }
}
