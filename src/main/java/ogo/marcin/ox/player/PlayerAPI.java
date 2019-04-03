package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;

import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public interface PlayerAPI {
    List<Player> createPlayers(Input input);
    Player setPlayerPoints(Player player, int newNumberOfPoints);
    String getPlayerName(Player player);
    Sign getPlayerSign(Player player);
    int getPlayerPoints(Player player);
}
