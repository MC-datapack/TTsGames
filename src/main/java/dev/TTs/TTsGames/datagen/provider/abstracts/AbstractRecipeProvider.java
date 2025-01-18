package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.TTsGames.Games.PixelQuest.json.ItemStackFormat;
import dev.TTs.TTsGames.Games.PixelQuest.json.RecipeFormats;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRecipeProvider extends AbstractProvider {
    private final List<RecipeFormats.Furnace> furnaceRecipes;
    private final List<RecipeFormats.Crafting> craftingRecipes;

    public AbstractRecipeProvider(String basePath, Gson gson) {
        super(basePath, gson);
        furnaceRecipes = new ArrayList<>();
        craftingRecipes = new ArrayList<>();
    }

    public FurnaceRecipeBuilder createFurnaceRecipe(Identifier id) {
        return new FurnaceRecipeBuilder(id);
    }

    public CraftingRecipeBuilder createCraftingRecipe(Identifier id) {
        return new CraftingRecipeBuilder(id);
    }

    @Override
    public void run() {
        for (RecipeFormats.Furnace furnaceRecipe : furnaceRecipes) {
            String path = basePath + "/" + new Identifier(furnaceRecipe.getName()).furnaceRecipeName();
            if (!checkDictionary(path)) continue;

            String json = gson.toJson(furnaceRecipe);

            write(path, json);
        }

        for (RecipeFormats.Crafting craftingRecipe : craftingRecipes) {
            String path = basePath + "/" + new Identifier(craftingRecipe.getName()).craftingRecipeName();
            if (!checkDictionary(path)) continue;

            String json = gson.toJson(craftingRecipe);

            write(path, json);
        }
    }

    public class FurnaceRecipeBuilder {
        private final Identifier name;
        private String input;
        private ItemStackFormat output;

        public FurnaceRecipeBuilder(Identifier name) {
            this.name = name;
        }

        public FurnaceRecipeBuilder input(Item input) {
            this.input = input.identifier().itemId();
            return this;
        }

        public FurnaceRecipeBuilder output(Item item) {
            this.output = new ItemStackFormat(item, CountProvider.fixed(1));
            return this;
        }

        public void build() {
            furnaceRecipes.add(new RecipeFormats.Furnace(name.toString(), input, output));
        }
    }

    public class CraftingRecipeBuilder {
        private final Identifier name;
        private Map<Character, Item> inputNotConverted = new HashMap<>();
        private String[] pattern = new String[9];
        private Map<Integer, String> input = new HashMap<>();
        private ItemStackFormat output;

        public CraftingRecipeBuilder(Identifier name) {
            this.name = name;
        }

        public CraftingRecipeBuilder input(char key, Item item) {
            inputNotConverted.put(key, item);
            return this;
        }

        public CraftingRecipeBuilder pattern3x3(String row1, String row2, String row3) {
            char[] row1c = row1.toCharArray();
            char[] row2c = row2.toCharArray();
            char[] row3c = row3.toCharArray();
            setInput(inputNotConverted.get(row1c[0]),inputNotConverted.get(row1c[1]), inputNotConverted.get(row1c[2]),
                    inputNotConverted.get(row2c[0]), inputNotConverted.get(row2c[1]), inputNotConverted.get(row2c[2]),
                    inputNotConverted.get(row3c[0]), inputNotConverted.get(row3c[1]), inputNotConverted.get(row3c[2]));
            return this;
        }
        public CraftingRecipeBuilder pattern2x2(String row1, String row2) {
            char[] row1c = row1.toCharArray();
            char[] row2c = row2.toCharArray();
            setInput(inputNotConverted.get(row1c[0]),inputNotConverted.get(row1c[1]), null,
                    inputNotConverted.get(row2c[0]), inputNotConverted.get(row2c[1]), null, null, null, null);
            return this;
        }
        public CraftingRecipeBuilder pattern2x1(char row1, char row2) {
            setInput(inputNotConverted.get(row1), null, null,inputNotConverted.get(row2),
                    null, null, null, null, null);
            return this;
        }

        public CraftingRecipeBuilder output(Item item, CountProvider countProvider) {
            this.output = new ItemStackFormat(item, countProvider);
            return this;
        }

        public void build() {
            craftingRecipes.add(new RecipeFormats.Crafting(name.toString(), input, output));
        }

        private void setInput(Item s1, Item s2, Item s3, Item s4, Item s5, Item s6, Item s7, Item s8, Item s9) {
            input.put(0, s1 != null ? s1.identifier().toString() : null);
            input.put(1, s2 != null ? s2.identifier().toString() : null);
            input.put(2, s3 != null ? s3.identifier().toString() : null);
            input.put(3, s4 != null ? s4.identifier().toString() : null);
            input.put(4, s5 != null ? s5.identifier().toString() : null);
            input.put(5, s6 != null ? s6.identifier().toString() : null);
            input.put(6, s7 != null ? s7.identifier().toString() : null);
            input.put(7, s8 != null ? s8.identifier().toString() : null);
            input.put(8, s9 != null ? s9.identifier().toString() : null);
        }
    }
}
