package ogo.marcin.ox.dimension;

import ogo.marcin.ox.io.Input;

/**
 * @author Marcin Ogorzalek
 */
public abstract class DimensionBuilder<T extends Dimension> {
    protected int xDimension;
    protected int yDimension;

    private Input input;


    public DimensionBuilder(Input input) {
        this.input = input;
    }

    public abstract T build();

    public DimensionBuilder<T> withXDimension(String message) {
        System.out.println(message);
        this.xDimension = input.getIntegerInput();
        return this;
    }

    public DimensionBuilder<T> withYDimension(String message) {
        System.out.println(message);
        this.yDimension = input.getIntegerInput();
        return this;
    }
}
