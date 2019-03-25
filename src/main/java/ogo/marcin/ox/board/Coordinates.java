package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;
import ogo.marcin.ox.dimension.DimensionBuilder;

/**
 * @author Marcin Ogorzalek
 */
public class Coordinates extends Dimension {
    public static int MAX_HEIGHT;
    public static int MAX_WIDTH;

    public Coordinates(int x, int y) {
        super(x, y);
    }

    public Coordinates(DimensionBuilder dimensionBuilder) {
        super(dimensionBuilder);
    }

    @Override
    protected boolean validate(int x, int y) {
        return y >= Dimension.MIN_HEIGHT && y <= Coordinates.MAX_HEIGHT
                && x >= Dimension.MIN_WIDTH && x <= Coordinates.MAX_WIDTH;
    }
}
