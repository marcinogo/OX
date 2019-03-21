package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;

import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public class Settings {
    private List<Player> players;
    private Sign defaultSign;
    private Integer winCondition;
    private Board board;

    public Settings(List<Player> players, Sign defaultSign, Integer winCondition, Board board) {
        this.players = players;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.board = board;
    }

    public Settings(List<Player> players, Integer winCondition, Board board) {
        this(players, Sign.DEFAULT, winCondition, board);
    }

    public List<Player> getPlayers() {
        return players;
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
}
