package ogo.marcin.ox.board;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class BoardAPIImplTest {
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
        BoardAPI boardAPI = new BoardAPIImpl();
        Coordinates coordinates = new Coordinates(width, height);
        Board board = boardAPI.createBoard(coordinates, sign);
        assert board != null : "Board should not be null";
    }

    @Test(dataProvider = "createBoard")
    public void testCreateBoard(int width, int height, Sign sign) {
        BoardAPI boardAPI = new BoardAPIImpl();
        Coordinates coordinates = new Coordinates(width, height);
        Board board = boardAPI.createBoard(coordinates, sign);
        Board expectedBoard = new Board(coordinates).setBoardMatrixCells(sign);
        assert board.equals(expectedBoard) : "Board should be equals";
    }

    @DataProvider
    public static Object[][] setFieldValue(){
        return new Object[][] {
                {3, 3, 1, 1 , Sign.DEFAULT, Sign.O},
                {3, 3, 1, 1 , Sign.DEFAULT, Sign.X},
                {3, 5, 2, 4, Sign.O, Sign.X},
                {3, 5, 2, 4, Sign.O, Sign.DEFAULT},
                {4, 4, 2, 3, Sign.DEFAULT, Sign.X},
                {4, 4, 2, 3, Sign.DEFAULT, Sign.O},
                {40, 36, 35, 25, Sign.X, Sign.O},
                {40, 36, 35, 25, Sign.X, Sign.DEFAULT},
                {40, 40, 39, 39, Sign.O, Sign.X},
                {40, 40, 39, 39, Sign.O, Sign.DEFAULT},
        };
    }

    @Test(dataProvider = "setFieldValue")
    public void testSetField(int width, int height, int widthToUpdate,
                             int heightToUpdate, Sign defaultSign, Sign newSign) {
        BoardAPI boardAPI = new BoardAPIImpl();
        Coordinates coordinates = new Coordinates(width, height);
        Board defaultBoard = new Board(coordinates).setBoardMatrixCells(defaultSign);
        Coordinates coordinatesToUpdate = new Coordinates(widthToUpdate, heightToUpdate);
        Board result = boardAPI.setField(defaultBoard, coordinatesToUpdate, newSign);
        defaultBoard.matrix[heightToUpdate][widthToUpdate] = new Field(newSign);
        assert defaultBoard.equals(result) : "Boards should be equals";
    }
}