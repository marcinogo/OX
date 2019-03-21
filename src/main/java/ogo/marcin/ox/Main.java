package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
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
            PlayerAPI playerAPI = new PlayerAPIImpl();

            List<Player> players = new ArrayList<>();
            players.add(playerAPI.createPlayer("player 1", Sign.X));
            players.add(playerAPI.createPlayer("player 2", Sign.O));

            Integer winCondition = 3;

            Coordinates boardDimensions = boardAPI.createCoordinates(3, 3);
            Board board = boardAPI.createBoard(boardDimensions);

            Settings settings = new Settings(players, winCondition, board);

            Match match = new Match(boardAPI, playerAPI, input, settings);
            match.play();
        }
    }
}
