package dev.TTs.TTsGames.Resources;

import dev.TTs.TTsGames.Resources.Json.Text;

import static dev.TTs.TTsGames.Main.jsonReader;

public final class Translations {
    public Translations() {
        translate();
    }

    public static String[]
            Questions = jsonReader.AMTranslationKeys("questions"),
            AnswerButtons  = jsonReader.AMTranslationKeys("buttons"),
            Compliments  = jsonReader.AMTranslationKeys("compliments"),
            Insults  = jsonReader.AMTranslationKeys("insults"),
            Results  = jsonReader.AMTranslationKeys("results"),
            Informations = jsonReader.AMTranslationKeys("informations"),
            OtherButtons = jsonReader.AMTranslationKeys("other_buttons"),
            Games = jsonReader.TTsTranslationKeys("games"),
            Settings = jsonReader.TTsTranslationKeys("settings"),
            Credits = jsonReader.TTsTranslationKeys("credits"),
            Statistics = jsonReader.TTsTranslationKeys("statistics"),
            TTsGames = jsonReader.TTsTranslationKeys("other"),
            DetectiveEagle = jsonReader.DATranslationKeys("main"),
            QuestionsD  = jsonReader.DATranslationKeys("questions"),
            ButtonsD = jsonReader.DATranslationKeys("buttons"),
            ButtonsDT  = jsonReader.DTTranslationKeys("buttons"),
            DetectiveThunder = jsonReader.DTTranslationKeys("other");

    private void translate() {
        translateArray( Questions);
        translateArray( AnswerButtons);
        translateArray(Compliments);
        translateArray( Insults);
        translateArray(Results);
        translateArray( Informations);
        translateArray( OtherButtons);
        translateArray(Games);
        translateArray(Settings);
        translateArray(Credits);
        translateArray(Statistics);
        translateArray( TTsGames);
        translateArray(DetectiveEagle);
        translateArray( QuestionsD);
        translateArray( ButtonsD);
        translateArray(ButtonsDT);
        translateArray(DetectiveThunder);
    }

    private void translateArray(String[] arrayKey) {
        for (int i = 0; i < arrayKey.length; i++) {
            arrayKey[i] = Text.translatable(arrayKey[i]);
        }
    }
}
