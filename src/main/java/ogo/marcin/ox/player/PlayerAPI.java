package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public interface PlayerAPI {
    Player setPlayerPoints(Player player, Integer newNumberOfPoints);
    String getPlayerName(Player player);
    Sign getPlayerSign(Player player);
    Integer getPlayerPoints(Player player);
}
