package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.entity.ItemEntity;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LootTableFormat {
    private String name;
    private ItemStackFormat[] tables;

    public LootTableFormat(String name, ItemStackFormat[] tables) {
        this.name = name;
        this.tables = tables;
    }

    public ItemStack getItemStack(int index, Damageable entity) {
        return tables[index].getItemStack(entity);
    }

    public ItemStack getItemStack(int index) {
        return tables[index].getItemStack();
    }

    public ItemEntity drop(int index, int x, int y, Damageable entity) {
        ItemStack itemStack = getItemStack(index, entity);
        if (itemStack != null) {
            return new ItemEntity(itemStack, x, y);
        }
        return null;
    }

    public ItemEntity drop(int index, int x, int y) {
        ItemStack itemStack = getItemStack(index);
        if (itemStack != null) {
            return new ItemEntity(itemStack, x, y);
        }
        return null;
    }

    public ItemEntity[] dropAll(int x, int y, Damageable entity) {
        List<ItemEntity> items = new ArrayList<>();
        for (int i = 0; i < tables.length; i++) {
            ItemEntity itemEntity = drop(i, x, y, entity);
            if (itemEntity != null) {
                items.add(itemEntity);
            }
        }
        return items.toArray(new ItemEntity[0]);
    }

    public ItemEntity[] dropAll(int x, int y) {
        List<ItemEntity> items = new ArrayList<>();
        for (int i = 0; i < tables.length; i++) {
            ItemEntity itemEntity = drop(i, x, y);
            if (itemEntity != null) {
                items.add(itemEntity);
            }
        }
        return items.toArray(new ItemEntity[0]);
    }

    public ItemStackFormat[] getTables() {
        return tables;
    }
}
