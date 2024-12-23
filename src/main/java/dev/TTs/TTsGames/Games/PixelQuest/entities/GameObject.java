package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected int width() {
        return 20;
    }
    protected int height() {
        return 20;
    }
    protected long lastCollisionTime;
    protected static final long COLLISION_COOLDOWN = 1000;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.lastCollisionTime = System.currentTimeMillis();
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void onCollision(GameObject with);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width(), height());
    }

    public boolean canCollide() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCollisionTime >= COLLISION_COOLDOWN) {
            return true;
        }
        return false;
    }

    public void registerCollision() {
        lastCollisionTime = System.currentTimeMillis();
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    public abstract Identifier id();
}
