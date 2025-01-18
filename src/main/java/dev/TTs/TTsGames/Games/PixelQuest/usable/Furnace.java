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
import dev.TTs.TTsGames.Games.PixelQuest.util.Tags;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame.drop;
import static dev.TTs.TTsGames.Main.Textures;
import static dev.TTs.TTsGames.Main.logger;

public class Furnace extends GameObject {
    public BasicArrayInventory inventory;
    private transient RecipeFormats.Furnace[] recipes;
    private transient Item[] fuelItems;

    public transient boolean open = false;

    public Furnace() {
        super(730, 420, 32, 32);
        recipes = PixelQuestJsonReader.getAllFurnaceRecipes();
        fuelItems = PixelQuestJsonReader.readTag(Tags.Item.FURNACE_FUEL);
        inventory = new BasicArrayInventory(3) {
            @Override
            public Point[] slotPositions() {
                return new Point[]{
                        new Point(700, 400), // Input
                        new Point(700, 440), // Fuel
                        new Point(745, 420)  // Output
                };
            }
        };
    }

    @Override
    public void update() {
        for (RecipeFormats.Furnace recipe : recipes) {
            try {
                Item input = inventory.getItem(0);
                Item fuel = inventory.getItem(1);
                if (input.equals(recipe.getInput())) {
                    for (Item item : fuelItems) {
                        if (fuel.equals(item)) {
                            if (inventory.isEmpty(2)) {
                                inventory.removeItem(0);
                                inventory.removeItem(1);
                                inventory.replaceSlot(2, recipe.getOutput());
                                break;
                            } else if (inventory.getItem(2).equals(recipe.getOutput().getItem())) {
                                inventory.removeItem(0);
                                inventory.removeItem(1);
                                inventory.addItem(2, recipe.getOutput().getCount());
                                break;
                            }
                        }
                    }
                }
            } catch (NullPointerException ignored) {}
        }
        if (drop) {
            inventory.dropSelectedStack(new Point(PixelQuestGame.game.player.x, PixelQuestGame.game.player.y));
        }
    }

    @Override
    public void render(Graphics g, int y, int x) {
        if (getBounds().contains(PixelQuestGame.game.mouseClickPosition)) {
            open = true;
            inventory.drawInventory(g);
        } else {
            open = false;
            g.drawImage(Textures[4][2][0].toImage(), 730, 420, 32, 32, null);
        }
    }

    @Override
    public void onCollision(GameObject with) {}

    public void receiveItem(ItemStack item) {
        for (RecipeFormats.Furnace furnace : recipes) {
            if (item.getItem() == furnace.getInput()) {
                if (inventory.isEmpty(0)) {
                    inventory.replaceSlot(0, item);
                    return;
                } else if (item.getItem().equals(inventory.getItem(0))) {
                    inventory.addItem(0, item.getCount());
                    return;
                }
            }
        }
        for (Item item1 : fuelItems) {
            if (item.getItem() == item1) {
                if (inventory.isEmpty(1)) {
                    inventory.replaceSlot(1, item);
                    return;
                } else if (item.getItem().equals(inventory.getItem(1))) {
                    try {
                        inventory.addItem(1, item.getCount());
                        return;
                    } catch (InventoryOutOfBoundsException | ItemCountException e) {
                        logger.error("Failed to add item: %s", e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public Identifier id() {
        return new Identifier("furnace");
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.readObject();
        recipes = PixelQuestJsonReader.getAllFurnaceRecipes();
        fuelItems = PixelQuestJsonReader.readTag(Tags.Item.FURNACE_FUEL);
    }
}
