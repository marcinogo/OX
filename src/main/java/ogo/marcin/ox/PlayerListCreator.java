package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;

import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerListCreator {
    FactoryAPI factoryAPI;
    Input input;

    public PlayerListCreator(FactoryAPI factoryAPI, Input input) {
        this.factoryAPI = factoryAPI;
        this.input = input;
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        Set<Sign> usedSigns = new HashSet<>();
        usedSigns.add(Sign.DEFAULT);
        for(int i = 1; i <= 2; i++) {
            players.add(factoryAPI.createPlayer(withName(i, input), withSign(i, input, usedSigns)));
        }

        return players;
    }

    private String withName(int i, Input input) {
        System.out.printf("Give player %d name%n", i);
        return input.getStringInput();
    }

    private Sign withSign(int i, Input input, Set<Sign> usedSigns) {
        String signString;
        Optional<Sign> playerSign;
        do {
            System.out.printf("Give player %d sign - X or O%n", i);
            signString = input.getStringInput().toUpperCase();
            playerSign = getPlayerSign(signString, usedSigns);
        } while (playerSign.isEmpty());
        return playerSign.get();
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
