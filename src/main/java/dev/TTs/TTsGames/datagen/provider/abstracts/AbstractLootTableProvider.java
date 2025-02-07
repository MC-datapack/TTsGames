package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.TTsGames.Games.PixelQuest.json.ItemStackFormat;
import dev.TTs.TTsGames.Games.PixelQuest.json.LootTableFormat;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.Predicate;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagKey;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.util.Identifier;
import dev.TTs.lang.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLootTableProvider extends AbstractProvider {
    private final List<LootTable> lootTables;

    public AbstractLootTableProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
        this.lootTables = new ArrayList<>();
    }

    public LootTableBuilder createLootTable(Identifier identifier) {
        return new LootTableBuilder(identifier);
    }

    public LootTableBuilder createChestLootTable(String name) {
        return createLootTable(Identifier.of("chests/" + name));
    }

    @Override
    public void run() {
        for (LootTable lootTable : lootTables) {
            write(lootTable.name().getLootTablePath(), lootTable.json());
        }
    }

    @SuppressWarnings("DataFlowIssue")
    public class LootTableBuilder {
        private final Identifier name;
        private final List<ItemStackFormat> pools = new ArrayList<>();

        public LootTableBuilder(Identifier name) {
            this.name = name;
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPool(Item item, CountProvider countProvider) {
            this.pools.add(new ItemStackFormat(item, countProvider, 1.0));
            lootTables.add(new LootTable(name, pools));
            return this;
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPool(Item item, CountProvider countProvider, double chance) {
            this.pools.add(new ItemStackFormat(item, countProvider, chance));
            lootTables.add(new LootTable(name, pools));
            return this;
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPoolWithConditions(Item item, CountProvider countProvider, double chance, Predicate<?>... conditions) {
            this.pools.add(new ItemStackFormat(item, countProvider, chance, conditions));
            lootTables.add(new LootTable(name, pools));
            return this;
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPoolWithConditions(Item item, CountProvider countProvider, Predicate<?>... conditions) {
            return addPoolWithConditions(item, countProvider, 1.0, conditions);
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPools(TagKey<Item> item, CountProvider countProvider, double chance) {
            for (Item ite : PixelQuestJsonReader.readTag(item)) {
                addPool(ite, countProvider, chance);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPools(TagKey<Item> item, CountProvider countProvider) {
            return addPools(item, countProvider, 1.0);
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPoolsWithConditions(TagKey<Item> item, CountProvider countProvider, double chance, Predicate<?>... conditions) {
            for (Item ite : PixelQuestJsonReader.readTag(item)) {
                addPoolWithConditions(ite, countProvider, chance, conditions);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public LootTableBuilder addPoolsWithConditions(TagKey<Item> item, CountProvider countProvider, Predicate<?>... conditions) {
            return addPoolsWithConditions(item, countProvider, 1.0, conditions);
        }
    }

    public record LootTable(Identifier name, List<ItemStackFormat> pools) {
        public LootTableFormat json() {
            return new LootTableFormat(name.toString(), pools.toArray(new ItemStackFormat[0]));
        }
    }
}
