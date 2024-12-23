package dev.TTs.TTsGames.Games.PixelQuest.item;

import java.util.Objects;

public class ItemStack {
    private final Item item;
    private int count;
    private final int maxCount;

    public ItemStack(Item item, int count) {
        this.item = item;
        this.count = count;
        this.maxCount = item.toItem().settings().getStackLimit();
    }

    public ItemStack(Item item) {
        this(item, 1);
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void addItem() throws ItemCountException {
        addItems(1);
    }

    public void addItems(int count) throws ItemCountException {
        this.count += count;
        if (this.count > maxCount) {
            throw new ItemCountException("Item count higher than max count:", maxCount, this.count);
        }
    }

    public void removeItem() throws ItemCountException {
        removeItems(1);
    }

    public void removeItems(int count) throws ItemCountException {
        this.count -= count;
        if (this.count < 1) {
            throw new ItemCountException("Item count lower than one:", this.count);
        }
    }

    public float getWeight() {
        return item.toItem().settings().getWeight() * count;
    }

    @Override
    public String toString() {
        return "Item: " + item + ", Count: " + count + ", Max Count: " + maxCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStack itemStack = (ItemStack) o;
        return Objects.equals(item, itemStack.item);
    }
    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}
