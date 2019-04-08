package ogo.marcin.ox.board;

import ogo.marcin.ox.game.Coordinates;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class BoardTest {
    @DataProvider
    public static Object[][] createBoard() {
        return new Object[][]{
                {3},
                {5},
                {5},
                {7},
                {4},
                {30},
                {3},
                {30},
                {30},
                {30},
                {4},
        };
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveDimensions(int edge) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        assert board.getBoardEdge() == edge : String.format("Width should be %d", edge);
    }

    @DataProvider
    public static Object[][] tryCreateImproperBoardDimensions() {
        return new Object[][]{
                {0},
                {1},
                {0},
                {-1},
                {-3},
                {Integer.MAX_VALUE},
                {Integer.MIN_VALUE},
                {201},
                {201},
                {1000},
                {1000},
                {Integer.MAX_VALUE},
                {Integer.MIN_VALUE},
                {155},
                {200},
                {199},
                {200},
                {100},
        };
    }

    @Test(dataProvider = "tryCreateImproperBoardDimensions", expectedExceptions = BoardSizeException.class)
    public void testIfBoardThrowExceptionWhenCreateWithWrongDimensions(int edge) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        assert board.getBoardEdge() == edge : String.format("Width should be %d", edge);
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveMatrixOfGivenHeight(int edge) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        assert board.matrix.length == edge : String.format("Matrix should have height of %d", edge);
    }

    @Test(dataProvider = "createBoard")
    public void testIfBoardHaveMatrixOfGivenWidth(int edge) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        assert board.matrix[0].length == edge : String.format("Matrix should have Width of %d", edge);
    }

    @DataProvider
    public static Object[][] setFieldValue() {
        return new Object[][]{
                {3, 1, Sign.DEFAULT},
                {5, 4, Sign.O},
                {5, 4, Sign.X},
                {7, 3, Sign.X},
                {4, 3, Sign.DEFAULT},
                {15, 10, Sign.X},
                {30, 15, Sign.O},
                {3, 1, Sign.DEFAULT},
                {29, 17, Sign.DEFAULT},
                {30, 20, Sign.X},
                {3, 1, Sign.O},
                {20, 12, Sign.DEFAULT},
        };
    }

    @Test(dataProvider = "setFieldValue")
    public void testIfBoardSetFieldValueInMatrix(int edge, int move, Sign sign) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        board = board.setBoardMatrixCells(Sign.DEFAULT);
        Coordinates coordinatesToUpdate = new Coordinates.CoordinatesBuilder().withMovePosition(move).build();
        Board updateBoard = board.setField(coordinatesToUpdate, sign);
        Field expected = new Field(sign);
        assert updateBoard.matrix[coordinatesToUpdate.getYOfMove()][coordinatesToUpdate.getXOfMove()]
                .equals(expected) : String.format("Field should " +
                "have value %s", expected);
    }

    @DataProvider
    public static Object[][] setAllBoardValue() {
        return new Object[][]{
                {3, Sign.DEFAULT},
                {5, Sign.O},
                {5, Sign.X},
                {7, Sign.X},
                {4, Sign.DEFAULT},
                {30, Sign.X},
                {20, Sign.O},
                {3, Sign.DEFAULT},
                {30, Sign.DEFAULT},
                {30, Sign.X},
                {30, Sign.O},
                {20, Sign.DEFAULT},
        };
    }

    @Test(dataProvider = "setAllBoardValue")
    public void testSetAllBoardFields(int edge, Sign sign) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        board = board.setBoardMatrixCells(sign);
        Field[] expectedContent = new Field[edge];
        Arrays.fill(expectedContent, new Field(sign));

        for (Field[] matrixRow : board.matrix) {
            assert Arrays.equals(expectedContent, matrixRow) : "Rows are not equals";
        }
    }

    @Test(dataProvider = "setAllBoardValue")
    public void testIfToStringReturnCorrectString(int edge, Sign sign) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board = new Board(boardDimension);
        board = board.setBoardMatrixCells(sign);
        String expected = createBoardExpectedStringRepresentation(board.getBoardEdge(), sign);
        assert board.toString().equals(expected) : String.format("Wrong representation " +
                "of board, get %s", board.toString());
    }

    private String createBoardExpectedStringRepresentation(int edge, Sign sign) {
        StringBuilder expected = new StringBuilder();
        int count = 0;
        for (int i = 0; i < edge; i++) {
            for (int j = 0; j < edge; j++) {
                expected.append(new Field(sign, ++count).toString());
            }
            if (i < edge - 1) {
                expected.append(System.lineSeparator());
            }
        }
        return expected.toString();
    }

    @Test(dataProvider = "createBoard")
    public void testEquals(int edge) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board1 = new Board(boardDimension);
        Board board2 = new Board(boardDimension);
        assert board1.equals(board2) : "Boards should be equals";
    }

    @Test(dataProvider = "createBoard")
    public void testHashCoed(int edge) {
        BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(edge).build();
        Board board1 = new Board(boardDimension);
        Board board2 = new Board(boardDimension);
        assert board1.hashCode() == board2.hashCode() : "HashCodes should be equals";
    }
}
