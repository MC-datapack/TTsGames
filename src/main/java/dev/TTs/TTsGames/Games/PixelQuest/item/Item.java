package dev.TTs.TTsGames.Games.PixelQuest.item;

import java.util.HashMap;
import java.util.Map;

import static dev.TTs.TTsGames.Main.logger;
import static dev.TTs.resources.Translations.ItemDescriptions;

public sealed interface Item permits BasicItem, FoodItem {
    Map<String, Item> items = new HashMap<>();
    BasicItem toItem();

    static <T extends Item> T register(T item) {
        items.put(item.toItem().identifier().itemId(), item);
        return item;
    }

    static Item getItem(String name) {
        Item item = items.get(name);
        if (item == null) {
            logger.error("Item not found: %s", name);
        }
        return item;
    }

    class Settings {
        private float weight = 1.0F;
        private Rarity rarity = Rarity.COMMON;
        private int stackLimit = 128;
        private String description = "";

        public Settings weight(float weight) {
            this.weight = weight;
            return this;
        }

        public Settings rarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Settings stackLimit(int limit) {
            this.stackLimit = limit;
            return this;
        }

        public Settings description(int description) {
            try {
                this.description = ItemDescriptions[description];
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.warn("Did not find description");
            }
            return this;
        }

        public float getWeight() {
            return weight;
        }

        public Rarity getRarity() {
            return rarity;
        }

        public int getStackLimit() {
            return stackLimit;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "[wight: " + weight + ", rarity: " + rarity + ", stack_limit: " + stackLimit + ", description: " + description + "]";
        }
    }
}
