package ogo.marcin.ox.player;

import java.util.ArrayList;
import java.util.List;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerListCreator {

  private final Input input;
  private final Output output;

  public PlayerListCreator(Input input, Output output) {
    this.input = input;
    this.output = output;
  }

  public List<Player> createPlayers() {
    List<Player> players = new ArrayList<>();
    for (int i = 1; i <= 2; i++) {
      players.add(createPlayer(i));
    }
    return players;
  }

  private Player createPlayer(int playerNumber) {
    Player player = null;
    boolean playerCreated;
    do {
      try {
        player = new Player.PlayerBuilder()
            .withName(getPlayerName(playerNumber))
            .withSign(getPlayerSignString(playerNumber))
            .build();
        playerCreated = true;
      } catch (PlayerNameException | PlayerSignException e) {
        output.print(System.err, e.getMessage());
        playerCreated = false;
      }
    } while (!playerCreated);
    return player;
  }

  private String getPlayerName(int playerNumber) {
    output.printf(Localization.LanguageKey.GET_PLAYER_NAME, playerNumber);
    return input.getStringInput();
  }

  private String getPlayerSignString(int playerNumber) {
    if (Player.PlayerBuilder.unusedSigns.size() == 1) {
      String playerSignString = Player.PlayerBuilder.unusedSigns.get(0).name();
      output
          .printf(Localization.LanguageKey.GET_SECOND_PLAYER_SIGN, playerNumber, playerSignString);
      return playerSignString;
    }
    output.printf(Localization.LanguageKey.GET_PLAYER_SIGN, playerNumber);
    return input.getStringInput().toUpperCase();
  }
}
