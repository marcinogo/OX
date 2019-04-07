package ogo.marcin.ox.game;

import ogo.marcin.ox.automation.AutoMatchSettings;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.Collections;

/**
 * @author Marcin Ogorzalek
 */
public class Game {
    private final Settings settings;
    private final BoardAPI boardAPI;
    private final PlayerAPI playerAPI;
    private final Input input;
    private final Output output;
    private final AutoMatchSettings autoMatchSettings;

    public Game(Settings settings, BoardAPI boardAPI, PlayerAPI playerAPI,
                 Input input, Output output, AutoMatchSettings autoMatchSettings) {
        this.settings = settings;
        this.boardAPI = boardAPI;
        this.playerAPI = playerAPI;
        this.input = input;
        this.output = output;
        this.autoMatchSettings = autoMatchSettings;
    }

    public void play() {
        for (int i = 0; i < settings.getNumberOfRounds(); i++) {
            Judge judge = new Judge(boardAPI, settings);
            Match match;
            if(autoMatchSettings.isAutomated()) {
                autoMatchSettings.setXAiMoves(i);
            }
            match = new Match(input, output, boardAPI, playerAPI, judge, autoMatchSettings);
            match.play();
            boardAPI.clearBoard(settings.getDefaultSign());
            if(!autoMatchSettings.isAutomated()) Collections.reverse(playerAPI.getPlayers());
        }
        if(!autoMatchSettings.isAutomated()) printResult();
    }

    private void printResult() {
        if(playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) == 0) {
            output.print(Localization.LanguageKey.DRAW_OF_GAME);
        } else if(playerAPI.getPlayerOnIndex(0).compareTo(playerAPI.getPlayerOnIndex(1)) > 0){
            output.printf(Localization.LanguageKey.WINNER_OF_GAME,
                    playerAPI.getPlayerName(playerAPI.getPlayerOnIndex(0)), playerAPI.getPlayerPoints(playerAPI.getPlayerOnIndex(0)));
        } else {
            output.printf(Localization.LanguageKey.WINNER_OF_GAME,
                    playerAPI.getPlayerName(playerAPI.getPlayerOnIndex(1)), playerAPI.getPlayerPoints(playerAPI.getPlayerOnIndex(1)));
        }
    }
}
