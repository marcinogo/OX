package ogo.marcin.ox.io;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Marcin Ogorzalek
 */
public class Localization {
    public static ResourceBundle setLanguage(String language) {
        try {
            return Language.valueOf(language.toUpperCase()).getResourceBundle();
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("No such language, english set as default");
            return Language.ENGLISH.getResourceBundle();
        }
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

    public enum Key {
        INVALID_INPUT_FOR_INT, ENTER_COORDINATE, BOARD_SIZE, WIN_CONDITION
    }
}
