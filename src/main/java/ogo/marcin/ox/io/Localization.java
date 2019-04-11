package ogo.marcin.ox.io;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Contains logic for bilingualism of application
 *
 * @author Marcin Ogorzalek
 */
public class Localization {

  private static ResourceBundle resourceBundle;

  static {
    setResourceBundleLanguage("POLISH");
  }

  /**
   * Allow to choose application language. Default option is english.
   *
   * @param language name of a language choose by a user
   */
  public static void setResourceBundleLanguage(String language) {
    switch (language.toUpperCase()) {
      case "POLISH":
        resourceBundle = Language.POLISH.getResourceBundle();
        break;
      case "ENGLISH":
        resourceBundle = Language.ENGLISH.getResourceBundle();
        break;
      default:
        new OutputImpl(System.err).print(LanguageKey.NO_LANGUAGE_EXCEPTION);
        resourceBundle = Language.ENGLISH.getResourceBundle();
        new OutputImpl(System.err).print(LanguageKey.NO_LANGUAGE_EXCEPTION);
        break;
    }
  }

  /**
   * Return localized version of text with given key from file .properties
   *
   * @return localized text
   */
  public static String getLocalizedText(LanguageKey languageKey) {
    return Localization.resourceBundle.getString(languageKey.name());
  }

  enum Language {
    POLISH(ResourceBundle.getBundle("OX", new Locale("pl", "PL"))),
    ENGLISH(ResourceBundle.getBundle("OX", new Locale("en", "EN")));

    private final ResourceBundle resourceBundle;

    Language(ResourceBundle resourceBundle) {
      this.resourceBundle = resourceBundle;
    }

    ResourceBundle getResourceBundle() {
      return resourceBundle;
    }
  }

  /**
   * Contains keys for all possible text messages defined in OX_en_EN.properties and
   * OX_pl_PL.properties
   */
  public enum LanguageKey {
    //        Language configuration
    CHOOSE_LANGUAGE, NO_LANGUAGE_EXCEPTION,
    //        Game creation
    BOARD_SIZE, WIN_CONDITION, BOARD_SIZE_EXCEPTION,
    WIN_CONDITION_EXCEPTION, ENTER_INT,
    //        Player creation
    GET_PLAYER_NAME, GET_PLAYER_SIGN, GET_SECOND_PLAYER_SIGN,
    PLAYER_NAME_EXCEPTION, PLAYER_SIGN_EXCEPTION,
    //        Player move
    PLAYER_WITH_MOVE, ENTER_COORDINATE,
    PLAYER_MOVE_EXCEPTION, COORDINATE_NOT_FREE_EXCEPTION,
    //        Match resolution
    START_OF_MATCH, END_OF_MATCH,
    DRAW_IN_MATCH, WINNER_OF_MATCH,
    //        Game resolution
    DRAW_OF_GAME, WINNER_OF_GAME, QUIT,
    //        Input exception
    NOT_INTEGER_EXCEPTION,
  }
}
