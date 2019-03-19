package ogo.marcin.ox;

/**
 * @author Marcin Ogorzalek
 */
public interface BoardFactory {
    Board createBoard(int width, int height, Sign defaultSign);
}
