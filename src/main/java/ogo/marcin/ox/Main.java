package ogo.marcin.ox;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.BoardAPIImpl;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            Input input = new Input(scanner);
            BoardAPI boardAPI = new BoardAPIImpl();
            Sign defaultSign = Sign.DEFAULT;
            PlayerAPI playerAPI = new PlayerAPIImpl();
            List<Player> players = new ArrayList<>();
            players.add(playerAPI.createPlayer("player 1", Sign.X));
            players.add(playerAPI.createPlayer("player 2", Sign.X));
            Integer winCondition = 3;

            Match match = new Match(boardAPI, input, players, defaultSign, winCondition, playerAPI);
            match.play();
        }
    }
}
