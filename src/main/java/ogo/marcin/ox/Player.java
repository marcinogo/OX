package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
class Player {
    final String name;
    final Sign playerSign;

    Player(String name, Sign playerSign) {
        if(!validateName(name)) throw new IllegalArgumentException("Player must have name");
        this.name = name;
        this.playerSign = playerSign;
    }

    private boolean validateName(String name) {
        return name != null && !name.equals("");
    }
}
