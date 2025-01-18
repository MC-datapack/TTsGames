package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.util.Tags;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractItemTagProvider;

public class ItemTagProvider extends AbstractItemTagProvider {
    public ItemTagProvider(String basePath, Gson gson) {
        super(basePath, gson);
    }

    @Override
    public void generate() {
        createTag(Tags.Item.WOOD)
                .add(Items.OAK_WOOD, Items.SPRUCE_WOOD);
        createTag(Tags.Item.FURNACE_FUEL)
                .addTag(Tags.Item.WOOD)
                .add(Items.WOOL);
    }
}
