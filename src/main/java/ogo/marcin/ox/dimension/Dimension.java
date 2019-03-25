package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public abstract class Dimension {
    protected static final int MIN_HEIGHT = 3;
    protected static final int MAX_HEIGHT = 40;
    protected static final int MIN_WIDTH = 3;
    protected static final int MAX_WIDTH = 40;

    protected final int xDimension;
    protected final int yDimension;

    public Dimension(int xDimension, int yDimension) {
//        TODO: do something with different x-y in message
        if(validate(xDimension, yDimension)) throw new IllegalArgumentException();
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    protected Dimension(DimensionBuilder dimensionBuilder) {
        this(dimensionBuilder.xDimension, dimensionBuilder.yDimension);
    }

    public int getXDimension() {
        return xDimension;
    }

    public int getYDimension() {
        return yDimension;
    }

    protected abstract boolean validate(int width, int height);

//    TODO: impelemnt Builder pattern here and do builder interface chage this pseudo factories to builder
}
