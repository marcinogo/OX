package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public class BoardDimensionBuilder extends DimensionBuilder<BoardDimension> {
    @Override
    public BoardDimension build() {
        return new BoardDimension(this);
    }
}
