package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.player.*;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.game.Settings;
import ogo.marcin.ox.io.Input;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            PlayerAPI playerAPI = new PlayerAPIImpl();

            Input input = new Input(scanner);
            List<Player> players = playerAPI.createPlayers(input);

            Board board = new Board.BoardBuilder()
                    .withDimension(input.getBoardDimensions())
                    .withDefaultSign()
                    .build();
            BoardAPI boardAPI = new BoardAPIImpl(board);

            Settings settings = new Settings.SettingsBuilder(input)
                    .withWinCondition(boardAPI.getBoardDimension())
                    .withNumberOfRounds()
                    .withDefaultSign()
                    .build();

            Game game = new Game(settings, boardAPI, playerAPI, input, players);
            game.play();
        }
    }
}
