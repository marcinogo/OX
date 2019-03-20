package ogo.marcin.ox.board;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Field;
import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

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
            {40, 40},
            {40, 3},
            {3, 40},
            {25, 40},
            {4, 40},
            {40, 4},
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
                {100, 155},
                {200, 200},
                {199, 3},
                {3, 199},
                {3, 200},
                {200, 3},
                {100, 100},
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
                {40, 36, 35, 25, new Field(Sign.X)},
                {40, 40, 39, 39, new Field(Sign.O)},
                {39, 3, 17, 1, new Field(Sign.DEFAULT)},
                {3, 39, 1, 17, new Field(Sign.DEFAULT)},
                {3, 40, 1, 20, new Field(Sign.X)},
                {40, 3, 20, 1, new Field(Sign.O)},
                {20, 20, 12, 12, new Field(Sign.DEFAULT)},
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
                {40, 40, Sign.X},
                {20, 20, Sign.O},
                {40, 3, Sign.DEFAULT},
                {3, 40, Sign.DEFAULT},
                {4, 40, Sign.X},
                {4, 40, Sign.O},
                {20, 20, Sign.DEFAULT},
        };
    }

    @Test(dataProvider = "setAllBoardValue")
    public void testSetAllBoardFields(int width, int height, Sign sign) {
        Board board = new Board(width, height);
        board = board.setBoardMatrixCells(sign);
        Field[] expectedContent = new Field[width];
        Arrays.fill(expectedContent, new Field(sign));

        for (Field[] matrixRow: board.matrix) {
            assert Arrays.equals(expectedContent, matrixRow) : "Rows are not equals";
        }
    }

    @Test(dataProvider = "setAllBoardValue")
    public void testIfToStringReturnCorrectString(int width, int height, Sign sign) {
        Board board = new Board(width, height);
        board = board.setBoardMatrixCells(sign);
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

    @Test(dataProvider = "createBoard")
    public void testEquals(int width, int height) {
        Board board1 = new Board(width, height);
        Board board2 = new Board(width, height);
        assert board1.equals(board2) : "Boards should be equals";
    }

    @Test(dataProvider = "createBoard")
    public void testHashCoed(int width, int height) {
        Board board1 = new Board(width, height);
        Board board2 = new Board(width, height);
        assert board1.hashCode() == board2.hashCode() : "HashCodes should be equals";
    }
}
