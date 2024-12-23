package dev.TTs.TTsGames.Games.PixelQuest.gui;

import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;

import java.awt.*;

import static dev.TTs.TTsGames.Main.logger;

public class BasicInventory extends Inventory {
    public BasicInventory(int maxSize) {
        super(maxSize);
    }

    @Override
    public Point[] slotPositions() {
        Point[] slotPositionsT = new Point[maxSize];
        int slotPerRow = (int) Math.sqrt(maxSize);

        for (int i = 0; i < maxSize; i++) {
            int Slotx = 50 + (i % slotPerRow) * 32;
            int Sloty = 50 + (i / slotPerRow) * 32;
            slotPositionsT[i] = new Point(Slotx, Sloty);
        }

        return slotPositionsT;
    }

    @Override
    public void addItem(ItemStack item) {
        try {
            super.addItem(item);
            logger.info("Successfully added item: %s to inventory", item);
        } catch (InventoryOutOfBoundsException e) {
            logger.error("Failed to add item: %s", e.getMessage());
        }
    }

    @Override
    public void addItemAsNewStack(ItemStack item) {
        try {
            super.addItemAsNewStack(item);
        } catch (InventoryOutOfBoundsException e) {
            logger.error("Failed to add item as new Item stack: %s", e.getMessage());
        }
    }

    public float getWeight() {
        float result = 0.0F;
        for (ItemStack itemStack : items) {
            if (itemStack != null) {
                result += itemStack.getWeight();
            }
        }
        return result;
    }
}
