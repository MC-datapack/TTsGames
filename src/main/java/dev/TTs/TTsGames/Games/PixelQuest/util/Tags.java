package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.TTsGames.Games.PixelQuest.json.TagType;

public class Tags {
    public static class Item {
        public static final TagKey FURNACE_FUEL = register("furnace_fuel");
        public static final TagKey WOOD = register("wood");

        private static TagKey register(String name) {
            return TagKey.of(TagType.ITEM, new Identifier(name));
        }
    }
}
