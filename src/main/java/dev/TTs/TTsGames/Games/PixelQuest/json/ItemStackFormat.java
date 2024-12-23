package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings("unused")
public class ItemStackFormat {
    private String item;
    private CountProvider count;
    private double chance = 1.0;

    public ItemStack getItemStack() {
        int itemCount = count.getCount();
        if (Math.random() < chance && itemCount > 0) {
            Item retrievedItem = Item.getItem(item);
            if (retrievedItem != null) {
                return new ItemStack(retrievedItem, itemCount);
            } else {
                logger.error("Item not found: %s", item);
            }
        }
        return null;
    }
}
