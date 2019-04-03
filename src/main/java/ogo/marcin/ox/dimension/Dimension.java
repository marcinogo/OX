package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public abstract class Dimension {
    static final int MIN_HEIGHT = 3;
    static final int MAX_HEIGHT = 30;
    static final int MIN_WIDTH = 3;
    static final int MAX_WIDTH = 30;

    private final int xDimension;
    private final int yDimension;

    Dimension(int xDimension, int yDimension) {
//        TODO: do something with different x-y in message
        if (!validate(xDimension, yDimension)) {
            throw new IllegalArgumentException();
        }
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    Dimension(DimensionBuilder dimensionBuilder) {
        this(dimensionBuilder.xDimension, dimensionBuilder.yDimension);
    }

    public int getXDimension() {
        return xDimension;
    }

    public int getYDimension() {
        return yDimension;
    }

    protected abstract boolean validate(int width, int height);
}
