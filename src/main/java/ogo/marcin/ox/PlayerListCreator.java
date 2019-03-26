package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;

import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerListCreator {
    Input input;

    public PlayerListCreator(Input input) {
        this.input = input;
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        for(int i = 1; i <= 2; i++) {
            players.add(new Player.PlayerBuilder(input).withName(i)
                    .withSign(i)
                    .withPoints()
                    .build());
        }
        return players;
    }
}
