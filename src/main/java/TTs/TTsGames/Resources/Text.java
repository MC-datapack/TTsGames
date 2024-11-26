package TTs.TTsGames.Resources;


import java.util.Map;

import static TTs.TTsGames.Main.*;

public class Text {
    private static final Map<String, String> languageMap = jsonReader.mainJSON != null ? jsonReader.mainJSON.getLanguageMap() : null;
    private static String language() {return languageMap.getOrDefault(configLoader.getLanguage(), null);}
    public static final Map<String, String> translations = jsonReader.TranslationJsonFile(language());

    public static String translatable(String string) {
        if (translations.getOrDefault(string, string).contains("௹⨌{Username}")) {
            return translations.getOrDefault(string, string).replace("௹⨌{Username}", Username);
        }
        else {
            return translations.getOrDefault(string, string);
        }
    }

    public static String fixed(String string) {
        if (translations.getOrDefault(string, string).contains("௹⨌{Username}")) {
            return string.replace("௹⨌{Username}", Username);
        }
        else {
            return string;
        }
    }
}
