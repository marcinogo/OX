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
        if(!validateName(name)) throw new IllegalArgumentException("Player must have name");
        if(!validateSign(playerSign)) throw new IllegalArgumentException("Player must have not default sign");

        this.name = name;
        this.playerSign = playerSign;
        this.points = 0;
    }

    private boolean validateName(String name) {
        return name != null && !name.equals("");
    }

    private boolean validateSign(Sign sign) {
        return sign != null && !sign.equals(Sign.DEFAULT);
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
}
