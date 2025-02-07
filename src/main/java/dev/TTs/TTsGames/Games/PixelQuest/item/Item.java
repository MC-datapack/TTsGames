package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.util.Identifier;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;
import static dev.TTs.TTsGames.Main.logger;
import static dev.TTs.resources.Translations.ItemDescriptions;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;
    private static final Map<Identifier, Item> items = new HashMap<>();

    Identifier identifier;
    Item.Settings settings;

    public static Item registerItem(String name, Settings settings) {
        return register(new Item(Identifier.ofPixelQuest(name), settings));
    }

    public static FoodItem registerFoodItem(String name, int saturation, Settings settings) {
        return Item.register(new FoodItem(Identifier.ofPixelQuest(name), saturation, settings));
    }

    public static WeaponItem registerWeapon(String name, int damage, Settings settings) {
        return Item.register(new WeaponItem(Identifier.ofPixelQuest(name), damage, settings));
    }

    public static <T extends Item> T register(T item) {
        items.put(item.identifier(), item);
        return item;
    }

    public static Item getItem(Identifier id) {
        if (id != null) {
            Item item = items.get(id);
            if (item == null) {
                logger.error("Items not found: %s", id);
            }
            return item;
        } else {
            return null;
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(identifier());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Identifier identifier = (Identifier) in.readObject();
        Item item = getItem(identifier);
        if (item == null) {
            logger.error("Items not found during deserialization: %s", identifier);
            this.identifier = null;
            this.settings = null;
        } else {
            this.identifier = item.identifier;
            this.settings = item.settings;
        }
    }

    public Item(Identifier identifier, Item.Settings settings) {
        this.identifier = identifier;
        this.settings = settings;
    }

    public Identifier identifier() {
        return identifier;
    }

    public Item.Settings settings() {
        return settings;
    }

    @Override
    public String toString() {
        return "Items[" +
                "identifier=" + identifier + ", " +
                "settings=" + settings + ']';
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Item item)) return false;
        return item.identifier == this.identifier;
    }

    public static class Settings {
        private float weight = 1.0F;
        private Rarity rarity = Rarity.COMMON;
        private int stackLimit = 64;
        private String description = "";
        private Category category = Category.MISC;

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

        public Settings category(Category category) {
            this.category = category;
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

        public Category getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return "[weight: " + weight + ", rarity: " + rarity + ", stack_limit: " + stackLimit + ", description: " + description + ", category: " + category.getName() + "]";
        }
    }

    public enum Category {
        MISC("Misc"), FOOD("Food"), WOOD("Wood"), WEAPON("Weapon");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
