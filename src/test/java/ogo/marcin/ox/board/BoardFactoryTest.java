package ogo.marcin.ox.board;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
public class BoardFactoryTest {

    @DataProvider
    public static Object[][] createBoard(){
        return new Object[][] {
                {3, 3, Sign.DEFAULT},
                {3, 5, Sign.O},
                {5, 5, Sign.X},
                {9, 7, Sign.X},
                {4, 4, Sign.DEFAULT},
                {40, 36, Sign.X},
                {40, 40, Sign.O},
                {39, 3, Sign.DEFAULT},
                {3, 39, Sign.DEFAULT},
                {3, 40, Sign.X},
                {40, 3, Sign.O},
                {20, 20, Sign.DEFAULT},
        };
    }

    @Test(dataProvider = "createBoard")
    public void testCreateBoardDontReturnNull(int width, int height, Sign sign) {
        BoardFactory boardFactory = new BoardFactory();
        Coordinates coordinates = new Coordinates(width, height);
        Board board = boardFactory.createBoard(coordinates, sign);
        assert board != null : "Board should not be null";
    }

    @Test(dataProvider = "createBoard")
    public void testCreateBoard(int width, int height, Sign sign) {
        BoardFactory boardFactory = new BoardFactory();
        Coordinates coordinates = new Coordinates(width, height);
        Board board = boardFactory.createBoard(coordinates, sign);
        Board expectedBoard = new Board(coordinates).setBoardMatrixCells(sign);
        assert board.equals(expectedBoard) : "Board should be equals";
    }
}