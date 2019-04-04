package ogo.marcin.ox.board;

import ogo.marcin.ox.dimension.Dimension;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Marcin Ogorzalek
 */
public class Board {
    private final int width;
    private final int height;

    final Field[][] matrix;

    private Board(Dimension boardDimension) {
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
            if (i < matrix.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    Board setBoardMatrixCells(Sign sign) {
        Board board = new Board(this);
        int count = 0;
        for(int i = 0; i < board.matrix.length; i++) {
            for (int j = 0; j < board.matrix[i].length; j++) {
                count++;
                board.matrix[i][j] = new Field(sign, count);
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
        return !Arrays.deepEquals(matrix, board.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result;
        result += Arrays.deepHashCode(matrix);
        return result;
    }

    int getBoardDimension() {
        return width;
    }

    public static class BoardBuilder {
        private Dimension dimension;

        public Board build() {
            Board board = new Board(dimension);
            return board.setBoardMatrixCells(Sign.DEFAULT);
        }

        public BoardBuilder withDimension(Dimension dimension) {
            this.dimension = dimension;
            return this;
        }
    }
}
