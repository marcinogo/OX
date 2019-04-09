package ogo.marcin.ox.io;

import ogo.marcin.ox.game.CoordinateNotFreeException;
import ogo.marcin.ox.game.Coordinates;
import ogo.marcin.ox.game.Judge;
import ogo.marcin.ox.io.Localization.LanguageKey;

/**
 * @author Marcin Ogorzalek
 */
public class InputValidation {

  void ifUserInputEqualsQuitEndApp(String userInput) {
    if (userInput.equalsIgnoreCase("quit")) {
      throw new QuitGameException(Localization.getLocalizedText(LanguageKey.QUIT));
    }
  }

  void validateCoordinate(Judge judge, Coordinates coordinates) throws CoordinateNotFreeException {
    if (!judge.isPlayerSignSetOnFreeSpace(coordinates)) {
      throw new CoordinateNotFreeException(Localization.getLocalizedText(
          Localization.LanguageKey.COORDINATE_NOT_FREE_EXCEPTION
      ));
    }
  }

  boolean isIntegerBetween(int number, int minNumber, int maxNumber) {
    return number >= minNumber && number <= maxNumber;
  }
}
