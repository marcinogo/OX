package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardAPIImpl implements BoardAPI {
    Board board;

    public BoardAPIImpl(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setField(Coordinates coordinates, Sign sign) {
        board = board.setField(coordinates, sign);
    }

    @Override
    public Boolean isFreeSpaceOnBoard(Sign defaultSign) {
        for (Field[] row: board.matrix) {
            for(Field field: row) {
                if(field.sign.equals(defaultSign)) return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean isCoordinatesWithinBoard(Coordinates coordinates) {
        if(coordinates.x < 0 || coordinates.x >= board.width) return Boolean.FALSE;
        if(coordinates.y < 0 || coordinates.y >= board.height) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    @Override
    public Boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates) {
        return board.matrix[coordinates.y][coordinates.x].sign.equals(defaultSign);
    }

    public Boolean isSignNumberMeetWinCondition(Sign playerSign, Coordinates coordinates, Integer winCondition) {
        int x = coordinates.x;
        int y = coordinates.y;

        return horizontalCheck(playerSign, x, y, winCondition) ||
                verticalCheck(playerSign, x, y, winCondition) ||
                diagonalCheck(playerSign, x, y, winCondition) ||
                antidiagonalCheck(playerSign, x, y, winCondition);
    }

    private Boolean horizontalCheck(Sign playerSign, int x, int y, Integer winCondition) {
        Integer count = 1;
        Field[] row = board.matrix[y];
        for(int i = x + 1; i < row.length; i++) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }
        return count.equals(winCondition);
    }

    private Boolean verticalCheck(Sign playerSign, int x, int y, Integer winCondition) {
        Integer count = 1;

        for(int i = y + 1; i < board.matrix.length; i++) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }
        return count.equals(winCondition);
    }

    private Boolean diagonalCheck(Sign playerSign, int x, int y, Integer winCondition) {
        Integer count = 1;

        for(int i = y + 1, j = x + 1; i < board.matrix.length && j < board.matrix[i].length; i++, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }
        return count.equals(winCondition);
    }

    private Boolean antidiagonalCheck(Sign playerSign, int x, int y, Integer winCondition) {
        Integer count = 1;

        for(int i = y + 1, j = x - 1; i < board.matrix.length && j >= 0; i++, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x + 1; i >= 0 && j < board.matrix.length; i--, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(winCondition)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }
        return count.equals(winCondition);
    }
}
