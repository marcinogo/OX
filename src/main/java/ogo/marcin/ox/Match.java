package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
class Match {
    private BoardAPI boardAPI;
    private PlayerAPI playerAPI;
    private Input input;

    private List<Player> players;
    private Board board;
    private Integer winCondition;

    private Boolean isWinner = Boolean.FALSE;

    Match(BoardAPI boardAPI, PlayerAPI playerAPI, Input input, Settings settings) {
        this.boardAPI = boardAPI;
        this.input = input;
        this.players = settings.getPlayers();
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
             System.out.println("Enter x coordinate");
             Integer x = input.getIntegerInput();
             System.out.println("Enter y coordinate");
             Integer y = input.getIntegerInput();
//             TODO check and info if they are correct;
             coordinates = boardAPI.createCoordinates(x, y);
         } while (!judge.isPlayerActionWithinBoard(coordinates) ||
                 !judge.isPlayerSignSetOnFreeSpace(coordinates));
        return coordinates;
    }

    private Boolean playPlayerTurn(Judge judge, Player player) {
        System.out.println(board);
        if (!judge.isFreeSpaceOnBoard()) return Boolean.FALSE;
        Boolean endTurn = Boolean.FALSE;

        System.out.printf("It is turn of %s - %s%n",
                playerAPI.getPlayerName(player), playerAPI.getPlayerSign(player));

        Coordinates coordinates = getCoordinates(judge);
        board = boardAPI.setField(board, coordinates, playerAPI.getPlayerSign(player));
        judge.setBoard(board);
        isWinner = judge.isPlayerWon(playerAPI.getPlayerSign(player), coordinates);
        if(isWinner) endTurn = Boolean.TRUE;
        return endTurn;
    }

    private Player playMatch(Judge judge) {
        Player winner = null;
        do {
            for (Player player: players) {
                if(playPlayerTurn(judge, player)) {
                    winner = player;
                    break;
                }
            }
        } while (judge.isFreeSpaceOnBoard() && !isWinner);
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
            System.out.printf("Winner is %s", winner);
        }
    }
}
