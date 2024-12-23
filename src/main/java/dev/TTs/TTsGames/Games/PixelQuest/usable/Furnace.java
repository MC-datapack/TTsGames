package dev.TTs.TTsGames.Games.PixelQuest.usable;

import dev.TTs.TTsGames.Games.PixelQuest.entities.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.gui.BasicInventory;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.json.RecipeFormats;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.awt.*;

import static dev.TTs.TTsGames.Main.Textures;

public class Furnace extends GameObject {
    public BasicInventory inventory;
    private RecipeFormats.Furnace[] recipes;

    public Furnace() {
        super(730, 420);
        recipes = PixelQuestJsonReader.getAllFurnaceRecipes();
        inventory = new BasicInventory(3) {
            @Override
            public Point[] slotPositions() {
                return new Point[] {
                        new Point(700, 400), //Input
                        new Point(700, 440), //Fuel
                        new Point(745, 420)  //Output
                };
            }
        };
    }

    @Override
    protected int width() {
        return 32;
    }

    @Override
    protected int height() {
        return 32;
    }

    @Override
    public void update() {
        for (RecipeFormats.Furnace recipe : recipes) {
            try {
                if (inventory.getItem(1).toItem() == recipe.getInput()) {
                    inventory.removeItem(0);
                    inventory.replaceSlot(2, recipe.getOutput());
                }
            } catch (NullPointerException ignored) {}
        }
    }

    @Override
    public void render(Graphics g) {
        if (getBounds().contains(PixelQuestGame.game.mouseClickPosition)) {
            inventory.paintInventory(g);
        } else {
            g.drawImage(Textures[4][2][0].toImage(), x, y, 32, 32,null);
        }
    }

    @Override
    public void onCollision(GameObject with) {

    }

    @Override
    public Identifier id() {
        return new Identifier("furnace");
    }
}
