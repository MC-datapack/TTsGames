package dev.TTs.TTsGames.Games.PixelQuest.gui;

import dev.TTs.TTsGames.Games.PixelQuest.entities.ItemEntity;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.item.Rarity;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;

import java.awt.*;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings("unused")
public abstract class Inventory {
    protected ItemStack[] items;
    protected final int maxSize;
    protected final Point[] slotPositions;
    private String tooltipTitle = null;
    private String tooltipDescription = null;
    private Point tooltipPosition = null;
    private Rarity tooltipRarity = null;
    public Rectangle[] bounds;

    public Inventory(int maxSize) {
        this.items = new ItemStack[maxSize];
        this.maxSize = maxSize;
        this.slotPositions = slotPositions();
        this.bounds = new Rectangle[maxSize];
        for (int i = 0; i < maxSize; i++) {
            Point slotPosition = getSlotPosition(i);
            if (slotPosition != null) {
                bounds[i] = new Rectangle(slotPosition.x, slotPosition.y, 32, 32);
            }
        }
    }

    public abstract Point[] slotPositions();

    public void handleMouseMoved() {
        for (int i = 0; i < maxSize; i++) {
            Point slotPosition = getSlotPosition(i);
            if (slotPosition != null) {
                Rectangle slotBounds = new Rectangle(slotPosition.x, slotPosition.y, 32, 32);
                if (slotBounds.contains(PixelQuestGame.game.mousePosition)) {
                    ItemStack itemStack = getItemStack(i);
                    if (itemStack != null) {
                        tooltipTitle = itemStack.getItem().toItem().identifier().translatedItemName();
                        tooltipDescription = itemStack.getItem().toItem().settings().getDescription();
                        tooltipRarity = itemStack.getItem().toItem().settings().getRarity();
                        tooltipPosition = PixelQuestGame.game.mousePosition;
                        return;
                    }
                }
            }
        }
        tooltipTitle = null;
        tooltipDescription = null;
        tooltipRarity = null;
        tooltipPosition = null;
    }

    public void paintInventory(Graphics g) {
        for (int i = 0; i < maxSize; i++) {
            ItemStack item = this.getItemStack(i);
            Point slotPosition = this.getSlotPosition(i);
            if (slotPosition != null) {
                g.setColor(new Color(158, 158, 158));
                g.fillRect(slotPosition.x, slotPosition.y, 32, 32);
                g.setColor(new Color(92, 91, 91));
                g.drawRect(slotPosition.x, slotPosition.y, 33, 33);
                g.drawRect(slotPosition.x, slotPosition.y, 32, 32);
                if (item != null) {
                    g.drawImage(item.getItem().toItem().identifier().getItemTexture().toImage(), slotPosition.x, slotPosition.y, 32, 32, null);
                    if (item.getCount() != 1) {
                        int xC = item.getCount() > 99 ? 5 : 10;
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial", Font.BOLD, 16));
                        g.drawString(String.valueOf(item.getCount()), slotPosition.x + xC, slotPosition.y + 32);
                    }
                }
            }
        }
        if (tooltipTitle != null && tooltipDescription != null && tooltipPosition != null) {
            drawTooltip(g, tooltipTitle, tooltipDescription, tooltipRarity, tooltipPosition.x, tooltipPosition.y);
        }
    }

    private void drawTooltip(Graphics g, String title, String description, Rarity rarity, int x, int y) {
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

    public void addItemAsNewStack(ItemStack item) throws InventoryOutOfBoundsException {
        int i;
        for (i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        throw new InventoryOutOfBoundsException(formatTooManyItems());
    }

    public void addItem(ItemStack item) throws InventoryOutOfBoundsException {
        if (items.length >= maxSize && findSpaceInExistingStacks(item) == 0) {
            throw new InventoryOutOfBoundsException(formatTooManyItems());
        }
        int itemsToAdd = item.getCount();
        itemsToAdd -= addToExistingStacks(item);

        while (itemsToAdd > 0) {
            int countToAdd = Math.min(item.getItem().toItem().settings().getStackLimit(), itemsToAdd);
            addItemAsNewStack(new ItemStack(item.getItem(), countToAdd));
            itemsToAdd -= countToAdd;
        }
    }

    public String formatTooManyItems() {
        StringBuilder build = new StringBuilder("Tried adding too many items:\n");
        for (int i = 0; i < maxSize; i++) {
            build.append(i).append(": ").append(items[i]).append("\n");
        }
        return build.toString();
    }

    protected int addToExistingStacks(ItemStack item) {
        int remainingItems = item.getCount();
        for (ItemStack existingItem : items) {
            if (existingItem != null && existingItem.getItem().equals(item.getItem())) {
                int availableSpace = item.getItem().toItem().settings().getStackLimit() - existingItem.getCount();
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

    protected int findSpaceInExistingStacks(ItemStack item) {
        int totalSpace = 0;
        for (ItemStack existingItem : items) {
            if (existingItem == null || existingItem.getItem().equals(item.getItem())) {
                int availableSpace;
                if (existingItem != null) {
                    availableSpace = item.getItem().toItem().settings().getStackLimit() - existingItem.getCount();
                } else {
                    availableSpace = item.getItem().toItem().settings().getStackLimit();
                }
                totalSpace += availableSpace;
            }
        }
        return totalSpace;
    }

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

    public void removeItem(int index) {
        items[index] = null;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public ItemStack getItemStack(int index) {
        if (index >= 0 && index < items.length) {
            return items[index];
        }
        return null;
    }

    public Item getItem(int index) {
        ItemStack itemStack = getItemStack(index);
        return itemStack != null ? itemStack.getItem() : null;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getCurrentSize() {
        return items.length;
    }

    public Point getSlotPosition(int index) {
        if (index >= 0 && index < slotPositions.length) {
            return slotPositions[index];
        }
        return null;
    }

    public void replaceSlot(int index, ItemStack item) {
        if (index >= 0 && index < slotPositions.length) {
            items[index] = item;
        }
    }

    public void dropSelectedStack() {
        for (int i = 0; i < maxSize; i++) {
            if (bounds[i].contains(PixelQuestGame.game.mousePosition)) {
                try {
                    ItemStack selectedItemStack = items[i];
                    if (selectedItemStack != null) {
                        PixelQuestGame.game.addGameObject(new ItemEntity(selectedItemStack, PixelQuestGame.game.mousePosition));
                        removeItem(i);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    logger.warn("Failed to drop item at index %s" , i);
                }
            }
        }
    }
}
