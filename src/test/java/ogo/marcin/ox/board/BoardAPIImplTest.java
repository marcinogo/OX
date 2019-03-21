package ogo.marcin.ox.board;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;

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
        Board board = boardAPI.createBoard(width, height, sign);
        assert board != null : "Board should not be null";
    }

    @Test(dataProvider = "createBoard")
    public void testCreateBoard(int width, int height, Sign sign) {
        BoardAPI boardAPI = new BoardAPIImpl();
        Board board = boardAPI.createBoard(width, height, sign);
        Board expectedBoard = new Board(width, height).setBoardMatrixCells(sign);
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
        Board defaultBoard = new Board(width, height).setBoardMatrixCells(defaultSign);
        Board result = boardAPI.setField(defaultBoard, widthToUpdate, heightToUpdate, newSign);
        defaultBoard.matrix[heightToUpdate][widthToUpdate] = new Field(newSign);
        assert defaultBoard.equals(result) : "Boards should be equals";
    }

    @Test(dataProvider = "setFieldValue")
    public void testGetBoardContent(int width, int height, int widthToUpdate,
                             int heightToUpdate, Sign defaultSign, Sign newSign) {
        BoardAPI boardAPI = new BoardAPIImpl();
        Board defaultBoard = new Board(width, height).setBoardMatrixCells(defaultSign);
        Field[][] result = boardAPI.getBoardContent(defaultBoard);
        assert defaultBoard.matrix.length == result.length : "Boards matixes length should be equals";
        for(int i = 0; i < result.length; i++) {
            assert Arrays.equals(defaultBoard.matrix[i], result[i]) :"Boards matixes should be equals";
        }
    }
}