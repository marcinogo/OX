package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
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

    public void testIfCreatePlayerWith0Points() {
        Player player = new Player("Player 1", Sign.X);
        assert player.points == 0 : "Created player should have 0 points";
    }

    public void testPlayersEquality() {
        Player player1 = new Player("Player 1", Sign.X);
        Player player2 = new Player("Player 1", Sign.X);
        assert player1.equals(player2);
    }

    public void testPlayersHashCode() {
        Player player1 = new Player("Player 1", Sign.X);
        Player player2 = new Player("Player 1", Sign.X);
        assert player1.hashCode() == player2.hashCode();
    }

    public void testIfCanCreatePlayerWithGivenNumberOfPoints() {
        Player player = new Player("Player 1", Sign.X, 20);
        assert player.points == 20 : "Player should have 20 points";
    }

    @DataProvider
    public static Object[][] testPlayer(){
        return new Object[][] {
            {"Player 1", Sign.X, 0, 3},
            {"Player 1", Sign.O, 0, 3},
            {"Player 2", Sign.X, 10, 3},
            {"Player 2", Sign.O, 10, 3},
            {"Tomek", Sign.X, 0, 100},
            {"Tomek", Sign.O, 0, 100},
        };
    }

    @Test(dataProvider = "testPlayer")
    public void testIfPlayerPointsCanBeSet(String name, Sign sign,
                                           Integer startingPoints, Integer newNumberOfPoints) {
        Player player = new Player(name, sign, startingPoints);
        player = player.setPlayerPoints(newNumberOfPoints);
        assert player.points == newNumberOfPoints : String.format("Player should " +
                "have %d points", newNumberOfPoints);
    }

    @DataProvider
    public static Object[][] testToString(){
        return new Object[][] {
                {"Player 1", Sign.X, 0},
                {"Player 1", Sign.O, 0},
                {"Player 2", Sign.X, 10},
                {"Player 2", Sign.O, 10},
                {"Tomek", Sign.X, 0},
                {"Tomek", Sign.O, 0},
                {"Tomek", Sign.X, 20},
                {"Tomek", Sign.O, 20},
                {"Tomek", Sign.X, 3},
                {"Tomek", Sign.O, 3},
        };
    }

    @Test(dataProvider = "testToString")
    public void testIfPlayerPointsCanBeSet(String name, Sign sign, Integer points) {
        Player player = new Player(name, sign, points);
        String expected = String.format("%s with sign %s have: %d points", name, sign, points);
        assert player.toString().equals(expected) : "toString give wrong output";
    }
}
