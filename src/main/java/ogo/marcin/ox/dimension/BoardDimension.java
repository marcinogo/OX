package ogo.marcin.ox.dimension;

import ogo.marcin.ox.board.Coordinates;

/**
 * @author Marcin Ogorzalek
 */
public class BoardDimension extends Dimension{
    public BoardDimension(int height, int width) {
        super(height, width);
        setCoordinatesMaxXY();
    }

    public BoardDimension(DimensionBuilder dimensionBuilder) {
        this(dimensionBuilder.yDimension, dimensionBuilder.xDimension);
    }

    @Override
    protected boolean validate(int width, int height) {
        return height >= Dimension.MIN_HEIGHT && height <= Dimension.MAX_HEIGHT
                && width >= Dimension.MIN_WIDTH && width <= Dimension.MAX_WIDTH;
    }

    private void setCoordinatesMaxXY() {
        Coordinates.MAX_X_INDEX = this.xDimension;
        Coordinates.MAX_Y_INDEX = this.yDimension;
    }
}
