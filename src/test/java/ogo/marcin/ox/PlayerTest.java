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
        assert player.name != null : "Player should do not throw NPE";
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIfCreatePlayerWithEmptyNameThrowsException() {
        Player player = new Player("", Sign.X);
        assert !player.name.equals("") : "Player should not be empty";
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIfCreatePlayerWithoutSignThrowsException() {
        Player player = new Player("Player 1", null);
        assert player.playerSign != null : "Player should have sign";
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIfCreatePlayerWithDefaultSignThrowsException() {
        Player player = new Player("Player 1", Sign.DEFAULT);
        assert !player.playerSign.equals(Sign.DEFAULT) : "Player should have not default sign";
    }

    public void testIfCreatePlayerWith0Points() {
        Player player = new Player("Player 1", Sign.X);
        assert player.points.equals(0) : "Created player should have 0 points";
    }
}
