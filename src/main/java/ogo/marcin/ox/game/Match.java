package ogo.marcin.ox.game;

import ogo.marcin.ox.automation.AutoMatchSettings;
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
    private final Judge judge;

    private final AutoMatchSettings autoMatchSettings;

    private boolean isWinner;

    Match(Input input, Output output,
          BoardAPI boardAPI, PlayerAPI playerAPI,
          Judge judge, AutoMatchSettings autoMatchSettings) {
        this.input = input;
        this.output = output;
        this.boardAPI = boardAPI;
        this.playerAPI = playerAPI;
        this.judge = judge;
        this.autoMatchSettings = autoMatchSettings;
    }

    void play() {
        Optional<Player> winner = playMatch(judge);
        winner.ifPresentOrElse(this::announceWinner, this::announceDraw);
    }

    private Optional<Player> playMatch(Judge judge) {
        Player winner = null;
        if(!autoMatchSettings.isAutomated()) output.print(Localization.LanguageKey.START_OF_MATCH);
        do {
            for (Player player: playerAPI.getPlayers()) {
                if(!autoMatchSettings.isAutomated()) output.print(boardAPI.getBoard().toString());
                if (!judge.isFreeSpaceOnBoard()) break;
                if(!autoMatchSettings.isAutomated()) output.printf(Localization.LanguageKey.PLAYER_WITH_MOVE,
                        playerAPI.getPlayerName(player), playerAPI.getPlayerSign(player));
                if(playPlayerTurn(player)) {
                    winner = player;
                    break;
                }
            }
        } while (judge.isFreeSpaceOnBoard() && !isWinner);
        if(!autoMatchSettings.isAutomated()) output.print(Localization.LanguageKey.END_OF_MATCH);
        output.print(boardAPI.getBoard().toString());
        return Optional.ofNullable(winner);
    }

    private Boolean playPlayerTurn(Player player) {
        Coordinates coordinates;
        if(!autoMatchSettings.isAutomated()) {
            coordinates = input.getCoordinates(judge);
        } else {
            coordinates = playAiPlayer(player);
        }
        boardAPI.setField(coordinates, playerAPI.getPlayerSign(player));
        return isWinner = judge.isPlayerWon(playerAPI.getPlayerSign(player), coordinates);
    }

    private Coordinates playAiPlayer(Player player) {
        Coordinates coordinates;
        if(playerAPI.getPlayerName(player).equals("O-AI")) {
            coordinates = moveAiPlayer(autoMatchSettings.getListOfMovesOAi());
        } else {
            coordinates = moveAiPlayer(autoMatchSettings.getListOfMovesXAi());
        }
        return coordinates;
    }

    private Coordinates moveAiPlayer(List<Integer> listOfMoves) {
        return new Coordinates.CoordinatesBuilder()
                .withMovePosition(listOfMoves.remove(0))
                .build();
    }

    private void announceDraw() {
        output.print(Localization.LanguageKey.DRAW_IN_MATCH);
        givePointsForDraw();
    }

    private void givePointsForDraw() {
        for(int i = 0; i < playerAPI.getPlayers().size(); i++) {
            Player player = playerAPI.getPlayerOnIndex(i);
            int playerPoints = playerAPI.getPlayerPoints(player);
            playerAPI.getPlayers().set(i, playerAPI.setPlayerPoints(player, playerPoints + 1));
        }
    }

    private void announceWinner(Player victoriousPlayer) {
        givePointsForWinn(victoriousPlayer);
        output.printf(Localization.LanguageKey.WINNER_OF_MATCH, playerAPI.getPlayerName(victoriousPlayer));
    }

    private void givePointsForWinn(Player victoriousPlayer) {
        int playerPoints = playerAPI.getPlayerPoints(victoriousPlayer);
        int i = playerAPI.getPlayers().indexOf(victoriousPlayer);
        victoriousPlayer = playerAPI.setPlayerPoints(victoriousPlayer, playerPoints + 3);
        playerAPI.getPlayers().set(i, victoriousPlayer);
    }
}
