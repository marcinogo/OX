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

        super(dimensionBuilder);
    }

    @Override
    protected boolean validate(int width, int height) {
        return yDimension >= Dimension.MIN_HEIGHT && yDimension <= Dimension.MAX_HEIGHT
                && xDimension >= Dimension.MIN_WIDTH && xDimension <= Dimension.MAX_WIDTH;
    }

    private void setCoordinatesMaxXY() {
        Coordinates.MAX_WIDTH = this.xDimension;
        Coordinates.MAX_HEIGHT = this.yDimension;
    }
}
