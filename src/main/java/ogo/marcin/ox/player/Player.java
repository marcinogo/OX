package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;

import java.util.Objects;

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
        validatePlayerData(name, playerSign, points);
        this.name = name;
        this.playerSign = playerSign;
        this.points = points;
    }

    private Player(Player player) {
        this(player.name, player.playerSign, player.points);
    }

    private void validatePlayerData(String name, Sign sign, int points) {
        if(!validateName(name)) throw new IllegalArgumentException("Player must have name");
        if(!validateSign(sign)) throw new IllegalArgumentException("Player must have not default sign");
        if(!validatePoints(points)) throw new IllegalArgumentException("Player can not have points below 0");
    }

    private boolean validateName(String name) {
        return name != null && !name.equals("");
    }

    private boolean validateSign(Sign sign) {
        return sign != null && !sign.equals(Sign.DEFAULT);
    }

    private boolean validatePoints(int points) {
        return points >= 0;
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

//    In extending class change string parts to localized version from ResourceBundle
    @Override
    public String toString() {
        return String.format("%s with sign %s have: %d points", name, playerSign, points);
    }

    @Override
    public int compareTo(Player o) {
        return this.points - o.points;
    }
}
