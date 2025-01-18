package dev.TTs.TTsGames.Games.PixelQuest.gui;

import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static dev.TTs.TTsGames.Main.logger;

public class BasicArrayInventory extends ArrayInventory {
    public BasicArrayInventory(int maxSize) {
        super(maxSize);
    }
    private final Map<Integer, Point> customSlotPositions = new HashMap<>();

    @Override
    public Point[] slotPositions() {
        Point[] slotPositions = new Point[maxSize];
        int slotPerRow = (int) Math.sqrt(maxSize);

        for (int i = 0; i < maxSize; i++) {
            if (customSlotPositions.get(i) == null) {
                int Slotx = 50 + (i % slotPerRow) * 32;
                int Sloty = 50 + (i / slotPerRow) * 32;
                slotPositions[i] = new Point(Slotx, Sloty);
            } else {
                slotPositions[i] = customSlotPositions.get(i);
            }
        }

        return slotPositions;
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

    public BasicArrayInventory customSlotPosition(int index, Point point) {
        customSlotPositions.put(index, point);
        return this;
    }
}
