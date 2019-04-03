package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.player.*;
import ogo.marcin.ox.dimension.BoardDimension;
import ogo.marcin.ox.dimension.BoardDimensionBuilder;
import ogo.marcin.ox.dimension.DimensionBuilder;
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
//    TODO: Change factories to Builders and create some common interface for this wit T build();
            PlayerAPI playerAPI = new PlayerAPIImpl();

            Input input = new Input(scanner);
            List<Player> players = playerAPI.createPlayers(input);

            Board board = new Board.BoardBuilder()
                    .withDimension(getBoardDimensions(input))
                    .withDefaultSign()
                    .build();
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

//    TODO: move this to some class / API
    private static BoardDimension getBoardDimensions(Input input) {
        BoardDimension boardDimension = null;
        boolean dimensionsCreated;
        do {
            try {
                DimensionBuilder<BoardDimension> boardDimensionDimensionBuilder = new BoardDimensionBuilder();
                System.out.println("Enter width and height");
                int dimension = input.getIntegerInput();
                boardDimension = boardDimensionDimensionBuilder
                        .withDimension(dimension)
                        .build();
                dimensionsCreated = true;
            } catch (IllegalArgumentException e) {
                dimensionsCreated = false;
            }
        } while (!dimensionsCreated);
        return boardDimension;
    }
}
