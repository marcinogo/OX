package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;

import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
public class Player implements Comparable<Player>{
    final String name;
    final Sign playerSign;
    int points;

    Player(String name, Sign playerSign) {
        this(name, playerSign, 0);
    }

    Player(String name, Sign playerSign, int points) {
        this.name = name;
        this.playerSign = playerSign;
        this.points = points;
    }

    private Player(PlayerBuilder playerBuilder) {
        this(playerBuilder.name, playerBuilder.playerSign, playerBuilder.points);
    }

    private Player(Player player) {
        this(player.name, player.playerSign, player.points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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

    static class PlayerBuilder {
        static final List<Sign> unusedSigns = new LinkedList<>(Arrays.asList(Sign.values()));

        private String name;
        private Sign playerSign;
        private final int points = 0;

        PlayerBuilder() {
            unusedSigns.remove(Sign.DEFAULT);
        }

        Player build() {
            return new Player(this);
        }

        PlayerBuilder withName(String name) throws IllegalArgumentException{
            if(!PlayerValidation.validateName(name)) {
                throw new IllegalArgumentException("You have to enter player name");
            }
            this.name = name;
            return this;
        }

        PlayerBuilder withSign(String signString) throws IllegalArgumentException {
            if(!PlayerValidation.validatePlayerSignString(signString)) {
                throw new IllegalArgumentException("This is not proper sign, choose X or O");
            }
            this.playerSign = Sign.valueOf(signString);
            unusedSigns.remove(this.playerSign);
            return this;
        }
    }

    private static class PlayerValidation {
        private static boolean validateName(String name) {
            return name != null && !name.isBlank() && !name.isEmpty();
        }

        private static boolean validatePlayerSignString(String signString) {
            try {
                Sign.valueOf(signString);
            } catch (IllegalArgumentException e) {
                return false;
            }
            return true;
        }
    }
}
