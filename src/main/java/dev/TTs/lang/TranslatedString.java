package dev.TTs.lang;

import static dev.TTs.resources.Translations.Settings;

public class TranslatedString {
    public static String valueOf(boolean bool) {
        return bool ? Settings[6] : Settings[7];
    }
}
