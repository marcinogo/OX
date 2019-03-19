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
            {1, 1},
            {3, 3},
            {3, 5},
            {5, 5},
            {9, 7},
            {4, 4},
        };
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveDimensions(int width, int height) {
        Board board = new Board(width, height);
        assert board.width == width : String.format("Width should be %d", width);
        assert board.height == height : String.format("Height should be %d", height);
    }
}
