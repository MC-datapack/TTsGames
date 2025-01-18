package dev.TTs.TTsGames.Games.PixelQuest.item;

import static dev.TTs.TTsGames.Games.PixelQuest.item.Item.*;

public class Items {
    public static final Item FOX = registerFoodItem("fox", 5, new Settings()
            .category(Item.Category.FOOD)
            .weight(0.75F)
            .description(0));
    public static final Item COOKED_FOX = registerFoodItem("cooked_fox", 10, new Settings()
            .category(Item.Category.FOOD)
            .weight(0.75F)
            .rarity(Rarity.UNCOMMON));
    public static final Item MUTTON = registerFoodItem("mutton", 7, new Settings()
            .category(Item.Category.FOOD)
            .description(0));
    public static final Item COOKED_MUTTON = registerFoodItem("cooked_mutton", 14, new Settings()
            .category(Item.Category.FOOD)
            .rarity(Rarity.UNCOMMON));
    public static final Item WOOL = registerItem("wool", new Settings()
            .weight(0.9F));
    public static final Item OAK_WOOD = registerItem("oak_wood", new Settings()
            .category(Item.Category.WOOD)
            .weight(1.1F));
    public static final Item SPRUCE_WOOD = registerItem("spruce_wood", new Settings()
            .category(Item.Category.WOOD)
            .rarity(Rarity.UNCOMMON)
            .weight(1.3F));
    public static final Item STICK = registerItem("stick", new Settings()
            .category(Category.WOOD)
            .weight(0.5F));
    public static final Item WOODEN_SWORD = registerWeapon("wooden_sword", 3, new Settings()
            .description(1)
            .category(Item.Category.WEAPON)
            .stackLimit(1));

    public static void load() {}
}
