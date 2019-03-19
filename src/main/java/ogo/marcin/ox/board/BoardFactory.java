package ogo.marcin.ox.board;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardFactory {
    Board createBoard(int width, int height, Sign defaultSign);
}
