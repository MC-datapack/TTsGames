package dev.TTs.TTsGames.Games.PixelQuest.entity.usable;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.entity.InventoryOwner;
import dev.TTs.TTsGames.Games.PixelQuest.gui.BasicArrayInventory;
import dev.TTs.TTsGames.Games.PixelQuest.gui.InventoryOutOfBoundsException;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemCountException;
import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.json.RecipeFormats;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;
import dev.TTs.util.Identifier;
import dev.TTs.TTsGames.Games.PixelQuest.util.Tags;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame.drop;
import static dev.TTs.TTsGames.Main.Textures;
import static dev.TTs.TTsGames.Main.logger;

public class Furnace extends Entity implements InventoryOwner {
    public BasicArrayInventory inventory;
    private transient RecipeFormats.Furnace[] recipes;
    private transient Item[] fuelItems;

    public transient boolean open = false;

    public Furnace() {
        super(PixelQuestGame.width - 70, 420, 32, 32);
        recipes = PixelQuestJsonReader.getAllFurnaceRecipes();
        fuelItems = PixelQuestJsonReader.readTag(Tags.Items.FURNACE_FUEL);
        inventory = new BasicArrayInventory(3)
                .customSlotPosition(0, new Point(PixelQuestGame.width - 100, 400))
                .customSlotPosition(1, new Point(PixelQuestGame.width - 100, 440))
                .customSlotPosition(2, new Point(PixelQuestGame.width - 55, 420));
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
            inventory.dropSelectedStack(new Point(WorldSaving.world.player.x, WorldSaving.world.player.y));
        }
    }

    @Override
    public void render(Graphics g, int y, int x) {
        if (getBounds().contains(PixelQuestGame.game.mouseClickPosition)) {
            open = true;
            inventory.drawInventory(g);
        } else {
            open = false;
            g.drawImage(Textures[4][2][0].toImage(), PixelQuestGame.width - 70, 420, 32, 32, null);
        }
    }

    @Override
    public void onCollision(Entity with) {}

    @Override
    public void dropItem() {}

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
        return Identifier.ofPixelQuest("furnace");
    }

    @Override
    public boolean opened() {
        return getBounds().contains(PixelQuestGame.game.mouseClickPosition);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        logger.debug("Try loading Furnace");
        try {
            ois.defaultReadObject();
        } catch (IOException e) {
            logger.error("Failed loading Furnace");
        }
        recipes = PixelQuestJsonReader.getAllFurnaceRecipes();
        fuelItems = PixelQuestJsonReader.readTag(Tags.Items.FURNACE_FUEL);
    }
}
