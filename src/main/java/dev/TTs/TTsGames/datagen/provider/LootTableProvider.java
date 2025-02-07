package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Tree;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Fox;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Monster;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Sheep;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.KilledByPlayerPredicate;
import dev.TTs.TTsGames.Games.PixelQuest.util.Tags;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractLootTableProvider;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Logger;
import dev.TTs.lang.Provider;
import dev.TTs.util.RandomUtil;

@Provider(name = "Loot Table Provider")
public class LootTableProvider extends AbstractLootTableProvider {
    public LootTableProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
        logger.debug(RandomUtil.getRandom(Tags.Items.SWORDS));
    }

    @Override
    public void generate() {
        createLootTable(new Fox().id())
                .addPool(Items.FOX, CountProvider.uniform(1, 5))
                .addPool(Items.COOKED_FOX, CountProvider.uniform(1, 3), 0.5);
        createLootTable(new Sheep().id())
                .addPool(Items.MUTTON, CountProvider.fixed(4))
                .addPool(Items.WOOL, CountProvider.uniform(1, 3));
        createLootTable(Tree.Variants.OAK.lootTablePath())
                .addPool(Items.OAK_WOOD, CountProvider.uniform(8, 16))
                .addPool(Items.STICK, CountProvider.uniform(2, 8));
        createLootTable(Tree.Variants.SPRUCE.lootTablePath())
                .addPool(Items.SPRUCE_WOOD, CountProvider.uniform(12, 24))
                .addPool(Items.STICK, CountProvider.uniform(2, 8));
        createLootTable(new Monster().id())
                .addPoolWithConditions(Items.STICK, CountProvider.uniform(2, 4), new KilledByPlayerPredicate());

        createChestLootTable("mutton")
                .addPool(Items.MUTTON, CountProvider.uniform(4, 8), 0.75)
                .addPool(Items.COOKED_MUTTON, CountProvider.uniform(6, 12), 0.5);
        createChestLootTable("op")
                .addPools(Tags.Items.SWORDS, CountProvider.fixed(1), 0.125)
                .addPool(Items.SPRUCE_WOOD, CountProvider.uniform(10, 64));
    }
}
