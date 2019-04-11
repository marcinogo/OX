package ogo.marcin.ox.player;

import java.util.List;
import ogo.marcin.ox.board.Sign;

/**
 * Contain method to manipulate Player state
 *
 * @author Marcin Ogorzalek
 */
public interface PlayerApi {

  Player setPlayerPoints(Player player, int newNumberOfPoints);

  String getPlayerName(Player player);

  Sign getPlayerSign(Player player);

  int getPlayerPoints(Player player);

  /**
   * Return of players in game
   *
   * @return list of players in game
   */
  List<Player> getPlayers();

  /**
   * Return position of player in players list
   *
   * @return index of player
   */
  Player getPlayerOnIndex(int index);

}
