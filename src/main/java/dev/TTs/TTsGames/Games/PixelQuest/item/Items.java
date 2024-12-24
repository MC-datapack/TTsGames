package dev.TTs.TTsGames.Games.PixelQuest.item;

public class Items {
    public static final Item FOX = Item.registerFoodItem("fox", 5, new Item.Settings()
            .category(Item.Category.FOOD)
            .weight(0.75F)
            .stackLimit(64)
            .description(0));
    public static final Item COOKED_FOX = Item.registerFoodItem("cooked_fox", 10, new Item.Settings()
            .category(Item.Category.FOOD)
            .weight(0.75F)
            .stackLimit(64)
            .rarity(Rarity.UNCOMMON));
    public static final Item MUTTON = Item.registerFoodItem("mutton", 7, new Item.Settings()
            .category(Item.Category.FOOD)
            .stackLimit(64)
            .description(0));
    public static final Item COOKED_MUTTON = Item.registerFoodItem("cooked_mutton", 14, new Item.Settings()
            .category(Item.Category.FOOD)
            .stackLimit(64)
            .rarity(Rarity.UNCOMMON));
    public static final Item WOOL = Item.registerBasicItem("wool", new Item.Settings()
            .weight(0.9F));

    public static void registerItems() {}
}
