package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Player {
    String name;
    Sign playerSign;

    Player(String name, Sign playerSign) {
        this.name = name;
        this.playerSign = playerSign;
    }
}
