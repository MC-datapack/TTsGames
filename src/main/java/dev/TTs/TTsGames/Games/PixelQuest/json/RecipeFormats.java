package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class RecipeFormats {
    public static class Furnace {
        private String name;
        private String input;
        private ItemStackFormat output;

        public Furnace(String name, String input, ItemStackFormat output) {
            this.name = name;
            this.input = input;
            this.output = output;
        }

        public String getName() {
            return name;
        }

        public Item getInput() {
            return Item.getItem(Identifier.of(input));
        }

        public ItemStack getOutput() {
            return output.getItemStack();
        }
    }
    public static class Crafting {
        private String name;
        private Map<Integer, String> pattern;
        private ItemStackFormat output;

        public Crafting(String name, Map<Integer, String> pattern, ItemStackFormat output) {
            this.name = name;
            this.pattern = pattern;
            this.output = output;
        }

        public String getName() {
            return name;
        }

        public Map<Integer, Item> getInput() {
            Map<Integer, Item> result = new HashMap<>();
            for (int i = 0; i < 9; i++) {
                result.put(i, Item.getItem(pattern.get(i) != null ? Identifier.of(pattern.get(i)) : null));
            }
            return result;
        }

        public ItemStack getOutput() {
            return output.getItemStack();
        }
    }
}
