package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardAPIImpl implements BoardAPI {
    @Override
    public Board createBoard(Coordinates coordinates, Sign defaultSign) {
        BoardFactory boardFactory = new BoardFactory();
        return boardFactory.createBoard(coordinates, defaultSign);
    }

    @Override
    public Coordinates createCoordinates(Integer x, Integer y) {
        return new Coordinates(x, y);
    }

    @Override
    public Board setField(Board board, Coordinates coordinates, Sign sign) {
        return board.setField(coordinates, sign);
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
    public Boolean isCoordinatesWithinBoard(Board board, Coordinates coordinates) {
        if(coordinates.x < 0 || coordinates.x >= board.width) return Boolean.FALSE;
        if(coordinates.y < 0 || coordinates.y >= board.height) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    @Override
    public Boolean isCoordinatesPointsToDefaultSign(Board board, Sign defaultSign, Coordinates coordinates) {
        return board.matrix[coordinates.y][coordinates.x].sign.equals(defaultSign);
    }

    public Boolean isSignNumberMeetRequirements(Board board, Sign playerSign,
                                                Coordinates coordinates, Integer requiredSignNumber) {
        int x = coordinates.x;
        int y = coordinates.y;

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
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
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
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
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
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }
        return count.equals(requiredSignNumber);
    }

    private Boolean antidiagonalCheck(Board board, Sign playerSign, int x, int y, Integer requiredSignNumber) {
        Integer count = 1;

        for(int i = y + 1, j = x - 1; i < board.matrix.length && j >= 0; i++, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x + 1; i >= 0 && j < board.matrix.length; i--, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count.equals(requiredSignNumber)){
                return Boolean.TRUE;
            } else {
                break;
            }
        }
        return count.equals(requiredSignNumber);
    }
}
