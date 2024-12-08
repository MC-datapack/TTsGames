package dev.TTs.TTsGames.Resources.Json;

import java.util.Map;

import static dev.TTs.TTsGames.Main.*;

public final class Text {
    private static final String language = jsonReader.MainJSON.getLanguageFile(configLoader.getLanguage());
    private static final Map<String, String> translations = jsonReader.readTranslationJsonFile(language);
    private static final String USERNAME_PLACEHOLDER = "௹⨌{Username}";

    public static String translatable(String string) {
        String translation = translations.getOrDefault(string, string);
        return translation.contains(USERNAME_PLACEHOLDER) ? translation.replace(USERNAME_PLACEHOLDER, Username) : translation;
    }
}
