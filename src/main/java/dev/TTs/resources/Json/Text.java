package dev.TTs.resources.Json;

import dev.TTs.resources.Configs;

import java.util.Map;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public final class Text {
    private static Map<String, String> translations = jsonReader.readTranslationJsonFile(jsonReader.MainJSON.getLanguageFile(language));
    public static final String USERNAME_PLACEHOLDER = "௹⨌{Username}";

    public static String translatable(String string) {
        String translation = translations.getOrDefault(string, string);
        return translation.contains(USERNAME_PLACEHOLDER) ? translation.replace(USERNAME_PLACEHOLDER, Username) : translation;
    }

    public static String[] translatable(String[] string) {
        for (int i = 0; i < string.length; i++) {
            string[i] = Text.translatable(string[i]);
        }
        return string;
    }

    public static String[][] translatable(String[][] string) {
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < string[i].length; j++) {
                string[i][j] = Text.translatable(string[i][j]);
            }
        }
        return string;
    }

    public static void langChanged() {
        language = configLoader.get(Configs.LANGUAGE);
        translations = jsonReader.readTranslationJsonFile(jsonReader.MainJSON.getLanguageFile(language));
    }
}
