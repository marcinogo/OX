package ogo.marcin.ox.dimension;

import ogo.marcin.ox.io.Input;

/**
 * @author Marcin Ogorzalek
 */
public class BoardDimensionBuilder extends DimensionBuilder<BoardDimension> {

    public BoardDimensionBuilder(Input input) {
        super(input);
    }

    @Override
    public BoardDimension build() {
        return new BoardDimension(this);
    }
}
