package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;
import ogo.marcin.ox.dimension.DimensionBuilder;

/**
 * @author Marcin Ogorzalek
 */
public class Coordinates extends Dimension {
    public static int MIN_Y_INDEX = 0;
    public static int MAX_Y_INDEX;
    public static int MIN_X_INDEX = 0;
    public static int MAX_X_INDEX;


    public Coordinates(int x, int y) {
        super(x, y);
    }

    public Coordinates(DimensionBuilder dimensionBuilder) {
        super(dimensionBuilder);
    }

    @Override
    protected boolean validate(int x, int y) {
        return y >= Coordinates.MIN_Y_INDEX && y < Coordinates.MAX_Y_INDEX
                && x >= Coordinates.MIN_X_INDEX && x < Coordinates.MAX_X_INDEX;
    }
}
