package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.TTsGames.Games.PixelQuest.item.FoodItem;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.main.KeyBindingPanel;
import dev.TTs.TTsGames.Games.PixelQuest.main.KeyBindings;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.entity.usable.CraftingTable;
import dev.TTs.TTsGames.Games.PixelQuest.entity.usable.Furnace;
import dev.TTs.TTsGames.Games.PixelQuest.world.World;

import java.util.HashSet;

public class PlayerKeyBoardActions {
    private static long lastSetShowInventory = 1000;
    private static long lastDropped = 1000;
    private static long lastEaten = 1000;
    private static long lastSelected = 1000;

    public static void keyActions(Player player) {
        if (player.pressedKeys == null) player.pressedKeys = new HashSet<>();
        boolean moved = false;

        if (!player.died()) {
            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_UP"))) {
                if (player.pressedKeys.contains(KeyBindings.getKeyBinding("SPRINT"))) player.y -= player.speed*2;
                else player.y -= player.speed;
                moved = true;
            }
            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_DOWN"))) {
                if (player.pressedKeys.contains(KeyBindings.getKeyBinding("SPRINT"))) player.y += player.speed*2;
                else player.y += player.speed;
                moved = true;
            }
            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_LEFT"))) {
                if (player.pressedKeys.contains(KeyBindings.getKeyBinding("SPRINT"))) player.x -= player.speed*2;
                else player.x -= player.speed;
                moved = true;
            }
            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_RIGHT"))) {
                if (player.pressedKeys.contains(KeyBindings.getKeyBinding("SPRINT"))) player.x += player.speed*2;
                else player.x += player.speed;
                moved = true;
            }

            player.moving = moved;

            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("EAT"))) {
                if (System.currentTimeMillis() - lastEaten >= 1000) {
                    for (int i = 0; i < 27; i++) {
                        ItemStack currentItem = player.inventory.getItemStack(i);
                        if (currentItem != null && currentItem.getItem() instanceof FoodItem) {
                            player.consume(currentItem);
                            break;
                        }
                    }
                    lastEaten = System.currentTimeMillis();
                }
            }
            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("SHOW_INVENTORY"))) {
                if (System.currentTimeMillis() - lastSetShowInventory >= 250) {
                    player.showInventory = !player.showInventory;
                    lastSetShowInventory = System.currentTimeMillis();
                }
            }

            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("DROP_ITEM"))) {
                if (System.currentTimeMillis() - lastDropped >= 250) {
                    PixelQuestGame.drop = true;
                    player.inventory.dropSelectedStack();
                    lastDropped = System.currentTimeMillis();
                }
            }

            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("TRANSFER_ITEM"))) {
                ItemStack selectedItem = player.inventory.getSelectedStack();
                if (selectedItem != null) {
                    if (World.getFurnace().open) {
                        Furnace furnace = World.getFurnace();
                        furnace.receiveItem(selectedItem);
                        player.inventory.removeItems(selectedItem);
                    } else if (World.getCraftingTable().open) {
                        CraftingTable craftingTable = World.getCraftingTable();
                        craftingTable.receiveItem(selectedItem);
                        player.inventory.removeItems(selectedItem);
                    }
                }
            }

            if (player.pressedKeys.contains(KeyBindings.getKeyBinding("SELECT"))) {
                if (System.currentTimeMillis() - lastSelected >= 200) {
                    if (player.inventory.getSelectedStack() != null) {
                        if (player.inventory.getSelectedIndex() == 36) {
                            player.inventory.readdItem(player.inventory.getSelectedIndex());
                        } else {
                            player.inventory.moveItems(player.inventory.getSelectedIndex(), 36);
                        }
                    }
                    lastSelected = System.currentTimeMillis();
                }
            }
        }
    }
}
