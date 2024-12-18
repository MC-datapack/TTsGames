package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.item.Droppable;

import java.awt.Color;
import java.awt.Graphics;

import static dev.TTs.TTsGames.Main.Textures;
import static dev.TTs.TTsGames.Main.logger;

public class Animal extends GameObject implements Droppable {
    private int speed;
    private int direction; // 0: up, 1: down, 2: left, 3: right

    public Animal(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.direction = 0; // Initial direction
    }

    @Override
    public void update() {
        // Simple AI logic: move in a fixed direction and change direction on collision
        switch (direction) {
            case 0 -> {
                if (y >= 0) {
                    y -= speed;
                }
            }
            case 1 -> {
                if (y <= 750) {
                    y += speed;
                }
            }
            case 2 -> {
                if (x >= 0) {
                    x -= speed;
                }
            }
            case 3 -> {
                if (x <= 750) {
                    x += speed;
                }
            }
        }
        // Change direction occasionally (simple AI behavior)
        if (Math.random() < 0.02) {
            direction = (int) (Math.random() * 4);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Textures[4][3].toImage(), x, y, null);
    }

    @Override
    public void onCollision(GameObject other) {
        if (canCollide() && other instanceof Player) {
            dropItem();
            // Additional collision handling logic, e.g., reduce health or change direction
        } else if (canCollide() && (other instanceof Enemy || other instanceof Animal)) {
            // Example: Change direction on collision with another enemy or animal
            direction = (int) (Math.random() * 4);
        }
    }


    @Override
    public void dropItem() {
        logger.info("Animal dropped an item!");
        // Logic for dropping an item
    }
}
