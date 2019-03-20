package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerFactory {
    Player createPlayer(String name, Sign sign) {
        return new Player(name, sign);
    }

    Player createPlayer(String name, Sign sign, Integer points) {
        return new Player(name, sign, points);
    }
}
