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

    private static BoardDimension getBoardDimensions(Input input) {
        BoardDimension boardDimension = null;
        do {
            DimensionBuilder<BoardDimension> boardDimensionDimensionBuilder = new BoardDimensionBuilder(input);
            boardDimension = boardDimensionDimensionBuilder.withXDimension("Enter width")
                    .withYDimension("Enter height")
                    .build();
        }while (boardDimension.getXDimension() > 40 && boardDimension.getYDimension() > 40 && boardDimension.getXDimension() < 3 && boardDimension.getYDimension() < 3);
        return boardDimension;
    }
}
