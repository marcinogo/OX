package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;

import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            PlayerAPI playerAPI = new PlayerAPIImpl();

            FactoryAPI factoryAPI = new FactoryAPIImpl();

            Input input = new Input(scanner, factoryAPI);
            List<Player> players = new PlayerListCreator(factoryAPI, input).createPlayers();

            Board board = factoryAPI.createBoard(getBoardDimensions(input));
            BoardAPI boardAPI = new BoardAPIImpl(board);

            Settings settings = new Settings.SettingsBuilder(input)
                    .withWinCondition()
                    .withNumberOfRounds()
                    .withDefaultSign()
                    .build();

            Game game = new Game(settings, boardAPI, playerAPI, input, players);
            game.play();
        }
    }

    private static Coordinates getBoardDimensions(Input input) {
        Coordinates coordinates = null;
        do {
            coordinates = input.getCoordinates();
        }while (coordinates.getX() > 40 && coordinates.getY() > 40 && coordinates.getX() < 3 && coordinates.getY() < 3);
        return coordinates;
    }
}
