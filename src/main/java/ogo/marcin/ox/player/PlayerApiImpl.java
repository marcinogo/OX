package ogo.marcin.ox.player;

import java.util.List;
import ogo.marcin.ox.board.Sign;

/**
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

  @Override
  public List<Player> getPlayers() {
    return players;
  }

  @Override
  public Player getPlayerOnIndex(int index) {
    return players.get(index);
  }
}