package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public abstract class DimensionBuilder<T extends Dimension> {
    protected int xDimension;
    protected int yDimension;

    public abstract T build();

    public DimensionBuilder<T> withXDimension(int dimension) {
        this.xDimension = dimension;
        return this;
    }

    public DimensionBuilder<T> withYDimension(int dimension) {
        this.yDimension = dimension;
        return this;
    }

    public DimensionBuilder<T> withDimension(int dimension) {
        this.yDimension = dimension;
        this.xDimension = dimension;
        return this;
    }
}
