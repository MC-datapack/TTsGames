package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.util.Identifier;

public class Tags {
    public static class Items {
        public static final TagKey<Item> FURNACE_FUEL = register("furnace_fuel");
        public static final TagKey<Item> WOOD = register("wood");
        public static final TagKey<Item> SWORDS = register("swords");

        private static TagKey<Item> register(String name) {
            return TagKey.of(TagType.ITEM, Identifier.ofPixelQuest(name));
        }
    }
}
