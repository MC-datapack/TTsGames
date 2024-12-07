package dev.TTs.TTsGames.Resources;

import java.util.Map;

import static dev.TTs.TTsGames.Main.*;

public final class Text {
    private static final Map<String, String> languageMap = jsonReader.mainJSON.getLanguageFiles();
    private static String language() {
        return languageMap.getOrDefault(configLoader.getLanguage(), null);
    }
    protected static final Map<String, String> translations = jsonReader.TranslationJsonFile(language());

    protected static String translatable(String string) {
        if (translations.getOrDefault(string, string).contains("௹⨌{Username}")) {
            return translations.getOrDefault(string, string).replace("௹⨌{Username}", Username);
        }
        else {
            return translations.getOrDefault(string, string);
        }
    }
}
