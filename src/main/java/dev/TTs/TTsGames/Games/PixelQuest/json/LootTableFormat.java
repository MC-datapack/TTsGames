package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.entities.ItemEntity;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;

@SuppressWarnings({"unused", "MismatchedReadAndWriteOfArray"})
public class LootTableFormat {
    private ItemStackFormat[] tables;

    public ItemStack getItemStack(int index) {
        return tables[index].getItemStack();
    }

    public ItemEntity drop(int index, int x, int y) {
        ItemStack itemStack = getItemStack(index);
        if (itemStack != null) {
            return new ItemEntity(itemStack, x, y);
        }
        return null;
    }

    public ItemEntity[] dropAll(int x, int y) {
        ItemEntity[] items = new ItemEntity[tables.length];
        for (int i = 0; i < tables.length; i++) {
            if (drop(i, x, y) != null) {
                items[i] = drop(i, x, y);
            }
        }
        return items;
    }
}
