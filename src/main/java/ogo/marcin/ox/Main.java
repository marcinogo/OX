package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardFactory;
import ogo.marcin.ox.board.BoardFactoryImpl;
import ogo.marcin.ox.board.Sign;

/**
 * @author Marcin Ogorzalek
 */
public class Main {
    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactoryImpl();
        Board board = boardFactory.createBoard(3, 3, Sign.DEFAULT);
        System.out.println(board);
    }
}
