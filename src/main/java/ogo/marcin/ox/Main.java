package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;

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

        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player1 = playerAPI.createPlayer("Player 1", Sign.X);
        Player player2 = playerAPI.createPlayer("Player 2", Sign.O, 10);

        System.out.println(player1);
        System.out.println(player2);

        player1 = playerAPI.setPlayerPoints(player1, 3);
        System.out.println(player1);
    }
}
