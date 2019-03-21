package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;
import ogo.marcin.ox.player.Player;
import ogo.marcin.ox.player.PlayerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
public class PlayerFactoryTest {

    @DataProvider
    public static Object[][] createPlayerWithGivenPoints(){
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

    @Test(dataProvider = "createPlayerWithGivenPoints")
    public void testCreatePlayerWithGivenPoints(String name, Sign sign, Integer points) {
        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.createPlayer(name, sign, points);
        Player expected = new Player(name, sign, points);

        assert player != null : "Player should not be null";
        assert player.equals(expected) : "Players should be equals";
    }

    @DataProvider
    public static Object[][] createPlayer(){
        return new Object[][] {
                {"Player 1", Sign.X},
                {"Player 1", Sign.O},
                {"Player 2", Sign.X},
                {"Player 2", Sign.O},
                {"Tomek", Sign.X},
                {"Tomek", Sign.O},
                {"Marcin", Sign.X},
                {"Marcin", Sign.O},
                {"Zbyszek", Sign.X},
                {"Zbyszek", Sign.O},
        };
    }

    @Test(dataProvider = "createPlayer")
    public void testCreatePlayerDontReturnNull(String name, Sign sign) {
        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.createPlayer(name, sign);
        assert player != null : "Player should not be null";
    }

    @Test(dataProvider = "createPlayer")
    public void testCreatePlayer(String name, Sign sign) {
        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.createPlayer(name, sign);
        Player expected = new Player(name, sign);
        assert player.equals(expected) : "Players should be equals";
    }
}