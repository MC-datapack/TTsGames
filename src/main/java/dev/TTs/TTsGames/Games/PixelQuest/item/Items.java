package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import static dev.TTs.TTsGames.Main.logger;

public class Items {
    public static final Item FOX = Item.register(new FoodItem(new Identifier("fox"), 5, new Item.Settings()
            .weight(0.75F)
            .stackLimit(64)
            .description(0)));
    public static final Item COOKED_FOX = Item.register(new FoodItem(new Identifier("cooked_fox"), 10, new Item.Settings()
            .weight(0.75F)
            .stackLimit(64)
            .rarity(Rarity.UNCOMMON)));
    public static final Item MUTTON = Item.register(new FoodItem(new Identifier("mutton"), 7, new Item.Settings()
            .stackLimit(64)
            .description(0)));
    public static final Item COOKED_MUTTON = Item.register(new FoodItem(new Identifier("cooked_mutton"), 14, new Item.Settings()
            .stackLimit(64)
            .rarity(Rarity.UNCOMMON)));
    public static final Item WOOL = Item.register(new BasicItem(new Identifier("wool"), new Item.Settings()
            .weight(0.9F)));

    public static void registerItems() {}
}
