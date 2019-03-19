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

    private Board(Board board) {
        this(board.width, board.height);
    }

    private boolean validateDimensions(int width, int height) {
        return width >=3 && width <= 200
                && height >=3  && height <= 200;
    }

    public Board setField(int widthToUpdate, int heightToUpdate, Field field) {
        Board newBoard = new Board(this);
        newBoard.matrix[heightToUpdate][widthToUpdate] = field;
        return newBoard;
    }
}
