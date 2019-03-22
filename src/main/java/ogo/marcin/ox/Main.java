package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.io.Input;
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
            PlayerAPI playerAPI = new PlayerAPIImpl();

            FactoryAPI factoryAPI = new FactoryAPIImpl();

            Input input = new Input(scanner, factoryAPI);
            List<Player> players = new ArrayList<>();
            players.add(factoryAPI.createPlayer("player 1", Sign.X));
            players.add(factoryAPI.createPlayer("player 2", Sign.O));

            Integer winCondition = 3;

            Coordinates boardDimensions = factoryAPI.createCoordinates(3, 3);
            Board board = factoryAPI.createBoard(boardDimensions);
            BoardAPI boardAPI = new BoardAPIImpl(board);

            Settings settings = new Settings(winCondition);

            Game game = new Game(settings, boardAPI, playerAPI, input, players);
            game.play();
        }
    }
}
