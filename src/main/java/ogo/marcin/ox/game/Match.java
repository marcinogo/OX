package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.io.Input;
import ogo.marcin.ox.io.Localization;
import ogo.marcin.ox.io.Output;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerAPI;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcin Ogorzalek
 */
class Match {
    private final Input input;
    private final Output output;
    private final BoardAPI boardAPI;
    private final PlayerAPI playerAPI;
    private final List<Player> players;
    private final Judge judge;

    private boolean isWinner;

    Match(Input input, Output output, BoardAPI boardAPI, PlayerAPI playerAPI, List<Player> players, Judge judge) {
        this.input = input;
        this.output = output;
        this.boardAPI = boardAPI;
        this.playerAPI = playerAPI;
        this.players = players;
        this.judge = judge;
    }

    void play() {
        Optional<Player> winner = playMatch(judge);
        winner.ifPresentOrElse(this::announceWinner, this::announceDraw);
    }

    private Optional<Player> playMatch(Judge judge) {
        Player winner = null;
        do {
            for (Player player: players) {
                System.out.println(boardAPI.getBoard());
                if (!judge.isFreeSpaceOnBoard()) break;
                output.printf(Localization.LanguageKey.PLAYER_WITH_MOVE,
                        playerAPI.getPlayerName(player), playerAPI.getPlayerSign(player));
                if(playPlayerTurn(player)) {
                    winner = player;
                    break;
                }
            }
        } while (judge.isFreeSpaceOnBoard() && !isWinner);
        return Optional.ofNullable(winner);
    }

    private Boolean playPlayerTurn(Player player) {
        Coordinates coordinates = input.getCoordinates(judge);
        boardAPI.setField(coordinates, playerAPI.getPlayerSign(player));
        return isWinner = judge.isPlayerWon(playerAPI.getPlayerSign(player), coordinates);
    }

    private void announceDraw() {
        output.print(Localization.LanguageKey.DRAW_IN_MATCH);
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
        output.printf(Localization.LanguageKey.WINNER_OF_MATCH, playerAPI.getPlayerName(victoriousPlayer));
    }

    private void givePointsForWinn(Player victoriousPlayer) {
        int playerPoints = playerAPI.getPlayerPoints(victoriousPlayer);
        int i = players.indexOf(victoriousPlayer);
        victoriousPlayer = playerAPI.setPlayerPoints(victoriousPlayer, playerPoints + 3);
        players.set(i, victoriousPlayer);
    }
}
