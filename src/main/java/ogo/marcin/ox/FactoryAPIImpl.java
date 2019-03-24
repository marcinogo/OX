package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardFactory;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.dimension.BoardDimension;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerFactory;

/**
 * @author Marcin Ogorzalek
 */
public class FactoryAPIImpl implements FactoryAPI {
    @Override
    public Player createPlayer(String name, Sign sign) {
        return new PlayerFactory().createPlayer(name, sign);
    }

    @Override
    public Player createPlayer(String name, Sign sign, Integer points) {
        return new PlayerFactory().createPlayer(name, sign, points);
    }

    @Override
    public Board createBoard(BoardDimension boardDimension) {
        return new BoardFactory().createBoard(boardDimension, Sign.DEFAULT);
    }

    @Override
    public Board createBoard(BoardDimension boardDimension, Sign defaultSign) {
        return new BoardFactory().createBoard(boardDimension, defaultSign);
    }

    @Override
    public Coordinates createCoordinates(Integer x, Integer y) {
        return new Coordinates(x, y);
    }
}
