package ogo.marcin.ox.player;

import java.util.List;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public interface PlayerAPI {

  //    List<Player> createPlayers(Input input, Output output);
  Player setPlayerPoints(Player player, int newNumberOfPoints);

  String getPlayerName(Player player);

  Sign getPlayerSign(Player player);

  int getPlayerPoints(Player player);

  List<Player> getPlayers();

  Player getPlayerOnIndex(int index);

}
