package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.util.Tags;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractTagProvider;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Logger;
import dev.TTs.lang.Provider;

@Provider(name = "Item Tag Provider")
public class ItemTagProvider extends AbstractTagProvider<Item> {
    public ItemTagProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
    }

    @Override
    public void generate() {
        createTag(Tags.Items.WOOD)
                .add(Items.OAK_WOOD, Items.SPRUCE_WOOD);
        createTag(Tags.Items.FURNACE_FUEL)
                .addTag(Tags.Items.WOOD)
                .add(Items.WOOL);
        createTag(Tags.Items.SWORDS)
                .add(Items.WOODEN_SWORD, Items.SPRUCE_WOOD, Items.WOOL);
    }
}
