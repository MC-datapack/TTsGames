package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.util.Identifier;

import java.awt.*;
import java.io.*;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

public abstract class Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;

    public int x, y;
    protected int width, height;
    protected transient long lastCollisionTime;
    protected static final long COLLISION_COOLDOWN = 250;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lastCollisionTime = System.currentTimeMillis();
    }

    public abstract void update();
    public abstract void render(Graphics g, int x, int y);
    public abstract void onCollision(Entity with);

    public abstract void dropItem();

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean canCollide() {
        return System.currentTimeMillis() - lastCollisionTime >= COLLISION_COOLDOWN;
    }

    public void registerCollision() {
        lastCollisionTime = System.currentTimeMillis();
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    public abstract Identifier id();
}
