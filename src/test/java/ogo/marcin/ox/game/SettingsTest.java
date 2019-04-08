package ogo.marcin.ox.game;

import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class SettingsTest {
    @DataProvider
    public static Object[][] createSettings(){
        return new Object[][] {
                {3, 3, Sign.DEFAULT},
                {4, 3, Sign.X},
                {6, 15, Sign.O},
                {30, 1, Sign.DEFAULT},
                {15, 20, Sign.X},
                {29, 3, Sign.O}
        };
    }

    @Test(dataProvider = "createSettings")
    public void testCreateSettings(int winCondition, int numberOfRounds, Sign sign) {
        Settings settings = new Settings.SettingsBuilder().withWinConditionInRange(winCondition)
                .withDefaultSing(sign)
                .withNumberOfRounds(numberOfRounds)
                .build();
        Optional<Settings> optionalSettings = Optional.ofNullable(settings);
        assert optionalSettings.isPresent() : "Setting should be created";
    }
}