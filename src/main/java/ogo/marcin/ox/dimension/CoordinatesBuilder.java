package ogo.marcin.ox.dimension;

import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.io.Input;

/**
 * @author Marcin Ogorzalek
 */
public class CoordinatesBuilder extends DimensionBuilder<Coordinates>{
    public CoordinatesBuilder(Input input) {
        super(input);
    }

    @Override
    public Coordinates build() {
        return new Coordinates(this);
    }
}
