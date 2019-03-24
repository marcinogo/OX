package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public abstract class Dimension {
    protected static final int MIN_BOARD_HEIGHT = 3;
    protected static final int MAX_BOARD_HEIGHT = 40;
    protected static final int MIN_BOARD_WIDTH = 3;
    protected static final int MAX_BOARD_WIDTH = 40;

    final int xDimension;
    final int yDimension;

    public Dimension(int xDimension, int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    //    protected abstract boolean validate(int x, int y);

    public abstract Dimension build();
}
