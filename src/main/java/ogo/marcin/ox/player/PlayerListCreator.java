package ogo.marcin.ox.player;

import ogo.marcin.ox.io.Input;

import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
class PlayerListCreator {
    private final Input input;

    PlayerListCreator(Input input) {
        this.input = input;
    }

    List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        for(int i = 1; i <= 2; i++) {
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
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                playerCreated = false;
            }
        } while(!playerCreated);
        return player;
    }

    private String getPlayerName(int playerNumber) {
        System.out.printf("Give player %d name%n", playerNumber);
        return input.getStringInput();
    }

    private String getPlayerSignString(int playerNumber) {
        if(Player.PlayerBuilder.unusedSigns.size() == 1) {
            String playerSignString = Player.PlayerBuilder.unusedSigns.get(0).name();
            System.out.printf("Player %d get sign %s%n", playerNumber, playerSignString);
            return playerSignString;
        }
        System.out.printf("Give player %d sign - X or O%n", playerNumber);
        return input.getStringInput().toUpperCase();
    }
}
