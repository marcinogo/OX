package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.dimension.BoardDimension;
import ogo.marcin.ox.dimension.BoardDimensionBuilder;
import ogo.marcin.ox.dimension.DimensionBuilder;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;
import ogo.marcin.ox.player.PlayerListCreator;

import java.util.*;

/**
 * @author Marcin Ogorzalek
 */
public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
//    TODO: Change factories to Builders and create some common interface for this wit T build();
            PlayerAPI playerAPI = new PlayerAPIImpl();

            FactoryAPI factoryAPI = new FactoryAPIImpl();

            Input input = new Input(scanner);
            List<Player> players = new PlayerListCreator(input).createPlayers();

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

//    TODO: move this to some class / API
    private static BoardDimension getBoardDimensions(Input input) {
        BoardDimension boardDimension = null;
        boolean dimensionsCreated;
        do {
            try {
                DimensionBuilder<BoardDimension> boardDimensionDimensionBuilder = new BoardDimensionBuilder(input);
                boardDimension = boardDimensionDimensionBuilder.withXDimension("Enter width")
                        .withYDimension("Enter height")
                        .build();
                dimensionsCreated = true;
            } catch (IllegalArgumentException e) {
                dimensionsCreated = false;
            }
        }while (!dimensionsCreated);
        return boardDimension;
    }
}
