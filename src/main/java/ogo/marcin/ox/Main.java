package ogo.marcin.ox;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
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

            Output output = new Output(System.out);
            Input input = new Input(scanner, output);
            chooseLanguage(input, output);

            List<Player> players = playerAPI.createPlayers(input, output);

            Board board = new Board.BoardBuilder()
                    .withDimension(input.getBoardDimensions())
                    .build();
            BoardAPI boardAPI = new BoardAPIImpl(board);

            int MIN_WIN_CONDITION = 3;
            int MAX_WIN_CONDITION = boardAPI.getBoardDimension();

            Settings settings = new Settings.SettingsBuilder()
                    .withWinConditionInRange(input.getWinConditionInRange(MIN_WIN_CONDITION, MAX_WIN_CONDITION))
                    .build();

            Game game = new Game(settings, boardAPI, playerAPI, input, output, players);
            game.play();
        }
    }

    private static void chooseLanguage(Input input, Output output) {
        output.print(Localization.LanguageKey.CHOOSE_LANGUAGE);
        Localization.setResourceBundleLanguage(input.getStringInput());
    }
}
