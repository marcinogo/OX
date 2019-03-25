package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.dimension.BoardDimension;
import ogo.marcin.ox.player.Player;

/**
 * @author Marcin Ogorzalek
 */
public interface FactoryAPI {
    Player createPlayer(String name, Sign sign);
    Player createPlayer(String name, Sign sign, Integer points);
    Board createBoard(BoardDimension boardDimension);
    Board createBoard(BoardDimension boardDimension, Sign defaultSign);
}
