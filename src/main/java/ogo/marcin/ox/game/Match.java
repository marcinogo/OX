package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.dimension.Coordinates;
import ogo.marcin.ox.dimension.CoordinatesBuilder;
import ogo.marcin.ox.dimension.DimensionBuilder;
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
        giveMatchResult(winner);
    }

//    TODO: move this to some class / API
    private Coordinates getCoordinates(Judge judge) {
        Coordinates coordinates = null;
        boolean coordinatesWithinBoard;
         do {
            try {
                DimensionBuilder<Coordinates> coordinatesDimensionBuilder = new CoordinatesBuilder();
                System.out.println("Enter coordiate");
                int dimension = input.getIntegerInput() - 1;
                coordinates = coordinatesDimensionBuilder.withXDimension(dimension % boardAPI.getBoard().getWidth())
                        .withYDimension(dimension / boardAPI.getBoard().getWidth())
                        .build();
                coordinatesWithinBoard = true;
            } catch (IllegalArgumentException e) {
                coordinatesWithinBoard = false;
            }
         } while (!coordinatesWithinBoard ||
                 !judge.isPlayerSignSetOnFreeSpace(coordinates));
        return coordinates;
    }

    private Boolean playPlayerTurn(Judge judge, Player player) {
        Coordinates coordinates = getCoordinates(judge);
        boardAPI.setField(coordinates, playerAPI.getPlayerSign(player));
        return isWinner = judge.isPlayerWon(playerAPI.getPlayerSign(player), coordinates);
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

    private void giveMatchResult(Optional<Player> winner) {
        if(winner.isEmpty()) {
            System.out.println("Draw");
            for(int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                Integer playerPoints = playerAPI.getPlayerPoints(player);
                players.set(i, playerAPI.setPlayerPoints(player, playerPoints + 1));
            }
        } else {
            Player victoriusPlayer = winner.get();
            Integer playerPoints = playerAPI.getPlayerPoints(victoriusPlayer);
            int i = players.indexOf(victoriusPlayer);
            victoriusPlayer = playerAPI.setPlayerPoints(victoriusPlayer, playerPoints + 3);
            players.set(i, victoriusPlayer);
            System.out.printf("Winner of match is %s%n", playerAPI.getPlayerName(victoriusPlayer));
        }
    }
}
