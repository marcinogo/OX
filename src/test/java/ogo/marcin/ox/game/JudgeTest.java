package ogo.marcin.ox.game;

import ogo.marcin.ox.board.BoardAPI;
import ogo.marcin.ox.board.Sign;
import org.mockito.Mockito;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */

@Test
public class JudgeTest {
    public void testFreeSpaceOnBoardReturnTrue() {
        BoardAPI boardAPI = Mockito.mock(BoardAPI.class);
        Settings settings = Mockito.mock(Settings.class);

        Judge judge = new Judge(boardAPI, settings);

        judge.isFreeSpaceOnBoard();
        Mockito.verify(boardAPI).isFreeSpaceOnBoard();
    }

    public void testIsPlayerSignSetOnFreeSpace() {
        BoardAPI boardAPI = Mockito.mock(BoardAPI.class);
        Settings settings = Mockito.mock(Settings.class);

        Judge judge = new Judge(boardAPI, settings);
        Coordinates coordinates = Mockito.mock(Coordinates.class);
        judge.isPlayerSignSetOnFreeSpace(coordinates);
        Mockito.verify(boardAPI).isCoordinatesPointsToDefaultSign(settings.getDefaultSign(), coordinates);
    }

    public void testIsPlayerWon() {
        BoardAPI boardAPI = Mockito.mock(BoardAPI.class);
        Settings settings = Mockito.mock(Settings.class);

        Judge judge = new Judge(boardAPI, settings);
        Coordinates coordinates = Mockito.mock(Coordinates.class);
        Sign sign = Sign.X;
        judge.isPlayerWon(sign, coordinates);
        Mockito.verify(boardAPI).whetherWinningConditionHasBeenMet(sign, coordinates, settings.getWinCondition());
    }
}