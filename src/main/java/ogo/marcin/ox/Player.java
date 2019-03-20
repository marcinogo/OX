package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;

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
}
