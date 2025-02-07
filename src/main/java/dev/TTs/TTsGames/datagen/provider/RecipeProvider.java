package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Provider;
import dev.TTs.util.Identifier;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractRecipeProvider;
import dev.TTs.lang.Logger;

@Provider(name = "Recipe Provider")
public class RecipeProvider extends AbstractRecipeProvider {
    public RecipeProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
    }

    @Override
    public void generate() {
        createFurnaceRecipe(Identifier.ofPixelQuest("cooked_fox"))
                .input(Items.FOX)
                .output(Items.COOKED_FOX)
                .build();
        createFurnaceRecipe(Identifier.ofPixelQuest("cooked_mutton"))
                .input(Items.MUTTON)
                .output(Items.COOKED_MUTTON)
                .build();
        createCraftingRecipe(Identifier.ofPixelQuest("stick"))
                .input('A', Items.STICK)
                .pattern2x1('A', 'A')
                .output(Items.STICK, CountProvider.fixed(4))
                .build();
        createCraftingRecipe(Identifier.ofPixelQuest("wooden_sword_oak"))
                .input('A', Items.OAK_WOOD)
                .input('B', Items.STICK)
                .pattern2x2("AA", "BB")
                .output(Items.WOODEN_SWORD, CountProvider.fixed(1))
                .build();
        createCraftingRecipe(Identifier.ofPixelQuest("wooden_sword_spruce"))
                .input('A', Items.SPRUCE_WOOD)
                .input('B', Items.STICK)
                .pattern2x2("AA", "BB")
                .output(Items.WOODEN_SWORD, CountProvider.fixed(1))
                .build();
    }
}
