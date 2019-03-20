package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.board.Sign;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class MatchTest {
    public void testIfMachHaveWinningCondition() {
        BoardAPI boardAPI = new BoardAPIImpl();
        Match match = new Match(3, boardAPI.createBoard(3, 3, Sign.DEFAULT));
        assert match.winCondition == 3 : "Win condition should be equal 3";
    }

    public void testIfMachHaveBoard() {
        BoardAPI boardAPI = new BoardAPIImpl();
        Board board = boardAPI.createBoard(3, 3, Sign.DEFAULT);
        Match match = new Match(3, board);
        assert match.board ==  board: "Match should have board";
    }
}
