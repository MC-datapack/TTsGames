package dev.TTs.TTsGames.Games.PixelQuest.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.HashSet;

import static dev.TTs.TTsGames.Main.Textures;

public class Player extends GameObject {
    private int speed;
    private Set<Integer> pressedKeys;

    public Player(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.pressedKeys = new HashSet<>(); // Initialize the pressedKeys set
    }

    @Override
    public void update() {
        // Update player position based on multiple keys pressed
        if (pressedKeys.contains(KeyEvent.VK_W)) {
            if (y >= 0) {
                y -= speed;
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_S)) {
            if (y <= 750) {
                y += speed;
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            if (x >= 0) {
                x -= speed;
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_D)) {
            if (x <= 750) {
                x += speed;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Textures[4][4].toImage(), x, y, null);
    }

    @Override
    public void onCollision(GameObject other) {
        // Handle collision with other game objects
    }

    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    public void setPressedKeys(Set<Integer> pressed) {
        pressedKeys = pressed;
    }
}
