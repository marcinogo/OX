package ogo.marcin.ox;

/**
 * @author Marcin Ogorzalek
 */
public class BoardFactoryImpl implements BoardFactory {
    public Board createBoard(int width, int height, Sign defaultSign) {
        Board board = new Board(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board = board.setField(i, j, new Field(defaultSign));
            }
        }
        return board;
    }
}
