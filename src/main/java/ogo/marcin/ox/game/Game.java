package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.Collections;
import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public class Game {
    private final Settings settings;
    private final BoardAPI boardAPI;
    private final PlayerAPI playerAPI;
    private final Input input;
    private final Output output;
    private final List<Player> players;

    public Game(Settings settings, BoardAPI boardAPI, PlayerAPI playerAPI,
                 Input input, Output output, List<Player> players) {
        this.settings = settings;
        this.boardAPI = boardAPI;
        this.playerAPI = playerAPI;
        this.input = input;
        this.output = output;
        this.players = players;
    }

    public void play() {
        for (int i = 0; i < settings.getNumberOfRounds(); i++) {
            Judge judge = new Judge(boardAPI, settings);
            Match match = new Match(input, output, boardAPI, playerAPI, players, judge);
            match.play();
            boardAPI.clearBoard(settings.getDefaultSign());
            Collections.reverse(players);
        }
        printResult();
    }

    private void printResult() {
        if(players.get(0).compareTo(players.get(1)) == 0) {
            output.print(Localization.LanguageKey.DRAW_OF_GAME);
        } else if(players.get(0).compareTo(players.get(1)) > 0){
            output.printf(Localization.LanguageKey.WINNER_OF_GAME,
                    playerAPI.getPlayerName(players.get(0)), playerAPI.getPlayerPoints(players.get(0)));
        } else {
            output.printf(Localization.LanguageKey.WINNER_OF_GAME,
                    playerAPI.getPlayerName(players.get(1)), playerAPI.getPlayerPoints(players.get(1)));
        }
    }
}
