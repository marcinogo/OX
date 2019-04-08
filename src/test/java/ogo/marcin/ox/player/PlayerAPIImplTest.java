package ogo.marcin.ox.player;

import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class PlayerAPIImplTest {

    @DataProvider
    public static Object[][] createPlayer(){
        return new Object[][] {
                {"Player 1", "X"},
                {"Player 1", "O"},
                {"Player 2", "X"},
                {"Player 2", "O"},
                {"Tomek", "X"},
                {"Tomek", "O"},
                {"Marcin", "X"},
                {"Marcin", "O"},
                {"Zbyszek", "X"},
                {"Zbyszek", "O"},
        };
    }

    @Test(dataProvider = "createPlayer")
    public void testCreatePlayerDontReturnNull(String name, String  sign) {
        Player player = new Player.PlayerBuilder().withName(name).withSign(sign).build();
        assert player != null : "Player should not be null";
    }

    @Test(dataProvider = "createPlayer")
    public void testCreatePlayer(String name, String sign) {
        Player player = new Player.PlayerBuilder().withName(name).withSign(sign).build();
        Player expected = new Player(name, Sign.valueOf(sign));
        assert player.equals(expected) : "Players should be equals";
    }

    @DataProvider
    public static Object[][] testPlayer(){
        return new Object[][] {
                {"Player 1", "X", 3},
                {"Player 1", "O", 3},
                {"Player 2", "X", 3},
                {"Player 2", "O", 3},
                {"Tomek", "X", 100},
                {"Tomek", "O", 100},
        };
    }

    @Test(dataProvider = "testPlayer")
    public void testIfPlayerPointsCanBeSet(String name, String sign, Integer newNumberOfPoints) {
        Player player = new Player.PlayerBuilder().withName(name).withSign(sign).build();
        Player expected = new Player(name, Sign.valueOf(sign), newNumberOfPoints);
        PlayerAPI playerAPI = new PlayerAPIImpl(new ArrayList<>());
        player = playerAPI.setPlayerPoints(player, newNumberOfPoints);
        assert player.equals(expected) : "Players should have should be equals points";
    }

    @Test(dataProvider = "testPlayer")
    public void testGetName(String name, String sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl(new ArrayList<>());
        Player player = new Player(name, Sign.valueOf(sign), points);
        assert playerAPI.getPlayerName(player).equals(name) : "Player should be returned";
    }

    @Test(dataProvider = "testPlayer")
    public void testGetSign(String name, String sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl(new ArrayList<>());
        Player player = new Player(name, Sign.valueOf(sign), points);
        assert playerAPI.getPlayerSign(player).equals(Sign.valueOf(sign)) : "Player sign be returned";
    }

    @Test(dataProvider = "testPlayer")
    public void testGetPoints(String name, String  sign, Integer points) {
        PlayerAPI playerAPI = new PlayerAPIImpl(new ArrayList<>());
        Player player = new Player(name, Sign.valueOf(sign), points);
        assert playerAPI.getPlayerPoints(player) == points : "Player sign be returned";
    }
}