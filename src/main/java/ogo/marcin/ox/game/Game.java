package ogo.marcin.ox.game;

import ogo.marcin.ox.Settings;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public class Game {
    private Settings settings;
    private BoardAPI boardAPI;
    private PlayerAPI playerAPI;
    private Input input;
    private List<Match> matches;
    private List<Player> players;

    public Game(Settings settings, BoardAPI boardAPI, PlayerAPI playerAPI, Input input, List<Match> matches, List<Player> players) {
        this.settings = settings;
        this.boardAPI = boardAPI;
        this.playerAPI = playerAPI;
        this.input = input;
        this.matches = matches;
        this.players = players;
    }

    public Game(Settings settings, BoardAPI boardAPI, PlayerAPI playerAPI, Input input, List<Player> players) {
        this(settings, boardAPI, playerAPI, input, new LinkedList<>(), players);
    }

    public void play() {
        for (int i = 0; i < settings.getNumberOfRounds(); i++) {
            if (i % 2 != 0) {
                Collections.reverse(players);
            }
            Match match = new Match(boardAPI, playerAPI, input, settings, players);
            matches.add(match);
            match.play();
            boardAPI.setBoard(boardAPI.clearBoard(settings.getDefaultSign()));
        }
        printResult();
    }

    private void printResult() {
        if(players.get(0).compareTo(players.get(1)) == 0) {
            System.out.println("Draw in game");
        } else if(players.get(0).compareTo(players.get(1)) > 0){
            System.out.printf("Winner of game is %s%n", players.get(0));
        } else {
            System.out.printf("Winner of game is %s%n", players.get(1));
        }
    }
}
