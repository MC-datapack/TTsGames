package dev.TTs.TTsGames.Games.PixelQuest.item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

public class ItemStack implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;

    private final Item item;
    private int count;
    private transient int maxCount;

    public ItemStack(Item item, int count) {
        this.item = item;
        this.count = count;
        this.maxCount = item.settings() == null ? 64 : item.settings().getStackLimit();
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
        if (item.settings == null) return 0;
        return item.settings().getWeight() * count;
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

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.readObject();
        maxCount = item.settings().getStackLimit();
    }
}
