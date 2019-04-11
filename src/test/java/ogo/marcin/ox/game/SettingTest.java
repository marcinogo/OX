package ogo.marcin.ox.game;

import java.util.Optional;
import ogo.marcin.ox.board.Sign;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Marcin Ogorzalek
 */
@Test
public class SettingTest {

  @DataProvider
  public static Object[][] createSettings() {
    return new Object[][]{
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
    Setting setting = new Setting.SettingsBuilder().withWinConditionInRange(winCondition)
        .withDefaultSing(sign)
        .withNumberOfRounds(numberOfRounds)
        .build();
    Optional<Setting> optionalSettings = Optional.ofNullable(setting);
    assert optionalSettings.isPresent() : "Setting should be created";
  }
}