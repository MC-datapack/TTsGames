package dev.TTs.TTsGames.Games.PixelQuest.gui;

import dev.TTs.TTsGames.Games.PixelQuest.item.*;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

/**
 * Interface representing the inventory system.
 */
public interface Inventory extends Serializable {
    @Serial
    long serialVersionUID = serialVersion;

    /**
     * Returns the bounds of each slot.
     * @return an array of Rectangles representing slot bounds.
     */
    Rectangle[] bounds();

    /**
     * Returns the positions of each slot.
     * @return an array of Points representing slot positions.
     */
    Point[] slotPositions();

    /**
     * Draws a specific slot.
     * @param g the graphics context.
     * @param index the index of the slot to draw.
     */
    void drawSlot(Graphics g, int index);

    /**
     * Draws the entire inventory.
     * @param g the graphics context.
     */
    void drawInventory(Graphics g);

    /**
     * Draws a basic slot.
     * @param g the graphics context.
     * @param index the index of the slot to draw.
     */
    void basicDrawSlot(Graphics g, int index);

    /**
     * Draws a tooltip for an item.
     * @param g the graphics context.
     * @param title the title of the item.
     * @param description the description of the item.
     * @param rarity the rarity of the item.
     * @param x the x-coordinate of the tooltip.
     * @param y the y-coordinate of the tooltip.
     */
    void drawTooltip(Graphics g, String title, String description, Rarity rarity, int x, int y);

    /**
     * Handles mouse movement over the inventory.
     */
    void handleMouseMoved();

    /**
     * Adds an item to the inventory as a new stack.
     * @param item the item to add.
     * @throws InventoryOutOfBoundsException if the inventory is full.
     */
    void addItemAsNewStack(ItemStack item) throws InventoryOutOfBoundsException;

    /**
     * Adds a specified count of an item to an existing stack.
     * @param index the index of the stack.
     * @param count the count to add.
     * @throws InventoryOutOfBoundsException if the inventory is full.
     * @throws ItemCountException if the stack is getting bigger than the limit.
     */
    void addItem(int index, int count) throws InventoryOutOfBoundsException, ItemCountException;

    /**
     * Adds an item to the inventory.
     * @param item the item to add.
     * @throws InventoryOutOfBoundsException if the inventory is full.
     */
    void addItem(ItemStack item) throws InventoryOutOfBoundsException;

    /**
     * Adds an item to existing stacks if possible.
     * @param item the item to add.
     * @return the remaining count of the item that couldn't be added.
     */
    int addToExistingStacks(ItemStack item);

    /**
     * Finds space in existing stacks for an item.
     * @param item the item to find space for.
     * @return the available space for the item.
     */
    int findSpaceInExistingStacks(ItemStack item);

    /**
     * Removes an item from the inventory.
     * @param itemstack the item stack to remove.
     */
    void removeItem(ItemStack itemstack);

    /**
     * Removes an item from a specific slot.
     * @param index the index of the slot.
     */
    void removeItem(int index);

    /**
     * Removes multiple items from the inventory.
     * @param item the item to remove.
     */
    void removeItems(ItemStack item);

    /**
     * Removes multiple items from a specific slot.
     * @param index the index of the slot.
     */
    void removeItems(int index);

    /**
     * Replaces an item in a specific slot.
     * @param index the index of the slot.
     * @param item the item to place in the slot.
     */
    void replaceSlot(int index, ItemStack item);

    /**
     * Formats the message for too many items.
     * @return the formatted message.
     */
    String formatTooManyItems();

    /**
     * Returns all items in the inventory.
     * @return an array of ItemStacks representing the items.
     */
    ItemStack[] getItems();

    /**
     * Returns the item stack at a specific slot.
     * @param index the index of the slot.
     * @return the ItemStack at the slot.
     */
    ItemStack getItemStack(int index);

    /**
     * Returns the item at a specific slot.
     * @param index the index of the slot.
     * @return the item at the slot.
     */
    Item getItem(int index);

