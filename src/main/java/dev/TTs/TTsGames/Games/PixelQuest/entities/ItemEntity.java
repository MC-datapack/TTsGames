package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.awt.*;

import static dev.TTs.TTsGames.Main.timer;

public class ItemEntity extends GameObject {
    private final ItemStack stack;
    private final int x, y;

    public ItemEntity(ItemStack stack, int x, int y) {
        super(stack == null ? -500000000 : x, stack == null ? -500000000 : y);
        this.stack = stack;
        this.x = x;
        this.y = y;
        this.lastCollisionTime = 0;
    }

    public ItemEntity(ItemStack stack, Point point) {
        this(stack, point.x, point.y);
    }

    @Override
    public void update() {
        if (stack == null) {
            PixelQuestGame.game.removeGameObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        if (stack != null) {
            g.drawImage(stack.getItem().toItem().identifier().getItemTexture().toImage(), x, y, 32, 32, null);
        }
    }

    @Override
    public void onCollision(GameObject with) {}

    @Override
    public Identifier id() {
        return new Identifier("item");
    }

    public ItemStack getStack() {
        return stack;
    }
}
