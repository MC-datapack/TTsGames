package dev.TTs.TTsGames.Games.PixelQuest.usable;

import dev.TTs.TTsGames.Games.PixelQuest.entity.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.gui.BasicArrayInventory;
import dev.TTs.TTsGames.Games.PixelQuest.gui.InventoryOutOfBoundsException;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemCountException;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.json.RecipeFormats;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;
import dev.TTs.lang.Buggy;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame.drop;
import static dev.TTs.TTsGames.Main.Textures;
import static dev.TTs.TTsGames.Main.logger;

@Buggy(reason = {"Klick area to open is broken", "Item Transfer by clicking on a recipe output is broken", "Available recipes are broken"})
public class CraftingTable extends GameObject {
    public BasicArrayInventory inventory;
    private transient RecipeFormats.Crafting[] recipes;
    private final List<RecipeFormats.Crafting> possibleRecipes;

    public transient boolean open = false;

    public CraftingTable() {
        super(730, 380, 32, 32);
        recipes = PixelQuestJsonReader.getAllCraftingTableRecipes();
        inventory = new BasicArrayInventory(10) {
            @Override
            public Point[] slotPositions() {
                return new Point[]{
                        new Point(700, 394),
                        new Point(716, 394),
                        new Point(732, 394),
                        new Point(700, 380),
                        new Point(716, 380),
                        new Point(732, 380),
                        new Point(700, 364),
                        new Point(716, 364),
                        new Point(732, 364),
                        new Point(730, 380)
                };
            }
        };
        possibleRecipes = new ArrayList<>();
    }

    @Override
    public void update() {
        updatePossibleRecipes();

        if (drop) {
            inventory.dropSelectedStack(new Point(PixelQuestGame.game.player.x, PixelQuestGame.game.player.y));
        }
    }

    private void updatePossibleRecipes() {
        possibleRecipes.clear();
        for (RecipeFormats.Crafting recipe : recipes) {
            boolean match = true;
            Map<Integer, Item> inputMap = recipe.getInput();
            for (Map.Entry<Integer, Item> entry : inputMap.entrySet()) {
                int slot = entry.getKey();
                Item requiredItem = entry.getValue();
                boolean found = false;
                for (int i = 0; i < inventory.getMaxSize(); i++) {
                    ItemStack itemStack = inventory.getItemStack(i);
                    if (itemStack != null && itemStack.getItem().equals(requiredItem) && itemStack.getCount() >= 1) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    match = false;
                    break;
                }
            }
            if (match) {
                possibleRecipes.add(recipe);
            }
        }
    }

    @Override
    public void render(Graphics g, int y, int x) {
        g.drawImage(Textures[4][2][2].toImage(), 730, 380, 32, 32, null);

        if (getBounds().contains(PixelQuestGame.game.mouseClickPosition)) {
            open = true;
            inventory.drawInventory(g);
            drawPossibleRecipes(g);
        } else {
            open = false;
        }
    }

    private void drawPossibleRecipes(Graphics g) {
        int offsetX = 800;
        int offsetY = 460;
        for (int i = 0; i < possibleRecipes.size(); i++) {
            RecipeFormats.Crafting recipe = possibleRecipes.get(i);
            g.drawImage(recipe.getOutput().getItem().identifier().getItemTexture().toImage(), offsetX, offsetY + i * 32, 32, 32, null);
        }
    }

    @Override
    public void onCollision(GameObject with) {}

    public void receiveItem(ItemStack item) {
        for (RecipeFormats.Crafting recipe : recipes) {
            Map<Integer, Item> inputMap = recipe.getInput();
            for (int i = 0; i < 9; i++) {
                if (item.getItem().equals(inputMap.get(i))) {
                    if (inventory.isEmpty(i)) {
                        inventory.replaceSlot(i, item);
                        return;
                    } else if (item.getItem().equals(inventory.getItem(i))) {
                        try {
                            inventory.addItem(i, item.getCount());
                        } catch (InventoryOutOfBoundsException e) {
                            logger.error("Failed to add item: %s", e.getMessage());
                        }
                        return;
                    }
                }
            }
        }
    }

    public void moveItemsToCraftingTable(RecipeFormats.Crafting recipe) {
        Map<Integer, Item> inputMap = recipe.getInput();
        for (Map.Entry<Integer, Item> entry : inputMap.entrySet()) {
            int slot = entry.getKey();
            Item item = entry.getValue();
            for (int i = 0; i < PixelQuestGame.game.player.inventory.getMaxSize(); i++) {
                ItemStack itemStack = PixelQuestGame.game.player.inventory.getItemStack(i);
                if (itemStack != null && itemStack.getItem().equals(item)) {
                    try {
                        int count = itemStack.getCount();
                        inventory.replaceSlot(slot, new ItemStack(item, count));
                        PixelQuestGame.game.player.inventory.removeItem(new ItemStack(item, count));
                    } catch (ItemCountException | InventoryOutOfBoundsException e) {
                        logger.error("Failed to move item to crafting table: %s", e.getMessage());
                    }
                    break;
                }
            }
        }

        ItemStack outputItem = recipe.getOutput();
        try {
            PixelQuestGame.game.player.inventory.addItem(outputItem);
        } catch (InventoryOutOfBoundsException e) {
            logger.error("Player's inventory is full: %s", e.getMessage());
        }
    }

    @Override
    public Identifier id() {
        return new Identifier("crafting_table");
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        recipes = PixelQuestJsonReader.getAllCraftingTableRecipes();
    }
}
