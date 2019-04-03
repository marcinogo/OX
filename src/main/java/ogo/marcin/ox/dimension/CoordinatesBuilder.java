package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public class CoordinatesBuilder extends DimensionBuilder<Coordinates> {
    @Override
    public Coordinates build() {
        return new Coordinates(this);
    }


}
