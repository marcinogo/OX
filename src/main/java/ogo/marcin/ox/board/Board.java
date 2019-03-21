package ogo.marcin.ox.board;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
public class Board {
    int width;
    int height;

    Field[][] matrix;

    Board(Coordinates coordinates) {
        if(!validateDimensions(coordinates.x, coordinates.y)) {
            throw new IllegalArgumentException("Width and height have to be at least 3");
        }
        this.width = coordinates.x;
        this.height = coordinates.y;
        this.matrix = new Field[height][width];
    }

    private Board(Board board) {
        this.width = board.width;
        this.height = board.height;
        this.matrix = new Field[board.height][];
        copyMatrixContent(this.matrix, board.matrix);
    }

    private void copyMatrixContent(Field[][] newMatrix, Field[][] oldMatrix) {
        for(int i = 0; i < oldMatrix.length; i++) {
            newMatrix[i] = oldMatrix[i].clone();
        }
    }

    private boolean validateDimensions(int width, int height) {
        return width >=3 && width <= 40
                && height >=3  && height <= 40;
    }

    Board setField(Coordinates coordinates, Sign sign) {
        Board newBoard = new Board(this);
        Field fieldToChange = newBoard.matrix[coordinates.y][coordinates.x];
        newBoard.matrix[coordinates.y][coordinates.x] = fieldToChange.changeSign(sign);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        if(width != board.width || height != board.height) return false;

        for (int i = 0; i < height; i++) {
            if(!Arrays.equals(matrix[i], board.matrix[i])) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result;
        for (int i = 0; i < height; i++) {
            result += Arrays.hashCode(matrix[i]);
        }
        return result;
    }
}
