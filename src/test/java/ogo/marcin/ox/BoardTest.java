package ogo.marcin.ox;

import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class BoardTest {
    public void testIfBoardHaveDimensions() {
        Board board = new Board(1, 1);
        assert board.width == 1 : "Width should be 1";
        assert board.height == 1 : "Height should be 1";
    }
}
