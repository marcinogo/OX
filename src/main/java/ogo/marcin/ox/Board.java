package ogo.marcin.ox;

/**
 * @author Marcin Ogorzalek
 */
public class Board {
    int width;
    int height;

    Field[][] matrix;

    public Board(int width, int height) {
        if(!validateDimensions(width, height)) {
            throw new IllegalArgumentException("Width and height have to be at least 3");
        }
        this.width = width;
        this.height = height;

        this.matrix = new Field[height][width];
    }

    private boolean validateDimensions(int width, int height) {
        return width >=3 && width <= 200
                && height >=3  && height <= 200;
    }
}
