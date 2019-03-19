package ogo.marcin.ox.board;

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
        this.width = board.width;
        this.height = board.height;
        this.matrix = board.matrix;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                stringBuilder.append(matrix[i][j]);
            }
            if(i < matrix.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
