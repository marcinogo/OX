package ogo.marcin.ox.automation;

import ogo.marcin.ox.board.*;
import ogo.marcin.ox.game.Game;
import ogo.marcin.ox.game.Settings;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;
import ogo.marcin.ox.player.PlayerAPIImpl;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
class AutoMain {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            Output output = new Output(new PrintStream(new File("automated_test.txt"), StandardCharsets.UTF_8));
            Input input = new Input(scanner, output);
            Localization.setResourceBundleLanguage("ENGLISH");

            BoardDimension boardDimension = input.getBoardDimensions();

            Board board = new Board.BoardBuilder()
                    .withDimension(boardDimension)
                    .build();
            BoardAPI boardAPI = new BoardAPIImpl(board);

            int MIN_BOARD_SIZE = 3;
            int winCondition = input.getWinConditionInRange(MIN_BOARD_SIZE, boardAPI.getBoardDimension());

            WinConditionGenerator winConditionGenerator = new WinConditionGenerator(boardAPI.getBoardDimension(),
                    winCondition);
            winConditionGenerator.generateWinPatternRows()
                    .generateWinPatternColumns()
                    .generateWinPatternDiagonal()
                    .generateWinPatternAntidiagonal();

            int numberOfRounds = winConditionGenerator.winPatterns.size();

            List<Player> players = new LinkedList<>();
            PlayerAPI playerAPI = new PlayerAPIImpl(players);
            players.add(new Player.PlayerBuilder().withName("X-AI").withSign("X").build());
            players.add(new Player.PlayerBuilder().withName("O-AI").withSign("O").build());

            Settings settings = new Settings.SettingsBuilder(true)
                    .withWinConditionInRange(winCondition)
                    .withNumberOfRounds(numberOfRounds)
                    .withDefaultSing(Sign.DEFAULT)
                    .build();

            AutoMatchSettings autoMatchSettings = new AutoMatchSettings(boardAPI,
                    true, winConditionGenerator.winPatterns);

            Game game = new Game(settings, boardAPI, playerAPI, input, output, autoMatchSettings);
            game.play();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}