package ogo.marcin.ox;

import ogo.marcin.ox.board.Sign;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class PlayerTest {
    public void testCreatePlayer() {
        Player player = new Player("Player 1", Sign.X);
        assert player.name.equals("Player 1") : "Player should have name \"Player 1\"";
        assert player.playerSign == Sign.X : "Player should have sign X";
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIfCreatePlayerWithoutNameThrowsException() {
        Player player = new Player(null, Sign.X);
        assert player.name.equals("Player 1") : "Player should do not throw NPE";
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIfCreatePlayerWithEmptyThrowsException() {
        Player player = new Player("", Sign.X);
        assert player.name.equals("Player 1") : "Player should not be empty";
    }
}
