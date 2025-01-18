package dev.TTs.resources;

import dev.TTs.resources.Json.Text;

import java.util.Arrays;

import static dev.TTs.TTsGames.Main.jsonReader;

@SuppressWarnings("CanBeFinal")
public class Translations {
    public Translations() {
        Text.translatable(new String[][] {
                Questions, AnswerButtons, Compliments, Insults, Results, Informations, OtherButtons,
                Games, Settings, Credits, Statistics, WrongJava, TTsGames,
                DetectiveEagle, QuestionsD, ButtonsD,
                ButtonsDT, DetectiveThunder, PixelQuest, ItemDescriptions, KeyBindingsTranslation
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
            WrongJava = jsonReader.TTsTranslationKeys("wrong_java"),
            TTsGames = jsonReader.TTsTranslationKeys("other"),
            DetectiveEagle = jsonReader.DATranslationKeys("main"),
            QuestionsD  = jsonReader.DATranslationKeys("questions"),
            ButtonsD = jsonReader.DATranslationKeys("buttons"),
            ButtonsDT  = jsonReader.DTTranslationKeys("buttons"),
            DetectiveThunder = jsonReader.DTTranslationKeys("other"),
            PixelQuest = jsonReader.PQTranslationKeys("values"),
            ItemDescriptions = jsonReader.PQTranslationKeys("item_descriptions"),
            KeyBindingsTranslation = jsonReader.PQTranslationKeys("key_bindings");

    @Override
    public String toString() {
        return String.format(
                "animal_master: {questions: %s, buttons : %s, compliments: %s, insults: %s, results: %s, informations: %s, other_buttons: %s} " +
                "tts_games: {games: %s, settings: %s, credits: %s, statistics: %s, wrong_java: %s, other: %s} " +
                "detective_eagle: {main: %s, questions: %s, buttons: %s} " +
                "detective_thunder: {buttons: %s, other: %s}, pixel_quest: {main: %s, item_descriptions: %s}",
                Arrays.toString(Questions), Arrays.toString(AnswerButtons), Arrays.toString(Compliments), Arrays.toString(Insults), Arrays.toString(Results),
                Arrays.toString(Informations), Arrays.toString(OtherButtons), Arrays.toString(Games), Arrays.toString(Settings), Arrays.toString(Credits),
                Arrays.toString(Statistics), Arrays.toString(WrongJava), Arrays.toString(TTsGames), Arrays.toString(DetectiveEagle), Arrays.toString(QuestionsD),
                Arrays.toString(ButtonsD), Arrays.toString(ButtonsDT), Arrays.toString(DetectiveThunder), Arrays.toString(PixelQuest), Arrays.toString(ItemDescriptions));
    }
}
