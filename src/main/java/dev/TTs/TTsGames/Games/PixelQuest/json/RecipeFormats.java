package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;

@SuppressWarnings("unused")
public class RecipeFormats {
    public static class Furnace {
        private String input;
        private ItemStackFormat output;

        public Item getInput() {
            return Item.getItem(input);
        }

        public ItemStack getOutput() {
            return output.getItemStack();
        }
    }
}
