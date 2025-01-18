package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.entity.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.json.CountProvider;
import dev.TTs.TTsGames.Games.PixelQuest.json.ItemStackFormat;
import dev.TTs.TTsGames.Games.PixelQuest.json.LootTableFormat;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.Predicate;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.PredicateAdapter;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLootTableProvider extends AbstractProvider {
    private final List<LootTable> lootTables;

    public AbstractLootTableProvider(String basePath, Gson gson) {
        super(basePath, gson);
        this.lootTables = new ArrayList<>();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Predicate.class, new PredicateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public LootTableBuilder createLootTable(GameObject mob) {
        return new LootTableBuilder(mob.id());
    }

    @Override
    public void run() {
        for (LootTable lootTable : lootTables) {
            String path = basePath + "/" + lootTable.name().getLootTablePath();
            if (!checkDictionary(path)) continue;

            String json = gson.toJson(lootTable.json());

            write(path, json);
        }
    }

    public class LootTableBuilder {
        private final Identifier name;
        private final List<ItemStackFormat> pools = new ArrayList<>();

        public LootTableBuilder(Identifier name) {
            this.name = name;
        }

        public LootTableBuilder addPool(Item item, CountProvider countProvider) {
            this.pools.add(new ItemStackFormat(item, countProvider, 1.0));
            lootTables.add(new LootTable(name, pools));
            return this;
        }

        public LootTableBuilder addPool(Item item, CountProvider countProvider, double chance) {
            this.pools.add(new ItemStackFormat(item, countProvider, chance));
            lootTables.add(new LootTable(name, pools));
            return this;
        }

        public LootTableBuilder addPoolWithConditions(Predicate<? super Damageable> conditions, Item item, CountProvider countProvider, double chance) {
            this.pools.add(new ItemStackFormat(item, countProvider, chance, conditions));
            lootTables.add(new LootTable(name, pools));
            return this;
        }

        public LootTableBuilder addPoolWithConditions(Predicate<? super Damageable> conditions, Item item, CountProvider countProvider) {
            this.pools.add(new ItemStackFormat(item, countProvider, 1.0, conditions));
            lootTables.add(new LootTable(name, pools));
            return this;
        }
    }

    public record LootTable(Identifier name, List<ItemStackFormat> pools) {
        public LootTableFormat json() {
            return new LootTableFormat(name.toString(), pools.toArray(new ItemStackFormat[0]));
        }
    }
}
