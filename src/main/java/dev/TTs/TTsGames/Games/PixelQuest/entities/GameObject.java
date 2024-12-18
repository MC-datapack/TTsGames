package dev.TTs.TTsGames.Games.PixelQuest.entities;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected int width = 20;  // Default width
    protected int height = 20; // Default height
    private long lastCollisionTime;
    private static final long COLLISION_COOLDOWN = 1000; // Cooldown in milliseconds

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.lastCollisionTime = 0;
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void onCollision(GameObject other);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean canCollide() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCollisionTime >= COLLISION_COOLDOWN) {
            lastCollisionTime = currentTime;
            return true;
        }
        return false;
    }
}
