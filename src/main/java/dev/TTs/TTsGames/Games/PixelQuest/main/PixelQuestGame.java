package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entities.*;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.usable.Furnace;
import dev.TTs.swing.TPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.TTs.TTsGames.Main.*;

public class PixelQuestGame extends TPanel implements Runnable, GameObjectRendering {
    public static PixelQuestGame game = new PixelQuestGame();

    private boolean running;
    private Thread thread;
    private final List<GameObject> gameObjects;
    public final Player player;
    private final Set<Integer> pressedKeys;
    private int fps;
    private int frames;

    public Point mousePosition = new Point();
    public Point mouseClickPosition = new Point();

    private Image background = Textures[4][0][1].toImage();

    public PixelQuestGame() {
        Items.registerItems();
        gameObjects = new ArrayList<>();
        player = new Player(100, 100, 5);
        gameObjects.add(player);
        initGameObjects();
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
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                player.inventory.handleMouseMoved();
                mousePosition = e.getPoint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                stop();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickPosition = e.getPoint();
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
        long fpsLastTime = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            frames++;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();

            if (System.currentTimeMillis() - fpsLastTime >= 1000) {
                fps = frames;
                frames = 0;
                fpsLastTime += 1000;
            }

            if (player.died()) {
                background = Textures[4][0][2].toImage();
                while (player.died()) {
                    update();
                }
                initGameObjects();
                background = Textures[4][0][1].toImage();
            }
        }
    }

    private void initGameObjects() {
        //gameObjects.add(new Furnace());
        gameObjects.add(new Fox(200, 200, 2));
        gameObjects.add(new Sheep(500, 500, 0));
        gameObjects.add(new Monster(300, 300, 2));
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.error("Failed to stop: %s", e);
        }
    }

    private void update() {
        synchronized (gameObjects) {
            for (GameObject obj : gameObjects) {
                obj.update();
            }
            checkCollisions();
        }
    }

    private void checkCollisions() {
        synchronized (gameObjects) {
            for (int i = 0; i < gameObjects.size(); i++) {
                for (int j = i + 1; j < gameObjects.size(); j++) {
                    GameObject obj1 = gameObjects.get(i);
                    GameObject obj2 = gameObjects.get(j);
                    if (obj1.getBounds().intersects(obj2.getBounds())) {
                        if (obj1.canCollide() && obj2.canCollide()) {
                            obj1.onCollision(obj2);
                            obj2.onCollision(obj1);
                            obj1.registerCollision();
                            obj2.registerCollision();
                        }
                    }
                }
            }
        }
    }

    @Override
    public synchronized void addGameObject(GameObject gameObject) {
        logger.info("Attempting to add GameObject");
        synchronized (gameObjects) {
            SwingUtilities.invokeLater(() -> {
                gameObjects.add(gameObject);
                logger.info("Added GameObject: " + gameObject);
            });
        }
    }

    @Override
    public synchronized void addMultipleGameObjects(GameObject[] gameObjects) {
        addMultipleGameObjects(List.of(gameObjects));
    }

    @Override
    public synchronized void addMultipleGameObjects(List<GameObject> gameObjects) {
        logger.info("Attempting to add GameObject");
        synchronized (gameObjects) {
            SwingUtilities.invokeLater(() -> {
                this.gameObjects.addAll(gameObjects);
                logger.info("Added GameObjects: " + gameObjects);
            });
        }
    }

    @Override
    public synchronized void removeGameObject(GameObject gameObject) {
        logger.info("Attempting to remove GameObject");
        synchronized (gameObjects) {
            SwingUtilities.invokeLater(() -> {
                gameObjects.remove(gameObject);
                logger.info("Removed GameObject: " + gameObject);
            });
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        if (!player.died()) {
            synchronized (gameObjects) {
                for (GameObject obj : gameObjects) {
                    obj.render(g);
                }
            }
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString(player.deathReason(), 100, 200);
        }

        // Draw FPS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("FPS: " + fps, 10, 20);
    }
}
