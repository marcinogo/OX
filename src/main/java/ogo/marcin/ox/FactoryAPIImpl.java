package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardFactory;
import ogo.marcin.ox.board.Coordinates;
import ogo.marcin.ox.board.Sign;
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
    public Board createBoard(Coordinates dimensions) {
        return new BoardFactory().createBoard(dimensions, Sign.DEFAULT);
    }

    @Override
    public Board createBoard(Coordinates dimensions, Sign defaultSign) {
        return new BoardFactory().createBoard(dimensions, defaultSign);
    }

    @Override
    public Coordinates createCoordinates(Integer x, Integer y) {
        return new Coordinates(x, y);
    }
}
