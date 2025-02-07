package dev.TTs.TTsGames.Games.PixelQuest.gui;

import dev.TTs.TTsGames.Games.PixelQuest.entity.ItemEntity;
import dev.TTs.TTsGames.Games.PixelQuest.item.*;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.TTs.TTsGames.Main.logger;

public abstract class ArrayInventory implements Inventory, Serializable {
    @Serial
    private static final long serialVersionUID = Inventory.serialVersionUID;

    protected ItemStack[] items;
    protected int maxSize;
    private transient String tooltipTitle = null;
    private transient String tooltipDescription = null;
    private transient Point tooltipPosition = null;
    private transient Rarity tooltipRarity = null;

    public ArrayInventory(int maxSize) {
        this.items = new ItemStack[maxSize];
        this.maxSize = maxSize;
    }

    @Override
    public Rectangle[] bounds() {
        Rectangle[] bounds = new Rectangle[maxSize];
        for (int i = 0; i < maxSize; i++) {
            Point slotPosition = getSlotPosition(i);
            if (slotPosition != null) {
                bounds[i] = new Rectangle(slotPosition.x, slotPosition.y, 32, 32);
            }
        }
        return bounds;
    }

    @Override
    public void handleMouseMoved() {
        for (int i = 0; i < maxSize; i++) {
            Point slotPosition = getSlotPosition(i);
            if (slotPosition != null) {
                Rectangle slotBounds = new Rectangle(slotPosition.x, slotPosition.y, 32, 32);
                if (slotBounds.contains(PixelQuestGame.game.mousePosition)) {
                    ItemStack itemStack = getItemStack(i);
                    if (itemStack != null) {
                        if (itemStack.getItem().identifier() != null) {
                            tooltipTitle = itemStack.getItem().identifier().translatedItemName();
                            tooltipDescription = itemStack.getItem().settings().getDescription();
                            tooltipRarity = itemStack.getItem().settings().getRarity();
                            tooltipPosition = PixelQuestGame.game.mousePosition;
                            return;
                        }
                    }
                }
            }
        }
        tooltipTitle = null;
        tooltipDescription = null;
        tooltipRarity = null;
        tooltipPosition = null;
    }

    @Override
    public void drawSlot(Graphics g, int index) {
        basicDrawSlot(g, index);
        if (tooltipTitle != null && tooltipDescription != null && tooltipPosition != null) {
            drawTooltip(g, tooltipTitle, tooltipDescription, tooltipRarity, tooltipPosition.x, tooltipPosition.y);
        }
    }

    @Override
    public void drawInventory(Graphics g) {
        for (int i = 0; i < maxSize; i++) {
            basicDrawSlot(g, i);
        }
        if (tooltipTitle != null && tooltipDescription != null && tooltipPosition != null) {
            drawTooltip(g, tooltipTitle, tooltipDescription, tooltipRarity, tooltipPosition.x, tooltipPosition.y);
        }
    }

    @Override
    public void basicDrawSlot(Graphics g, int index) {
        ItemStack item = this.getItemStack(index);
        Point slotPosition = this.getSlotPosition(index);
        if (slotPosition != null) {
            g.setColor(new Color(158, 158, 158));
            g.fillRect(slotPosition.x, slotPosition.y, 32, 32);
            g.setColor(new Color(92, 91, 91));
            g.drawRect(slotPosition.x, slotPosition.y, 33, 33);
            g.drawRect(slotPosition.x, slotPosition.y, 32, 32);
            if (item != null) {
                if (item.getItem().identifier() != null) {
                    g.drawImage(item.getItem().identifier().getItemTexture().toImage(), slotPosition.x, slotPosition.y, 32, 32, null);
                    if (item.getCount() != 1) {
                        int xC = item.getCount() > 99 ? 5 : 10;
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial", Font.BOLD, 16));
                        g.drawString(String.valueOf(item.getCount()), slotPosition.x + xC, slotPosition.y + 32);
                    }
                }
            }
        }
    }

    @Override
    public void drawTooltip(Graphics g, String title, String description, Rarity rarity, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        int descWidth = fm.stringWidth(description);
        int width = Math.max(titleWidth, descWidth) + 10;
        int lines = description.isEmpty() ? 1 : 2;
        int height = fm.getHeight() * lines + 2;

        g.setColor(new Color(0, 0, 0));
        g.fillRoundRect(x, y, width, height, 10, 10);
        g.setColor(Color.WHITE);
        g.drawRoundRect(x, y, width, height, 10, 10);
        g.setColor(rarity.getColor());
        g.drawString(title, x + 5, y + fm.getAscent() + 2);
        if (!description.isEmpty()) {
            g.setColor(Color.WHITE);
            g.drawString(description, x + 5, y + fm.getAscent() * 2 + 5);
        }
    }

