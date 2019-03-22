package ogo.marcin.ox.game;

import ogo.marcin.ox.Settings;
import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
class Match {
    private final BoardAPI boardAPI;
    private final PlayerAPI playerAPI;
    private final Input input;

    private final List<Player> players;
    private final Integer winCondition;

    private Board board;

    private Boolean isWinner = Boolean.FALSE;

    Match(BoardAPI boardAPI, PlayerAPI playerAPI, Input input, Settings settings, List<Player> players) {
        this.boardAPI = boardAPI;
        this.input = input;
        this.players = players;
        this.winCondition = settings.getWinCondition();
        this.playerAPI = playerAPI;
        this.board = settings.getBoard();
    }

    void play() {
        Judge judge = new Judge(boardAPI, winCondition, board);
        Player winner = playMatch(judge);
        giveMatchResult(winner);
    }

    private Coordinates getCoordinates(Judge judge) {
        Coordinates coordinates = null;
         do {
            coordinates = input.getCoordinates();
         } while (!judge.isPlayerActionWithinBoard(coordinates) ||
                 !judge.isPlayerSignSetOnFreeSpace(coordinates));
        return coordinates;
    }

    private Boolean playPlayerTurn(Judge judge, Player player) {
        System.out.printf("It is turn of %s - %s%n",
            playerAPI.getPlayerName(player), playerAPI.getPlayerSign(player));

        Coordinates coordinates = getCoordinates(judge);
        board = boardAPI.setField(board, coordinates, playerAPI.getPlayerSign(player));
        judge.setBoard(board);
        // TODO Bug with winner - win at 3 at board but not proper one
        return isWinner = judge.isPlayerWon(playerAPI.getPlayerSign(player), coordinates);
    }

    private Player playMatch(Judge judge) {
        Player winner = null;
        do {
            for (Player player: players) {
                System.out.println(board);
                if (!judge.isFreeSpaceOnBoard()) break;
                if(playPlayerTurn(judge, player)) {
                    winner = player;
                    break;
                }
            }
        } while (judge.isFreeSpaceOnBoard() && !isWinner);
        System.out.println(board);
        return winner;
    }

    private void giveMatchResult(Player winner) {
        if(winner == null) {
            System.out.println("Draw");
            for(int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                Integer playerPoints = playerAPI.getPlayerPoints(player);
                players.set(i, playerAPI.setPlayerPoints(player, playerPoints + 1));
            }
        } else {
            Integer playerPoints = playerAPI.getPlayerPoints(winner);
            int i = players.indexOf(winner);
            winner = playerAPI.setPlayerPoints(winner, playerPoints + 3);
            players.set(i, winner);
            System.out.printf("Winner of match is %s%n", playerAPI.getPlayerName(winner));
        }
    }
}
