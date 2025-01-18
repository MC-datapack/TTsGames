package dev.TTs.TTsGames;

import dev.TTs.TTsGames.datagen.Pack;
import dev.TTs.TTsGames.datagen.provider.ItemTagProvider;
import dev.TTs.TTsGames.datagen.provider.LootTableProvider;
import dev.TTs.TTsGames.datagen.provider.RecipeProvider;

public class Datagen {
    public static void main(String[] args) {
        Main.load(args);
        Pack pack = new Pack();

        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(LootTableProvider::new);
        pack.addProvider(RecipeProvider::new);
    }
}
