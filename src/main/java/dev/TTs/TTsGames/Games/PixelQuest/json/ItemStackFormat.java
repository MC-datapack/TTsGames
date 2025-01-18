package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.Predicate;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings("unused")
public class ItemStackFormat {
    private final String item;
    private final CountProvider count;
    private double chance = 1.0;
    private Predicate<? super Damageable> conditions = entity -> true;

    public ItemStackFormat(Item item, CountProvider count) {
        this(item, count, 1.0, entity -> true);
    }

    public ItemStackFormat(Item item, CountProvider count, double chance) {
        this(item, count, chance, entity -> true);
    }

    public ItemStackFormat(Item item, CountProvider count, double chance, Predicate<? super Damageable> conditions) {
        this.item = item.identifier().itemId();
        this.count = count;
        this.chance = chance;
        this.conditions = conditions;
    }

    public ItemStack getItemStack(Damageable entity) {
        int itemCount = count.getCount();
        if (conditions.shouldApply(entity) && Math.random() < chance && itemCount > 0) {
            Item retrievedItem = Item.getItem(new Identifier(item));
            if (retrievedItem != null) {
                return new ItemStack(retrievedItem, itemCount);
            } else {
                logger.error("Item not found: %s", item);
            }
        }
        return null;
    }

    public ItemStack getItemStack() {
        int itemCount = count.getCount();
        if (Math.random() < chance && itemCount > 0) {
            Item retrievedItem = Item.getItem(new Identifier(item));
            if (retrievedItem != null) {
                return new ItemStack(retrievedItem, itemCount);
            } else {
                logger.error("Item not found: %s", item);
            }
        }
        return null;
    }

    public Predicate<? super Damageable> getConditions() {
        return conditions;
    }
}
