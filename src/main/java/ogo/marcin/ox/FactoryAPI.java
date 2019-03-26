package ogo.marcin.ox;

import ogo.marcin.ox.board.Board;
import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.dimension.BoardDimension;

/**
 * @author Marcin Ogorzalek
 */
public interface FactoryAPI {
    Board createBoard(BoardDimension boardDimension);
    Board createBoard(BoardDimension boardDimension, Sign defaultSign);
}
