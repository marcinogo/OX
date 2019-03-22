package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerAPIImpl implements PlayerAPI {
    @Override
    public Player setPlayerPoints(Player player, Integer newNumberOfPoints) {
        return player.setPlayerPoints(newNumberOfPoints);
    }

    @Override
    public String getPlayerName(Player player) {
        return player.name;
    }

    @Override
    public Sign getPlayerSign(Player player) {
        return player.playerSign;
    }

    @Override
    public Integer getPlayerPoints(Player player) {
        return player.points;
    }
}
