package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.TTsGames.Games.PixelQuest.item.ItemStack;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;
import dev.TTs.util.Identifier;

import java.awt.*;

import static dev.TTs.TTsGames.Main.timer;

public class ItemEntity extends Entity {
    private final ItemStack stack;

    public ItemEntity(ItemStack stack, int x, int y) {
        super(stack == null ? -500000000 : x, stack == null ? -500000000 : y, 32, 32);
        this.stack = stack;
        this.lastCollisionTime = 0;
    }

    public ItemEntity(ItemStack stack, Point point) {
        this(stack, point.x, point.y);
    }

    @Override
    public void update() {
        if (stack == null) {
            WorldSaving.world.removeGameObject(this);
        }
    }

    @Override
    public void render(Graphics g, int x, int y) {
        if (stack != null) {
            g.drawImage(stack.getItem().identifier().getItemTexture().toImage(), x, y, width, height, null);
        }
    }

    @Override
    public void onCollision(Entity with) {}

    @Override
    public void dropItem() {}

    @Override
    public Identifier id() {
        return Identifier.ofPixelQuest("item");
    }

    public ItemStack getStack() {
        return stack;
    }
}
