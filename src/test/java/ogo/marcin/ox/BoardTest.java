package ogo.marcin.ox;

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
}
