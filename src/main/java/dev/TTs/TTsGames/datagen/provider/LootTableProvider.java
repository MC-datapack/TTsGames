package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Fox;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Monster;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Sheep;
import dev.TTs.TTsGames.Games.PixelQuest.entity.tree.OakTree;
import dev.TTs.TTsGames.Games.PixelQuest.entity.tree.SpruceTree;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.KilledByPlayerPredicate;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractLootTableProvider;

public class LootTableProvider extends AbstractLootTableProvider {
    public LootTableProvider(String basePath, Gson gson) {
        super(basePath, gson);
    }

    @Override
    public void generate() {
        createLootTable(new Fox())
                .addPool(Items.FOX, CountProvider.uniform(1, 5))
                .addPool(Items.COOKED_FOX, CountProvider.uniform(1, 3), 0.5);
        createLootTable(new Sheep())
                .addPool(Items.MUTTON, CountProvider.fixed(4))
                .addPool(Items.WOOL, CountProvider.uniform(1, 3));
        createLootTable(new OakTree())
                .addPool(Items.OAK_WOOD, CountProvider.uniform(8, 16));
        createLootTable(new SpruceTree())
                .addPool(Items.SPRUCE_WOOD, CountProvider.uniform(12, 24));
        createLootTable(new Monster())
                .addPoolWithConditions(new KilledByPlayerPredicate(), Items.STICK, CountProvider.uniform(2, 4));
    }
}
