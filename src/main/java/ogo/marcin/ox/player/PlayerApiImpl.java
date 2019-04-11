package ogo.marcin.ox.player;

import java.util.List;
import ogo.marcin.ox.board.Sign;

/**
 * Contain method to manipulate Player state
 *
 * @author Marcin Ogorzalek
 */
public class PlayerApiImpl implements PlayerApi {

  private final List<Player> players;

  public PlayerApiImpl(List<Player> players) {
    this.players = players;
  }

  @Override
  public Player setPlayerPoints(Player player, int newNumberOfPoints) {
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
  public int getPlayerPoints(Player player) {
    return player.points;
  }

  /**
   * Return of players in game
   *
   * @return list of players in game
   */
  @Override
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Return position of player in players list
   *
   * @return index of player
   */
  @Override
  public Player getPlayerOnIndex(int index) {
    return players.get(index);
  }
}
