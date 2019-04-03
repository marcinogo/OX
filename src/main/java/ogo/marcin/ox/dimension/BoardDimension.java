package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public class BoardDimension extends Dimension{
    protected BoardDimension(int height, int width) {
        super(height, width);
        Coordinates.setCoordinatesMaxXY(width, height);
    }

    BoardDimension(DimensionBuilder dimensionBuilder) {
        this(dimensionBuilder.yDimension, dimensionBuilder.xDimension);
    }

    @Override
    protected boolean validate(int width, int height) {
        return height >= Dimension.MIN_HEIGHT && height <= Dimension.MAX_HEIGHT
                && width >= Dimension.MIN_WIDTH && width <= Dimension.MAX_WIDTH;
    }
}
