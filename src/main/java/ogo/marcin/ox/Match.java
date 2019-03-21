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

    public Match(BoardAPI boardAPI, Input input, List<Player> players,
                 Sign defaultSign, Integer winCondition, PlayerAPI playerAPI) {
        this.boardAPI = boardAPI;
        this.input = input;
        this.players = players;
        this.defaultSign = defaultSign;
        this.winCondition = winCondition;
        this.playerAPI = playerAPI;
    }

    void play() {
        Coordinates coordinates = new Coordinates(3, 3);
        Board board = boardAPI.createBoard(coordinates, defaultSign);
        Judge judge = new Judge(boardAPI, defaultSign, winCondition);

        System.out.println(board);
        Boolean isWinner = Boolean.FALSE;
        do {
            System.out.println(players.get(0));
            board = boardAPI.setField(board, getCoordinates(board), playerAPI.getPlayerSign(players.get(0)));
            System.out.println(board);

        } while (judge.isFreeSpaceOnBoard(board) && !isWinner);
    }

    private Coordinates getCoordinates(Board board) {
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
}
