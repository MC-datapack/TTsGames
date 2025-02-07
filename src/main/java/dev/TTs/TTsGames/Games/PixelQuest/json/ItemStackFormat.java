package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.Predicate;
import dev.TTs.util.Identifier;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings("unchecked")
public class ItemStackFormat {
    private final String item;
    private final CountProvider count;
    private double chance = 1.0;
    private final Predicate<?>[] conditions;

    public ItemStackFormat(Item item, CountProvider count) {
        this(item, count, 1.0, null);
    }

    public ItemStackFormat(Item item, CountProvider count, double chance) {
        this(item, count, chance, null);
    }

    public ItemStackFormat(Item item, CountProvider count, double chance, Predicate<?>[] conditions) {
        this.item = item.identifier().itemId();
        this.count = count;
        this.chance = chance;
        this.conditions = conditions;
    }

    public ItemStack getItemStack() {
        return new ItemStack(Item.getItem(Identifier.of(item)), count.getCount());
    }

    public ItemStack getItemStack(Entity entity) {
        int itemCount = count.getCount();
        if ((conditions == null || shouldApplyAll(entity)) && Math.random() < chance && itemCount > 0) {
            Item retrievedItem = Item.getItem(Identifier.of(item));
            if (retrievedItem != null) {
                return new ItemStack(retrievedItem, itemCount);
            } else {
                logger.error("Items not found: %s", item);
            }
        }
        return null;
    }

    public ItemStack getItemStack(Damageable entity) {
        int itemCount = count.getCount();
        if ((conditions == null || shouldApplyAll(entity)) && Math.random() < chance && itemCount > 0) {
            Item retrievedItem = Item.getItem(Identifier.of(item));
            if (retrievedItem != null) {
                return new ItemStack(retrievedItem, itemCount);
            } else {
                logger.error("Items not found: %s", item);
            }
        }
        return null;
    }

    private boolean shouldApplyAll(Object entity) {
        if (entity instanceof Entity) {
            for (Predicate<?> condition : conditions) {
                if (!((Predicate<Entity>) condition).shouldApply((Entity) entity)) {
                    return false;
                }
            }
        } else if (entity instanceof Damageable) {
            for (Predicate<?> condition : conditions) {
                if (!((Predicate<Damageable>) condition).shouldApply((Damageable) entity)) {
                    return false;
                }
            }
        } else {
            logger.error("Unknown entity type: %s", entity.getClass().getName());
            return false;
        }
        return true;
    }

    public Predicate<?>[] getConditions() {
        return conditions;
    }
}
