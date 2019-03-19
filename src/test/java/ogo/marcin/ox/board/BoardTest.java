package ogo.marcin.ox.board;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Field;
import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class BoardTest {
    @DataProvider
    public static Object[][] createBoard(){
        return new Object[][] {
            {3, 3},
            {3, 5},
            {5, 5},
            {9, 7},
            {4, 4},
            {100, 155},
            {200, 200},
            {199, 3},
            {3, 199},
            {3, 200},
            {200, 3},
            {100, 100},
        };
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveDimensions(int width, int height) {
        Board board = new Board(width, height);
        assert board.width == width : String.format("Width should be %d", width);
        assert board.height == height : String.format("Height should be %d", height);
    }

    @DataProvider
    public static Object[][] tryCreateImproperBoardDimensions(){
        return new Object[][] {
                {0, 0},
                {1, 1},
                {1, 0},
                {-1, -1},
                {-3, -3},
                {-3, 3},
                {Integer.MIN_VALUE, Integer.MAX_VALUE},
                {Integer.MIN_VALUE, Integer.MIN_VALUE},
                {201, 201},
                {201, 3},
                {3, 201},
                {1000, 1000},
                {1000, 10},
                {10, 1000},
                {Integer.MAX_VALUE, Integer.MAX_VALUE},
                {3, Integer.MIN_VALUE},
        };
    }

    @Test(dataProvider = "tryCreateImproperBoardDimensions", expectedExceptions = IllegalArgumentException.class)
    public void testIfBoardThrowExceptionWhenCreateWithWrongDimensions(int width, int height) {
        Board board = new Board(width, height);
        assert board.width == width : String.format("Width should be %d", width);
        assert board.height == height : String.format("Height should be %d", height);
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveMatrixOfGivenHeight(int width, int height) {
        Board board = new Board(width, height);
        assert board.matrix.length == height : String.format("Matrix should have height of %d", height);
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveMatrixOfGivenWidth(int width, int height) {
        Board board = new Board(width, height);
        assert board.matrix[0].length == width : String.format("Matrix should have Width of %d", width);
    }

    @DataProvider
    public static Object[][] setFieldValue(){
        return new Object[][] {
                {3, 3, 1, 1 , new Field(Sign.DEFAULT)},
                {3, 5, 2, 4, new Field(Sign.O)},
                {5, 5, 4, 4, new Field(Sign.X)},
                {9, 7, 0, 0, new Field(Sign.X)},
                {4, 4, 2, 3, new Field(Sign.DEFAULT)},
                {100, 155, 99, 25, new Field(Sign.X)},
                {200, 200, 199, 199, new Field(Sign.O)},
                {199, 3, 170, 1, new Field(Sign.DEFAULT)},
                {3, 199, 1, 170, new Field(Sign.DEFAULT)},
                {3, 200, 1, 100, new Field(Sign.X)},
                {200, 3, 100, 1, new Field(Sign.O)},
                {100, 100, 50, 50, new Field(Sign.DEFAULT)},
        };
    }

    @Test(dataProvider = "setFieldValue")
    public void testIfBoardSetFieldValueInMatrix(int width, int height,
                                                 int widthToUpdate, int heightToUpdate,
                                                 Field field) {
        Board board = new Board(width, height);
        Board updateBoard = board.setField(widthToUpdate, heightToUpdate, field);
        assert updateBoard.matrix[heightToUpdate][widthToUpdate] == field : String.format("Field should " +
                "have value \"%s\"", field);
    }

    @DataProvider
    public static Object[][] setAllBoardValue(){
        return new Object[][] {
                {3, 3, Sign.DEFAULT},
                {3, 5, Sign.O},
                {5, 5, Sign.X},
                {9, 7, Sign.X},
                {4, 4, Sign.DEFAULT},
                {100, 155, Sign.X},
                {200, 200, Sign.O},
                {199, 3, Sign.DEFAULT},
                {3, 199, Sign.DEFAULT},
                {3, 200, Sign.X},
                {200, 3, Sign.O},
                {100, 100, Sign.DEFAULT},
        };
    }

    @Test(dataProvider = "setAllBoardValue")
    public void testIfToStringReturnCorrectString(int width, int height, Sign sign) {
        Board board = new Board(width, height);
        board = setBoardMatrixCellsToValue(board, sign);
        String expected = createBoardExpectedStringRepresentation(board.width, board.height, sign);
        assert board.toString().equals(expected) : String.format("Wrong representation " +
                "of board, get %s", board.toString());
    }

    private String createBoardExpectedStringRepresentation(int width, int height, Sign sign) {
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                expected.append(new Field(sign).toString());
            }
            if(i < height - 1) {
                expected.append(System.lineSeparator());
            }
        }
        return expected.toString();
    }

    private Board setBoardMatrixCellsToValue(Board board, Sign sign) {
        for (int i = 0; i < board.matrix.length; i++) {
            for(int j = 0; j < board.matrix[i].length; j++) {
                board.matrix[i][j] = new Field(sign);
            }
        }
        return board;
    }
}
