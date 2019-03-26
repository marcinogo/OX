package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
public class Board {
    final int width;
    final int height;

    Field[][] matrix;

    Board(Dimension boardDimension) {
        if(!validateDimensions(boardDimension.getXDimension(), boardDimension.getYDimension())) {
            throw new IllegalArgumentException("Width and height have to be at least 3 and no more than 40");
        }
        this.width = boardDimension.getXDimension();
        this.height = boardDimension.getYDimension();
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

    Board setField(Dimension coordinates, Sign sign) {
        Board newBoard = new Board(this);
        Field fieldToChange = newBoard.matrix[coordinates.getYDimension()][coordinates.getXDimension()];
        newBoard.matrix[coordinates.getYDimension()][coordinates.getXDimension()] = fieldToChange.changeSign(sign);
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
        for (Field[] row: board.matrix) {
            Arrays.fill(row, new Field(sign));
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
