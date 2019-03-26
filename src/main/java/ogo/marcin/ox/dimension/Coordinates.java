package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public class Coordinates extends Dimension {
    private static final int MIN_Y_INDEX = 0;
    static int MAX_Y_INDEX;
    private static final int MIN_X_INDEX = 0;
    static int MAX_X_INDEX;


    protected Coordinates(int x, int y) {
        super(x, y);
    }

    Coordinates(DimensionBuilder dimensionBuilder) {
        super(dimensionBuilder);
    }

    @Override
    protected boolean validate(int x, int y) {
        return y >= Coordinates.MIN_Y_INDEX && y < Coordinates.MAX_Y_INDEX
                && x >= Coordinates.MIN_X_INDEX && x < Coordinates.MAX_X_INDEX;
    }
}
