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

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class AutoMain {
    public static void main(String[] args) {
        int winC = 3;
        int boardE = 3;
        WinConditionGenerator winConditionGenerator = new WinConditionGenerator(boardE,winC);
        winConditionGenerator.generateWinPatternRows()
                .generateWinPatternColumns();

        int numberOfRounds = winConditionGenerator.winPatterns.size();

        try(Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            Output output = new Output(System.out);
            Input input = new Input(scanner, output);
            Localization.setResourceBundleLanguage("ENGLISH");

            List<Player> players = new LinkedList<>();
            PlayerAPI playerAPI = new PlayerAPIImpl(players);
            players.add(new Player.PlayerBuilder().withName("X-AI").withSign("X").build());
            players.add(new Player.PlayerBuilder().withName("O-AI").withSign("O").build());

            BoardDimension boardDimension = new BoardDimension.BoardDimensionBuilder().withBoardEdgeSize(boardE).build();

            Board board = new Board.BoardBuilder()
                    .withDimension(boardDimension)
                    .build();
            BoardAPI boardAPI = new BoardAPIImpl(board);

            Settings settings = new Settings.SettingsBuilder(true)
                    .withWinConditionInRange(winC)
                    .withNumberOfRounds(numberOfRounds)
                    .withDefaultSing(Sign.DEFAULT)
                    .build();

            boolean isAutomated = true;

            AutoMatchSettings autoMatchSettings = new AutoMatchSettings(boardAPI, isAutomated, winConditionGenerator.winPatterns);

            Game game = new Game(settings, boardAPI, playerAPI, input, output, autoMatchSettings);
            game.play();
        }
    }
}
