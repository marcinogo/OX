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

    @Override
    protected boolean validate(int width, int height) {
        return yDimension >= MIN_HEIGHT && yDimension <= MAX_HEIGHT
                && xDimension >= MIN_WIDTH && xDimension <=MAX_WIDTH;
    }

    private void setCoordinatesMaxXY() {
        Coordinates.MAX_WIDTH = this.xDimension;
        Coordinates.MAX_HEIGHT = this.yDimension;
    }
}
