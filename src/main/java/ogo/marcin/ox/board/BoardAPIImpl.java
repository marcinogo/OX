package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public class BoardAPIImpl implements BoardAPI {
    private Board board;

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
    public boolean isFreeSpaceOnBoard(Sign defaultSign) {
        for (Field[] row: board.matrix) {
            for(Field field: row) {
                if(field.sign.equals(defaultSign)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCoordinatesWithinBoard(Coordinates coordinates) {
        if(coordinates.getXDimension() < 0 || coordinates.getXDimension() >= board.width) return false;
        if(coordinates.getYDimension() < 0 || coordinates.getYDimension() >= board.height) return false;
        return true;
    }

    @Override
    public boolean isCoordinatesPointsToDefaultSign(Sign defaultSign, Coordinates coordinates) {
        return board.matrix[coordinates.getYDimension()][coordinates.getXDimension()].sign.equals(defaultSign);
    }

    public boolean isSignNumberMeetWinCondition(Sign playerSign, Coordinates coordinates, int winCondition) {
        int x = coordinates.getXDimension();
        int y = coordinates.getYDimension();

        return horizontalCheck(playerSign, x, y, winCondition) ||
                verticalCheck(playerSign, x, y, winCondition) ||
                diagonalCheck(playerSign, x, y, winCondition) ||
                antidiagonalCheck(playerSign, x, y, winCondition);
    }

    private boolean horizontalCheck(Sign playerSign, int x, int y, int winCondition) {
        int count = 1;
        Field[] row = board.matrix[y];
        for(int i = x + 1; i < row.length; i++) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }

        for(int i = x - 1; i >= 0; i--) {
            if(row[i].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }
        return count== winCondition;
    }

    private boolean verticalCheck(Sign playerSign, int x, int y, int winCondition) {
        int count = 1;

        for(int i = y + 1; i < board.matrix.length; i++) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }

        for(int i = y - 1; i >= 0; i--) {
            if(board.matrix[i][x].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }
        return count == winCondition;
    }

    private boolean diagonalCheck(Sign playerSign, int x, int y, int winCondition) {
        int count = 1;

        for(int i = y + 1, j = x + 1; i < board.matrix.length && j < board.matrix[i].length; i++, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }
        return count == winCondition;
    }

    private boolean antidiagonalCheck(Sign playerSign, int x, int y, int winCondition) {
        int count = 1;

        for(int i = y + 1, j = x - 1; i < board.matrix.length && j >= 0; i++, j--) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }

        for(int i = y - 1, j = x + 1; i >= 0 && j < board.matrix.length; i--, j++) {
            if(board.matrix[i][j].sign.equals(playerSign)) {
                count++;
            } else if(count == winCondition){
                return true;
            } else {
                break;
            }
        }
        return count == winCondition;
    }

    @Override
    public Board clearBoard(Sign sing) {
        this.board = board.setBoardMatrixCells(sing);
        return board;
    }
}
