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
        assert board.matrix.length == height : String.format("Matrix should have length of %d", height);
    }
}
