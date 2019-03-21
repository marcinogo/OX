package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardAPIImpl implements BoardAPI {
    @Override
    public Board createBoard(int width, int height, Sign defaultSign) {
        BoardFactory boardFactory = new BoardFactory();
        return boardFactory.createBoard(width, height, defaultSign);
    }

    @Override
    public Board setField(Board board, int x, int y, Sign sign) {
        return board.setField(x, y, sign);
    }

    @Override
    public Field[][] getBoardContent(Board board) {
        return board.matrix;
    }

    @Override
    public Boolean isFreeSpaceOnBoard(Board board, Sign defaultSign) {
        for (Field[] row: board.matrix) {
            for(Field field: row) {
                if(field.sign.equals(defaultSign)) return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean isCoordinatesWithinBoard(Board board, int x, int y) {
        if(x < 0 || x >= board.width) return Boolean.FALSE;
        if(y < 0 || y >= board.height) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    @Override
    public Boolean isCoordinatesPointsToDefaultSign(Board board, Sign defaultSign, int x, int y) {
        return board.matrix[x][y].sign == defaultSign;
    }

    public Boolean isSignNumberMeet(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber) {
        return horizontalCheck(board, playerSign, x, y, requiredSignNumber) ||
                verticalCheck(board, playerSign, x, y, requiredSignNumber) ||
                diagonalCheck(board, playerSign, x, y, requiredSignNumber) ||
                antidiagonalCheck(board, playerSign, x, y, requiredSignNumber);
    }

    private Boolean horizontalCheck(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber) {
        Integer count = 1;
        Field[] row = board.matrix[y];
        for(int i = x + 1; i < row.length; i++) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }
        return count.equals(requiredSignNumber);
    }

    private Boolean verticalCheck(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber) {
        Integer count = 1;

        for(int i = y + 1; i < board.matrix.length; i++) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }
        return count.equals(requiredSignNumber);
    }

    private Boolean diagonalCheck(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber) {
        Integer count = 1;

        for(int i = y + 1, j = x + 1; i < board.matrix.length && j < board.matrix[i].length; i++, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }
        return count.equals(requiredSignNumber);
    }

    private Boolean antidiagonalCheck(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber) {
        Integer count = 1;

        for(int i = y + 1, j = x - 1; i < board.matrix.length && j < board.matrix[i].length; i++, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x + 1; i >= 0 && j >= 0; i--, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else {
                break;
            }
        }
        return count.equals(requiredSignNumber);
    }
}
