package ogo.marcin.ox.game;

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
    private final Settings settings;
    private final BoardAPI boardAPI;
    private final PlayerAPI playerAPI;
    private final Input input;
    private final List<Match> matches;
    private final List<Player> players;

    private Game(Settings settings, BoardAPI boardAPI, PlayerAPI playerAPI, Input input,
                 List<Match> matches, List<Player> players) {
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
            Judge judge = new Judge(boardAPI, settings);
            Match match = new Match(boardAPI, playerAPI, input, players, judge);
            matches.add(match);
            match.play();
            boardAPI.clearBoard(settings.getDefaultSign());
            Collections.reverse(players);
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
