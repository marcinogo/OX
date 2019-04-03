package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.dimension.Coordinates;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcin Ogorzalek
 */
class Match {
    private final BoardAPI boardAPI;
    private final PlayerAPI playerAPI;
    private final Input input;

    private final List<Player> players;
    private final Integer winCondition;

    private boolean isWinner;

    Match(BoardAPI boardAPI, PlayerAPI playerAPI, Input input, Settings settings, List<Player> players) {
        this.boardAPI = boardAPI;
        this.input = input;
        this.players = players;
        this.winCondition = settings.getWinCondition();
        this.playerAPI = playerAPI;
    }

    void play() {
        Judge judge = new Judge(boardAPI, winCondition);
        Optional<Player> winner = playMatch(judge);
        winner.ifPresentOrElse(this::announceWinner, this::announceDraw);
    }

    private Optional<Player> playMatch(Judge judge) {
        Player winner = null;
        do {
            for (Player player: players) {
                System.out.println(boardAPI.getBoard());
                if (!judge.isFreeSpaceOnBoard()) break;
                System.out.printf("It is turn of %s - %s%n",
                        playerAPI.getPlayerName(player), playerAPI.getPlayerSign(player));
                if(playPlayerTurn(judge, player)) {
                    winner = player;
                    break;
                }
            }
        } while (judge.isFreeSpaceOnBoard() && !isWinner);
        return Optional.ofNullable(winner);
    }

    private Boolean playPlayerTurn(Judge judge, Player player) {
        Coordinates coordinates = getLegalCoordinates(judge);
        boardAPI.setField(coordinates, playerAPI.getPlayerSign(player));
        return isWinner = judge.isPlayerWon(playerAPI.getPlayerSign(player), coordinates);
    }

    private Coordinates getLegalCoordinates(Judge judge) {
        Coordinates coordinates = null;
        boolean coordinatesWithinBoard;
         do {
            try {
                System.out.println("Enter coordinate");
                coordinates = input.getCoordinates(boardAPI.getBoardDimension());
                coordinatesWithinBoard = true;
            } catch (IllegalArgumentException e) {
                coordinatesWithinBoard = false;
            }
         } while (!coordinatesWithinBoard ||
                 !judge.isPlayerSignSetOnFreeSpace(coordinates));
        return coordinates;
    }

    private void announceDraw() {
        System.out.println("Draw");
        givePointsForDraw();
    }

    private void givePointsForDraw() {
        for(int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int playerPoints = playerAPI.getPlayerPoints(player);
            players.set(i, playerAPI.setPlayerPoints(player, playerPoints + 1));
        }
    }

    private void announceWinner(Player victoriousPlayer) {
        givePointsForWinn(victoriousPlayer);
        System.out.printf("Winner of match is %s%n", playerAPI.getPlayerName(victoriousPlayer));
    }

    private void givePointsForWinn(Player victoriousPlayer) {
        int playerPoints = playerAPI.getPlayerPoints(victoriousPlayer);
        int i = players.indexOf(victoriousPlayer);
        victoriousPlayer = playerAPI.setPlayerPoints(victoriousPlayer, playerPoints + 3);
        players.set(i, victoriousPlayer);
    }
}
