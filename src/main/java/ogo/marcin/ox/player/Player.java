package ogo.marcin.ox.player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Localization;

/**
 * @author Marcin Ogorzalek
 */
public class Player implements Comparable<Player> {

  final String name;
  final Sign playerSign;
  int points;

  Player(String name, Sign playerSign, int points) {
    this.name = name;
    this.playerSign = playerSign;
    this.points = points;
  }

  Player(String name, Sign playerSign) {
    this(name, playerSign, 0);
  }

  private Player(PlayerBuilder playerBuilder) {
    this(playerBuilder.name, playerBuilder.playerSign, playerBuilder.points);
  }

  private Player(Player player) {
    this(player.name, player.playerSign, player.points);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return points == player.points &&
        Objects.equals(name, player.name) &&
        playerSign == player.playerSign;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, playerSign, points);
  }

  Player setPlayerPoints(int newNumberOfPoints) {
    Player player = new Player(this);
    player.points = newNumberOfPoints;
    return player;
  }

  @Override
  public String toString() {
    return String.format("%s with sign %s have: %d points",
        name, playerSign, points);
  }

  @Override
  public int compareTo(Player o) {
    return this.points - o.points;
  }

  public static class PlayerBuilder {

    static final List<Sign> unusedSigns = new LinkedList<>(Arrays.asList(Sign.values()));
    private final int points;
    private String name;
    private Sign playerSign;

    public PlayerBuilder() {
      this.points = 0;
      unusedSigns.remove(Sign.DEFAULT);
    }

    public Player build() {
      return new Player(this);
    }

    public PlayerBuilder withName(String name) throws PlayerNameException {
      if (!PlayerDataValidation.validateName(name)) {
        throw new PlayerNameException(Localization.getLocalizedText(
            Localization.LanguageKey.PLAYER_NAME_EXCEPTION));
      }
      this.name = name;
      return this;
    }

    public PlayerBuilder withSign(String signString) throws PlayerSignException {
      if (signString == null || !PlayerDataValidation.validateSignString(signString)) {
        throw new PlayerSignException(Localization.getLocalizedText(
            Localization.LanguageKey.PLAYER_SIGN_EXCEPTION));
      }
      this.playerSign = Sign.valueOf(signString);
      unusedSigns.remove(this.playerSign);
      return this;
    }
  }

  private static class PlayerDataValidation {

    private static boolean validateName(String name) {
      return name != null && !name.isBlank() && !name.isEmpty();
    }

    private static boolean validateSignString(String signString) {
      try {
        Sign.valueOf(signString);
      } catch (IllegalArgumentException e) {
        return false;
      }
      return true;
    }
  }
}
