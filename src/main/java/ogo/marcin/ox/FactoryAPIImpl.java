package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.BoardFactory;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.dimension.BoardDimension;

/**
 * @author Marcin Ogorzalek
 */
public class FactoryAPIImpl implements FactoryAPI {
    @Override
    public Board createBoard(BoardDimension boardDimension) {
        return new BoardFactory().createBoard(boardDimension, Sign.DEFAULT);
    }

    @Override
    public Board createBoard(BoardDimension boardDimension, Sign defaultSign) {
        return new BoardFactory().createBoard(boardDimension, defaultSign);
    }
}
