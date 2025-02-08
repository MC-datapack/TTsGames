package dev.TTs.TTsGames.Games.PixelQuest.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.PredicateAdapter;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.Predicate;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagType;
import dev.TTs.util.Identifier;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagKey;
import dev.TTs.util.RandomUtil;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static dev.TTs.TTsGames.Games.PixelQuest.util.TagType.ITEM;
import static dev.TTs.TTsGames.Main.jsonReader;
import static dev.TTs.TTsGames.Main.logger;

public class PixelQuestJsonReader {

    private static final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Predicate[].class, new PredicateAdapter())
            .setPrettyPrinting()
            .create();

    public static Identifier getRandomChestLootTable() {
        File chestLootTableDict;
        try {
            chestLootTableDict = new File(PixelQuestJsonReader.class.getClassLoader().getResource("/data/pixel_quest/loot_table/chests").toURI());
        } catch (URISyntaxException | NullPointerException e) {
            logger.error("Failed creating Chest Loot Table dict: %s", e);
            return Identifier.of(null, null);
        }
        File[] files = chestLootTableDict.listFiles();

        assert files != null;
        List<File> fileList = new ArrayList<>(List.of(files));
        fileList.removeIf(file -> !file.getName().endsWith("json") && !file.getName().endsWith("json5"));

        List<Identifier> ids = new ArrayList<>();

        fileList.forEach(file -> ids.add(name(file)));

        return logger.debug(RandomUtil.getRandom(ids));
    }

    private static Identifier name(File file) {
        return Identifier.of("pixel_quest", "chests/" + (file.getName().endsWith("json") ? file.getName().replace(".json", "") : file.getName().replace(".json5", "")));
    }

    public static LootTableFormat readLootTable(Entity mob) {
        return readLootTable(mob.id());
    }

    public static LootTableFormat readLootTable(Identifier id) {
        logger.debug("Id: %s", id);
        String lootTablePath = id.getLootTablePath();
        try (InputStream inputStream = PixelQuestJsonReader.class.getClassLoader().getResourceAsStream(lootTablePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: %s", lootTablePath);
                return null;
            }

            return gson.fromJson(reader, LootTableFormat.class);
        } catch (IOException e) {
            logger.error("Failed to read Loot Table JSON file: %s", e);
            return null;
        }
    }

    public static RecipeFormats.Furnace[] getAllFurnaceRecipes() {
        File furnaceRecipeDictionary = null;
        try {
            furnaceRecipeDictionary = new File(Objects.requireNonNull(PixelQuestJsonReader.class.getClassLoader().getResource("data/pixel_quest/recipe/furnace")).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            logger.error("Did not find Furnace Recipe Dictionary: %s", e.getMessage());
        }
        assert furnaceRecipeDictionary != null;
        File[] files = furnaceRecipeDictionary.listFiles();

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
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            return gson.fromJson(reader, RecipeFormats.Furnace.class);
        } catch (IOException e) {
            logger.error("Failed to read Recipe JSON file: %s", e);
            return null;
        }
    }

    public static RecipeFormats.Crafting[] getAllCraftingTableRecipes() {
        File craftingRecipeDictionary = null;
        try {
            craftingRecipeDictionary = new File(Objects.requireNonNull(PixelQuestJsonReader.class.getClassLoader().getResource("data/pixel_quest/recipe/crafting")).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            logger.error("Did not find Crafting Recipe Dictionary: %s", e.getMessage());
        }
        assert craftingRecipeDictionary != null;
        File[] files = craftingRecipeDictionary.listFiles();

        assert files != null;
        List<File> fileList = new ArrayList<>(List.of(files));
        fileList.removeIf(file -> !file.getName().endsWith(".json") && !file.getName().endsWith(".json5"));

        File[] JSONFiles = fileList.toArray(new File[0]);

        RecipeFormats.Crafting[] result = new RecipeFormats.Crafting[JSONFiles.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = readCraftingRecipe(JSONFiles[i]);
        }
        return result;
    }

    private static RecipeFormats.Crafting readCraftingRecipe(File file) {
        try (InputStream inputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            return gson.fromJson(reader, RecipeFormats.Crafting.class);
        } catch (IOException e) {
            logger.error("Failed to read Recipe JSON file: %s", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] readTag(TagKey<T> key) {
        String[] raw = jsonReader.ValuesStandard("values", key.id().getTag(key.type()), String[].class);
        if (raw == null) return null;

        List<T> result = new ArrayList<>();

        if (key.type().equals(ITEM)) {
            for (String string : raw) {
                if (string.startsWith("#")) {
                    String referenceName = string.substring(1);
                    T[] referencedItems = readTagByName(key.type(), referenceName);
                    if (referencedItems != null) {
                        result.addAll(Arrays.asList(referencedItems));
                    }
                } else {
                    result.add((T) Item.getItem(Identifier.of(string)));
                }
            }
        } else {
            throw new IllegalArgumentException("Unsupported TagType: " + key.type());
        }

        T[] array = (T[]) java.lang.reflect.Array.newInstance(key.type().getClazz(), result.size());
        return result.toArray(array);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] readTagByName(TagType<T> type, String name) {
        Identifier path = getPathByName(name);
        return readTag(TagKey.of(type, path));
    }

    public static Identifier getPathByName(String name) {
        return Identifier.of(name);
    }
}