    /**
     * Returns the currently selected item stack.
     * @return the selected ItemStack.
     */
    ItemStack getSelectedStack();

    /**
     * Returns the index of the currently selected slot.
     * @return the selected index.
     */
    int getSelectedIndex();

    /**
     * Drops the Stack of the index.
     * @param index the index of the slot.
     */
    void dropStack(int index);

    /**
     * Drops the Stack of the index at a specific point.
     * @param point the point to drop the stack at.
     * @param index the index of the slot.
     */
    void dropStack(int index, Point point);

    /**
     * Drops the currently selected stack.
     */
    void dropSelectedStack();

    /**
     * Drops the currently selected stack at a specific point.
     * @param point the point to drop the stack at.
     */
    void dropSelectedStack(Point point);

    /**
     * Drops all stacks at a specific point.
     * @param point the point to drop the stack at.
     */
    void dropAll(Point point);

    /**
     * Returns the maximum size of the inventory.
     * @return the maximum size.
     */
    int getMaxSize();

    /**
     * Returns the current filled size of the inventory.
     * @return the current filled size.
     */
    int getCurrentFilledSize();

    /**
     * Returns the position of a specific slot.
     * @param index the index of the slot.
     * @return the position of the slot.
     */
    Point getSlotPosition(int index);

    /**
     * Checks the inventory for any issues.
     */
    void check();

    /**
     * Checks if a specific slot is empty.
     * @param index the index of the slot.
     * @return true if the slot is empty, false otherwise.
     */
    boolean isEmpty(int index);

    /**
     * Checks if the inventory is full.
     * @return true if the inventory is full, false otherwise.
     */
    boolean isFull();

    /**
     * Sorts the inventory based on the specified sort type.
     * @param sort the sort type.
     * @throws UnsupportedOperationException if the sort type is not supported.
     */
    void sortInventory(SortType sort) throws UnsupportedOperationException;

    /**
     * Increases the size of the inventory.
     * @param amount the amount to increase.
     */
    void increaseSize(int amount);

    /**
     * Decreases the size of the inventory.
     * @param amount the amount to decrease.
     */
    void decreaseSize(int amount);

    /**
     * Increases the size of the inventory using a power-up item.
     * @param added the power-up item.
     */
    void increaseSize(PowerUpItem added);

    /**
     * Decreases the size of the inventory using a power-up item.
     * @param removed the power-up item.
     */
    void decreaseSize(PowerUpItem removed);

    /**
     * Swaps items between two slots.
     * @param index1 the first slot index.
     * @param index2 the second slot index.
     */
    default void swapItems(int index1, int index2) {
        ItemStack stack1 = getItemStack(index1);
        ItemStack stack2 = getItemStack(index2);
        replaceSlot(index1, stack2);
        replaceSlot(index2, stack1);
    }

    /**
     * Moves items from one slot to another in the inventory.
     * @param from the index of the source slot.
     * @param to the index of the destination slot.
     */
    default void moveItems(int from, int to) {
        if (getItemStack(to) != null) {
            swapItems(from, to);
            return;
        }
        ItemStack fromStack = getItemStack(from);
        replaceSlot(to, fromStack);
        removeItems(from);
    }


    /**
     * Re-adds an item to the inventory.
     * @param index the index of the item.
     */
    default void readdItem(int index) {
        ItemStack sel = getItemStack(index);
        removeItems(index);
        addItem(sel);
    }

    /**
     * Returns the current empty size of the inventory.
     * @return the current empty size.
     */
    default int getCurrentEmptySize() {
        return getMaxSize() - getCurrentFilledSize();
    }

    /**
     * Enumeration for sorting types.
     */
    enum SortType {
        ITEM_COUNT_ASCENDING, ITEM_COUNT_DESCENDING, ITEM_NAME_A_Z, ITEM_NAME_Z_A, RARITY_COMMON_EPIC, RARITY_EPIC_UNCOMMON
    }
}
