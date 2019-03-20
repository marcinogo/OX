package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;

import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
class Player {
    final String name;
    final Sign playerSign;
    Integer points;

    Player(String name, Sign playerSign) {
        this(name, playerSign, 0);
    }

    Player(String name, Sign playerSign, Integer points) {
        validatePlayerData(name, playerSign, points);
        this.name = name;
        this.playerSign = playerSign;
        this.points = points;
    }

    private void validatePlayerData(String name, Sign sign, Integer points) {
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

    private boolean validatePoints(Integer points) {
        return points >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                playerSign == player.playerSign &&
                Objects.equals(points, player.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, playerSign, points);
    }
}
