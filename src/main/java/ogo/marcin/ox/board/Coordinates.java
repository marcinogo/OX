package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;

/**
 * @author Marcin Ogorzalek
 */
public class Coordinates extends Dimension {
    public static int MAX_HEIGHT;
    public static int MAX_WIDTH;

    public Coordinates(int x, int y) {
        super(x, y);
    }

    @Override
    protected boolean validate(int x, int y) {
        return y >= MIN_HEIGHT && y <= Coordinates.MAX_HEIGHT
                && x >= MIN_WIDTH && x <= Coordinates.MAX_WIDTH;
    }
}
