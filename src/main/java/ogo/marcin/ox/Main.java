package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public class Main {
    public static void main(String[] args) {
        BoardAPI boardAPI = new BoardAPIImpl();
        Board board = boardAPI.createBoard(3, 3, Sign.DEFAULT);
        System.out.println(board);
        board = boardAPI.setField(board, 1, 1, Sign.X);
        System.out.println();
        System.out.println(board);
    }
}
