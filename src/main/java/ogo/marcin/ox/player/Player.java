package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    Player(PlayerBuilder playerBuilder) {
        this(playerBuilder.name, playerBuilder.playerSign, playerBuilder.points);
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

    public static class PlayerBuilder {
        public static Set<Sign> usedSigns;
        private Input input;

        private String name;
        private Sign playerSign;
        private int points;

        public PlayerBuilder(Input input) {
            this.input = input;
            usedSigns = new HashSet<>();
            usedSigns.add(Sign.DEFAULT);
        }

        public Player build() {
            return new Player(this);
        }

        public PlayerBuilder withName(int i) {
            System.out.printf("Give player %d name%n", i);
            this.name = input.getStringInput();
            return this;
        }

        public PlayerBuilder withSign(int i) {
            String signString;
            Optional<Sign> playerSign;
            do {
                System.out.printf("Give player %d sign - X or O%n", i);
                signString = input.getStringInput().toUpperCase();
                playerSign = getPlayerSign(signString, usedSigns);
            } while (playerSign.isEmpty());
            this.playerSign = playerSign.get();
            return this;
        }

        public PlayerBuilder withPoints() {
            this.points = 0;
            return this;
        }

        public PlayerBuilder withPoints(int i, boolean noDefault) {
            if(!noDefault) return withPoints();
            int points = 0;
            do {
                System.out.printf("Give player %d starting points (more than 0)%n", i);
                points = input.getIntegerInput();
            } while (points <= 0);
            this.points = points;
            return this;
        }

        private Optional<Sign> getPlayerSign(String signString, Set<Sign> usedSigns) {
            Sign playerSign = null;
            if(validatePlayerSignString(signString, usedSigns))  {
                playerSign = Sign.valueOf(signString);
                usedSigns.add(playerSign);
            }
            return Optional.ofNullable(playerSign);
        }

        private boolean validatePlayerSignString(String signString, Set<Sign> usedSigns) {
            try {
                Sign player = Sign.valueOf(signString);
                return isSignNotUsed(player, usedSigns);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        private boolean isSignNotUsed(Sign playerSign, Set<Sign> usedSigns) {
            return !usedSigns.contains(playerSign);
        }
    }
}
