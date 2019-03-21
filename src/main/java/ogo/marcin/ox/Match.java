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
    private Input input;
    private List<Player> players;
    private Sign defaultSign;
    private Integer winCondition;
    private PlayerAPI playerAPI;
    private Boolean isWinner;
    private Board board;

    public Match(BoardAPI boardAPI, Input input, List<Player> players, Sign defaultSign,
                 Integer winCondition, PlayerAPI playerAPI, Boolean isWinner, Board board) {
        this.boardAPI = boardAPI;
        this.input = input;
        this.players = players;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.playerAPI = playerAPI;
        this.isWinner = isWinner;
        this.board = board;
    }

    void play() {
        Judge judge = new Judge(boardAPI, defaultSign, winCondition);

        Player winner = matchLoop(judge);

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
            players.set(i, playerAPI.setPlayerPoints(winner, playerPoints + 3));
            System.out.printf("Winner is %s", winner);
        }

    }

    private Coordinates getCoordinates() {
        Coordinates coordinates = null;
         do {
             System.out.println("Enter x coordinate");
             Integer x = input.getIntegerInput();
             System.out.println("Enter y coordinate");
             Integer y = input.getIntegerInput();
//             TODO check and info if they are correct;
             coordinates = new Coordinates(x, y);
         } while (!boardAPI.isCoordinatesWithinBoard(board, coordinates) ||
                 !boardAPI.isCoordinatesPointsToDefaultSign(board, defaultSign, coordinates));
        return coordinates;
    }

    private Boolean playerTurn(Judge judge, Player player) {
        System.out.println(board);
        if (!judge.isFreeSpaceOnBoard(board)) return Boolean.FALSE;
        Boolean endTurn = Boolean.FALSE;

        System.out.printf("It is turn of %s - %s%n",
                playerAPI.getPlayerName(player), playerAPI.getPlayerSign(player));

        Coordinates coordinates = getCoordinates();
        board = boardAPI.setField(board, coordinates, playerAPI.getPlayerSign(player));
        isWinner = judge.isPlayerWon(board, playerAPI.getPlayerSign(player), coordinates);
        if(isWinner) endTurn = Boolean.TRUE;
        return endTurn;
    }

    private Player matchLoop(Judge judge) {
        Player winner = null;
        do {
            for (Player player: players) {
                if(playerTurn(judge, player)) {
                    winner = player;
                    break;
                }
            }
        } while (judge.isFreeSpaceOnBoard(board) && !isWinner);
        return winner;
    }
}
