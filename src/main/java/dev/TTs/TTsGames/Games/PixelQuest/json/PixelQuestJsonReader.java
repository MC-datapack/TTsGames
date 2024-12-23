package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.entities.GameObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dev.TTs.TTsGames.Main.logger;
import static dev.TTs.resources.Json.JsonReader.gson;

public class PixelQuestJsonReader {
    public static LootTableFormat readLootTable(GameObject mob) {
        String lootTablePath = mob.id().getLootTablePath();
        try (InputStream inputStream = PixelQuestJsonReader.class.getClassLoader().getResourceAsStream(lootTablePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: %s" + lootTablePath);
                return null;
            }

            return gson.fromJson(reader, LootTableFormat.class);
        } catch (IOException e) {
            logger.error("Failed to read Loot Table JSON file: %s", e);
            return null;
        }
    }

    public static RecipeFormats.Furnace[] getAllFurnaceRecipes() {
        File FurnaceRecipeFictionary = null;
        try {
            FurnaceRecipeFictionary = new File(Objects.requireNonNull(PixelQuestJsonReader.class.getClassLoader().getResource("data/pixel_quest/recipe/furnace")).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            logger.error("Did not find Furnace Recipe Dictionary: %s", e.getMessage());
        }
        assert FurnaceRecipeFictionary != null;
        File[] files = FurnaceRecipeFictionary.listFiles();

        assert files != null;
        List<File> fileList = new ArrayList<>(List.of(files));
        fileList.removeIf(file -> !file.getName().endsWith(".json") && !file.getName().endsWith(".json5"));

        File[] JSONFiles = fileList.toArray(new File[0]);

        RecipeFormats.Furnace[] result = new RecipeFormats.Furnace[JSONFiles.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = readFurnaceRecipe(JSONFiles[i]);
        }
        return result;
    }

    private static RecipeFormats.Furnace readFurnaceRecipe(File file) {
        try (InputStream inputStream = new FileInputStream(file);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                return null;
            }

            return gson.fromJson(reader, RecipeFormats.Furnace.class);
        } catch (IOException e) {
            logger.error("Failed to read Recipe JSON file: %s", e);
            return null;
        }
    }
}