package dev.TTs.resources;

import dev.TTs.resources.Json.Text;

import static dev.TTs.TTsGames.Main.jsonReader;

public final class Translations {
    public Translations() {
        Text.translatable(new String[][] {
                Questions, AnswerButtons, Compliments, Insults, Results, Informations, OtherButtons,
                Games, Settings, Credits, Statistics, TTsGames,
                DetectiveEagle, QuestionsD, ButtonsD,
                ButtonsDT, DetectiveThunder
        });
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
}