    @Override
    public void addItemAsNewStack(ItemStack item) throws InventoryOutOfBoundsException {
        check();
        int i;
        for (i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        throw new InventoryOutOfBoundsException(formatTooManyItems());
    }

    @Override
    public void addItem(int index, int count) throws InventoryOutOfBoundsException, ItemCountException {
        check();
        if (maxSize <= index) {
            throw new InventoryOutOfBoundsException(formatTooManyItems());
        }
        items[index].addItems(count);
    }

    @Override
    public void addItem(ItemStack item) throws InventoryOutOfBoundsException {
        check();
        if (items.length >= maxSize && findSpaceInExistingStacks(item) == 0) {
            throw new InventoryOutOfBoundsException(formatTooManyItems());
        }
        int itemsToAdd = item.getCount();
        itemsToAdd -= addToExistingStacks(item);

        while (itemsToAdd > 0) {
            int countToAdd = Math.min(item.getItem().settings().getStackLimit(), itemsToAdd);
            addItemAsNewStack(new ItemStack(item.getItem(), countToAdd));
            itemsToAdd -= countToAdd;
        }
    }

    @Override
    public String formatTooManyItems() {
        StringBuilder build = new StringBuilder("Tried adding too many items:\n");
        for (int i = 0; i < maxSize; i++) {
            build.append(i).append(": ").append(items[i]).append("\n");
        }
        return build.toString();
    }

    @Override
    public int addToExistingStacks(ItemStack item) {
        check();
        int remainingItems = item.getCount();
        for (ItemStack existingItem : items) {
            if (existingItem != null && existingItem.getItem().equals(item.getItem())) {
                int availableSpace = item.getItem().settings().getStackLimit() - existingItem.getCount();
                int addable = Math.min(availableSpace, remainingItems);
                for (int i = 0; i < addable; i++) {
                    existingItem.addItem();
                }
                remainingItems -= addable;
                if (remainingItems <= 0) break;
            }
        }
        return item.getCount() - remainingItems;
    }

    @Override
    public int findSpaceInExistingStacks(ItemStack item) {
        int totalSpace = 0;
        for (ItemStack existingItem : items) {
            if (existingItem == null || existingItem.getItem().equals(item.getItem())) {
                int availableSpace;
                if (existingItem != null) {
                    availableSpace = item.getItem().settings().getStackLimit() - existingItem.getCount();
                } else {
                    availableSpace = item.getItem().settings().getStackLimit();
                }
                totalSpace += availableSpace;
            }
        }
        return totalSpace;
    }

    @Override
    public void removeItem(ItemStack itemstack) {
        if (itemstack.getCount() <= 1) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == itemstack) {
                    items[i] = null;
                }
            }
        } else {
            itemstack.removeItem();
        }
    }

    @Override
    public void removeItem(int index) {
        if (items[index].getCount() == 1) {
            items[index] = null;
        } else {
            items[index].removeItem();
        }
    }

    @Override
    public void removeItems(ItemStack item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i] == item) {
                    items[i] = null;
                } else if (items[i].getItem().equals(item.getItem())) {
                    items[i].removeItems(item.getCount());
                }
            }
        }
    }

    @Override
    public void removeItems(int index) {
        items[index] = null;
    }

    @Override
    public ItemStack[] getItems() {
        return items;
    }

    @Override
    public ItemStack getItemStack(int index) {
        if (index >= 0 && index < items.length) {
            return items[index];
        }
        return null;
    }

    @Override
    public Item getItem(int index) {
        ItemStack itemStack = getItemStack(index);
        return itemStack != null ? itemStack.getItem() : null;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public int getCurrentFilledSize() {
        return items.length;
    }

    @Override
    public Point getSlotPosition(int index) {
        if (index >= 0 && index < slotPositions().length) {
            return slotPositions()[index];
        }
        return null;
    }

    @Override
    public void replaceSlot(int index, ItemStack item) {
        if (index >= 0 && index < slotPositions().length) {
            items[index] = item;
        }
    }

    @Override
    public ItemStack getSelectedStack() {
        for (int i = 0; i < maxSize; i++) {
            if (bounds()[i].contains(PixelQuestGame.game.mousePosition)) {
                return items[i];
            }
        }
        return null;
    }

    @Override
    public void dropSelectedStack() {
        dropSelectedStack(PixelQuestGame.game.mousePosition);
    }

    @Override
    public int getSelectedIndex() {
        for (int i = 0; i < maxSize; i++) {
            if (bounds()[i].contains(PixelQuestGame.game.mousePosition)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void dropSelectedStack(Point point) {
        for (int i = 0; i < maxSize; i++) {
            if (bounds()[i].contains(PixelQuestGame.game.mousePosition)) {
                try {
                    ItemStack selectedItemStack = items[i];
                    if (selectedItemStack != null) {
                        WorldSaving.world.addGameObject(new ItemEntity(selectedItemStack, point));
                        removeItem(i);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    logger.warn("Failed to drop item at index %s" , i);
                }
            }
        }
    }

    @Override
    public boolean isEmpty(int index) {
        return items[index] == null;
    }

    @Override
    public void check() {
        for (ItemStack item : items) {
            if (item != null && item.getItem() != null && item.getItem().identifier() == null) {
                removeItem(item);
            }
        }
    }

    @Override
    public boolean isFull() {
        for (ItemStack itemStack : items) {
            if (itemStack.getCount() != itemStack.getItem().settings().getStackLimit()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sortInventory(SortType sort) throws UnsupportedOperationException {
        List<ItemStack> itemList = Arrays.asList(items);
        switch (sort) {
            case ITEM_COUNT_ASCENDING -> {
                Map<Item, Integer> countMap = new HashMap<>();
                for (ItemStack itemStack : itemList) {
                    countMap.merge(itemStack.getItem(), itemStack.getCount(), Integer::sum);
                }
                itemList.sort(Comparator.comparingInt(itemStack -> countMap.get(itemStack.getItem())));
            }
            case ITEM_COUNT_DESCENDING -> {
                Map<Item, Integer> countMap = new HashMap<>();
                for (ItemStack itemStack : itemList) {
                    countMap.merge(itemStack.getItem(), itemStack.getCount(), Integer::sum);
                }
                itemList.sort((itemStack1, itemStack2) -> countMap.get(itemStack2.getItem()) - countMap.get(itemStack1.getItem()));
            }
            case ITEM_NAME_A_Z -> itemList.sort(Comparator.comparing(itemStack -> itemStack.getItem().identifier().translatedItemName()));
            case ITEM_NAME_Z_A -> itemList.sort(Comparator.comparing(itemStack -> ((ItemStack) itemStack).getItem().identifier().translatedItemName()).reversed());
            case RARITY_COMMON_EPIC -> itemList.sort(Comparator.comparing(itemStack -> itemStack.getItem().settings().getRarity().getIndex()));
            case RARITY_EPIC_UNCOMMON -> itemList.sort(Comparator.comparing(itemStack -> ((ItemStack) itemStack).getItem().settings().getRarity().getIndex()).reversed());
            default -> throw new UnsupportedOperationException("SortType is not supported by ArrayInventory: " + sort);
        }
        items = itemList.toArray(new ItemStack[0]);
    }


    @Override
    public void dropAll(Point point) {
        for (int i = 0; i < maxSize; i++) {
            dropStack(i, point);
        }
    }

    @Override
    public void dropStack(int index) {
        dropStack(index, new Point(0, 0));
    }

    @Override
    public void dropStack(int index, Point point) {
        if (items[index] != null) {
            try {
                ItemStack selectedItemStack = items[index];
                if (selectedItemStack != null) {
                    WorldSaving.world.addGameObject(new ItemEntity(selectedItemStack, point));
                    removeItem(index);
                }
            } catch (IndexOutOfBoundsException e) {
                logger.warn("Failed to drop item at index %s", index);
            }
        }
    }

    @Override
    public void increaseSize(int amount) {
        this.maxSize += amount;
    }

    @Override
    public void decreaseSize(int amount) {
        this.maxSize -= amount;
    }

    @Override
    public void increaseSize(PowerUpItem added) {
        this.maxSize += added.amount();
    }

    @Override
    public void decreaseSize(PowerUpItem removed) {
        this.maxSize -= removed.amount();
    }
}
