package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class Board {
    int width;
    int height;

    Field[][] matrix;

    Board(int width, int height) {
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
        return width >=3 && width <= 40
                && height >=3  && height <= 40;
    }

    Board setField(int widthToUpdate, int heightToUpdate, Field field) {
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

    Board setBoardMatrixCells(Sign sign) {
        Board board = new Board(this);
        for (int i = 0; i < board.matrix.length; i++) {
            for(int j = 0; j < board.matrix[i].length; j++) {
                board.matrix[i][j] = new Field(sign);
            }
        }
        return board;
    }
}