package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class PlayerAPIImplTest {

    @DataProvider
    public static Object[][] createPlayer(){
        return new Object[][] {
                {"Player 1", Sign.X, 0},
                {"Player 1", Sign.O, 0},
                {"Player 2", Sign.X, 10},
                {"Player 2", Sign.O, 10},
                {"Tomek", Sign.X, 0},
                {"Tomek", Sign.O, 0},
                {"Marcin", Sign.X, 20},
                {"Marcin", Sign.O, 20},
                {"Zbyszek", Sign.X, 3},
                {"Zbyszek", Sign.O, 3},
        };
    }

    @Test(dataProvider = "createPlayer")

    public void testCreatePlayerDontReturnNull(String name, Sign sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player = playerAPI.createPlayer(name, sign, points);
        assert player != null : "Player should not be null";
    }

    @Test(dataProvider = "createPlayer")
    public void testCreatePlayerWithGivenPoints(String name, Sign sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player = playerAPI.createPlayer(name, sign, points);
        Player expected = new Player(name, sign, points);
        assert player.equals(expected) : "Players should be equals";
    }

    @Test(dataProvider = "createPlayer")
    public void testCreatePlayer(String name, Sign sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player = playerAPI.createPlayer(name, sign);
        Player expected = new Player(name, sign);

        assert player != null : "Player should not be null";
        assert player.equals(expected) : "Players should be equals";
    }

    @DataProvider
    public static Object[][] setPlayerPoints(){
        return new Object[][] {
                {"Player 1", Sign.X, 0, 3},
                {"Player 1", Sign.O, 0, 3},
                {"Player 2", Sign.X, 10, 3},
                {"Player 2", Sign.O, 10, 3},
                {"Tomek", Sign.X, 0, 100},
                {"Tomek", Sign.O, 0, 100},
        };
    }

    @Test(dataProvider = "setPlayerPoints")
    public void testIfPlayerPointsCanBeSet(String name, Sign sign,
                                           Integer startingPoints, Integer newNumberOfPoints) {
        Player player = new Player(name, sign, startingPoints);
        Player expected = new Player(name, sign, newNumberOfPoints);
        PlayerAPI playerAPI = new PlayerAPIImpl();
        player = playerAPI.setPlayerPoints(player, newNumberOfPoints);
        assert player.equals(expected) : "Players should have should be equals points";
    }

    @Test(dataProvider = "createPlayer")
    public void testGetName(String name, Sign sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player = new Player(name, sign, points);
        assert playerAPI.getPlayerName(player).equals(name) : "Player should be returned";
    }

    @Test(dataProvider = "createPlayer")
    public void testGetSign(String name, Sign sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player = new Player(name, sign, points);
        assert playerAPI.getPlayerSign(player).equals(sign) : "Player sign be returned";
    }

    @Test(dataProvider = "createPlayer")
    public void testGetPoints(String name, Sign sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl();
        Player player = new Player(name, sign, points);
        assert playerAPI.getPlayerPoints(player).equals(points) : "Player sign be returned";
    }
}