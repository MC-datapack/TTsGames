package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entities.Animal;
import dev.TTs.TTsGames.Games.PixelQuest.entities.Enemy;
import dev.TTs.TTsGames.Games.PixelQuest.entities.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.entities.Player;
import dev.TTs.swing.TFrame;
import dev.TTs.swing.TImage;
import dev.TTs.swing.WindowInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.TTs.TTsGames.Main.*;

public class PixelQuest extends JPanel implements Runnable {
    private boolean running;
    private Thread thread;
    private List<GameObject> gameObjects;
    private Player player;
    private Set<Integer> pressedKeys;

    public PixelQuest() {
        gameObjects = new ArrayList<>();
        player = new Player(100, 100, 5);
        gameObjects.add(player);
        gameObjects.add(new Animal(200, 200, 2)); // Add an animal
        gameObjects.add(new Enemy(300, 300, 3)); // Add an enemy
        pressedKeys = new HashSet<>();
        player.setPressedKeys(pressedKeys);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
                player.keyReleased(e);
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / 60; // ~60 ticks per second
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();

            try {
                Thread.sleep(2); // Sleep for a short duration to reduce CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        for (GameObject obj : gameObjects) {
            obj.update();
        }
        checkCollisions();
    }

    private void checkCollisions() {
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject obj1 = gameObjects.get(i);
                GameObject obj2 = gameObjects.get(j);

                if (obj1.getBounds().intersects(obj2.getBounds())) {
                    obj1.onCollision(obj2);
                    obj2.onCollision(obj1);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Textures[4][1].toImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        for (GameObject obj : gameObjects) {
            obj.render(g);
        }
    }

    public void create(WindowInformation information) {
        windows[7] = new TFrame(information.title());
        PixelQuest game = new PixelQuest();

        WindowOperations(7, information, game);
        game.start();
    }
}
