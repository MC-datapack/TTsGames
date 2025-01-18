package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractRecipeProvider;

public class RecipeProvider extends AbstractRecipeProvider {
    public RecipeProvider(String basePath, Gson gson) {
        super(basePath, gson);
    }

    @Override
    public void generate() {
        createFurnaceRecipe(new Identifier("cooked_fox"))
                .input(Items.FOX)
                .output(Items.COOKED_FOX)
                .build();
        createFurnaceRecipe(new Identifier("cooked_mutton"))
                .input(Items.MUTTON)
                .output(Items.COOKED_MUTTON)
                .build();
        createCraftingRecipe(new Identifier("stick"))
                .input('A', Items.STICK)
                .pattern2x1('A', 'A')
                .output(Items.STICK, CountProvider.fixed(4))
                .build();
        createCraftingRecipe(new Identifier("wooden_sword_oak"))
                .input('A', Items.OAK_WOOD)
                .input('B', Items.STICK)
                .pattern2x2("AA", "BB")
                .output(Items.WOODEN_SWORD, CountProvider.fixed(1))
                .build();
        createCraftingRecipe(new Identifier("wooden_sword_spruce"))
                .input('A', Items.SPRUCE_WOOD)
                .input('B', Items.STICK)
                .pattern2x2("AA", "BB")
                .output(Items.WOODEN_SWORD, CountProvider.fixed(1))
                .build();
    }
}
