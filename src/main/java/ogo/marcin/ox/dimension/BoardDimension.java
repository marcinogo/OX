package ogo.marcin.ox.dimension;

/**
 * @author Marcin Ogorzalek
 */
public class BoardDimension extends Dimension{
    final int height;
    final int width;

    public BoardDimension(int height, int width) {
        if(!validate(height, width)) throw new IllegalArgumentException("Board dimensions should be " +
                "between 3 and 40 height and width");
        this.height = height;
        this.width = width;
    }

    @Override
    protected boolean validate(int x, int y) {
        return y >= MAX_HEIGHT && y <= MAX_HEIGHT && x >= MIN_WIDTH && x <= MAX_WIDTH;
    }
}
