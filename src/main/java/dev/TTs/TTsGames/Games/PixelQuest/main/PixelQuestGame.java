package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entity.*;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.TTsGames.Games.PixelQuest.usable.CraftingTable;
import dev.TTs.TTsGames.Games.PixelQuest.usable.Furnace;
import dev.TTs.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;
import static dev.TTs.TTsGames.Main.logger;

public class PixelQuestGame extends TPanel implements Runnable, Serializable {
    public static final int width = 800, height = 800;
    @Serial
    private static final long serialVersionUID = serialVersion;
    public static PixelQuestGame game;
    public static Settings settings;
    CopyOnWriteArrayList<GameObject> gameObjects;
    public Player player;
    private final Set<Integer> pressedKeys;
    private boolean running;
    private Thread thread;
    public int fps;
    private final int refreshRate;
    public Point mousePosition = new Point();
    public Point mouseClickPosition = new Point();
    public boolean showSettings = false;
    public static boolean drop = false;
    private DefaultListModel<String> saveListModel;
    private JList<String> saveList;
    private JPanel savePanel;
    public boolean loadedGame;

    public PixelQuestGame(int refreshRate) {
        File dict = new File(System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves");
        if (!dict.exists()) {
            logger.info(dict.mkdirs() ? "Created dictionary" : "Failed to create dictionary or found dictionary");
        } else {
            logger.info("Dictionary exists");
        }
        this.refreshRate = refreshRate == 0 ? 60 : refreshRate;
        Items.load();
        gameObjects = new CopyOnWriteArrayList<>();
        player = new Player(0, 0);
        gameObjects.add(player);
        pressedKeys = new HashSet<>();
        player.setPressedKeys(pressedKeys);
        settings = new Settings();
        settings.setPreferredSize(new Dimension(300, 400)); // Ensure size is set
        this.setLayout(null);
        initSettingsComponents();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                player.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    toggleSettings();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
                player.keyReleased(e);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                player.inventory.handleMouseMoved();
                mousePosition = e.getPoint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickPosition = e.getPoint();
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        initSavePanel();
        displaySaveFrame();
    }

    public static void shutdown() {
        WorldSaving.saveGame();
    }

    private void initSettingsComponents() {
        settings.setBounds(50, 50, 500, 400);
        add(settings, BorderLayout.CENTER);
    }

    private void toggleSettings() {
        if (System.currentTimeMillis() - settings.lastSettings >= 250) {
            showSettings = !showSettings;
            settings.setVisible(showSettings);
            settings.lastSettings = System.currentTimeMillis();
            repaint();
            revalidate();
            if (showSettings) {
                stop();
            } else {
                start();
            }
        }
    }

    private void initSavePanel() {
        saveListModel = new DefaultListModel<>();
        saveList = new JList<>(saveListModel);
        saveList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedSave = saveList.getSelectedValue();
                WorldSaving.gameName = selectedSave;
                if (selectedSave != null) {
                    WorldSaving.loadGame(selectedSave);
                    this.remove(savePanel);
                    revalidate();
                    repaint();
                    start();
                }
            }
        });
        TButton newGameButton = getTButton();
        savePanel = new JPanel(new BorderLayout());
        savePanel.add(new JScrollPane(saveList), BorderLayout.CENTER);
        savePanel.add(newGameButton, BorderLayout.SOUTH);
        savePanel.setSize(300, 400);
        savePanel.setVisible(false);
        this.setLayout(null);
        this.add(savePanel);
        savePanel.setBounds(50, 50, 300, 400);
    }

    private TButton getTButton() {
        TButton newGameButton = new TButton("Create New Game");
        newGameButton.addActionListener(e -> {
            String newGameName = JOptionPane.showInputDialog(this, "Enter name for new game:");
            if (newGameName != null && !newGameName.trim().isEmpty()) {
                this.remove(savePanel);
                createNewGame(newGameName);
                WorldSaving.loadGame(newGameName);
                revalidate();
                repaint();
                start();
            }
        });
        return newGameButton;
    }


    public void displaySaveFrame() {
        saveListModel.clear();
        File saveDir = new File(System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves/");
        if (saveDir.exists() && saveDir.isDirectory()) {
            for (File file : Objects.requireNonNull(saveDir.listFiles((dir, name) -> {
                for (String s : WorldSaving.SaveFormats) {
                    if (name.endsWith(s)) {
                        return true;
                    }
                }
                return false;
            }))) {
                saveListModel.addElement(file.getName().replace(".pq", ""));
            }
        }
        savePanel.setVisible(true);
    }

    public void createNewGame(String name) {
        WorldSaving.gameName = name;
        gameObjects.clear();
        player = new Player(0, 0);
        gameObjects.add(player);
        Spawning.initGameObjects();
        WorldSaving.saveGame(name);
    }

    @Override
    public void run() {
        if (settings == null) {
            settings = new Settings();
            Spawning.initGameObjects();
        }
        long lastUpdateTime = System.nanoTime();
        long lastRepaintTime = System.nanoTime();
        double nsPerUpdate = 1000000000.0 / 60.0;
        double nsPerRepaint;
        long fpsLastTime = System.currentTimeMillis();
        int frames = 0;

        int i = 0;

        while (running) {
            if (settings.VSync) {
                nsPerRepaint = 1000000000.0 / refreshRate;
            } else {
                nsPerRepaint = 1000000000.0 / Double.MAX_VALUE;
            }

            long now = System.nanoTime();

            if ((now - lastUpdateTime) >= nsPerUpdate) {
                update();
                Spawning.gameObjectActions();
                lastUpdateTime += (long) nsPerUpdate;
                i++;
                if (i > 1000) {
                    WorldSaving.saveGame();
                }
            }

            if ((now - lastRepaintTime) >= nsPerRepaint) {
                repaint();
                frames++;
                lastRepaintTime += (long) nsPerRepaint;
            }

            if (System.currentTimeMillis() - fpsLastTime >= 1000) {
                fps = frames;
                frames = 0;
                fpsLastTime += 1000;
            }

            try {
                long sleepTime = Math.min((long) (lastUpdateTime + nsPerUpdate - System.nanoTime()),
                        (long) (lastRepaintTime + nsPerRepaint - System.nanoTime())) / 1000000;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                logger.error("Failed to sleep: %s", e.getMessage());
            }
        }
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
        revalidate();
        repaint();
        requestFocusInWindow();
        logger.info("Started game");
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.error("Failed to stop: %s", e.getMessage());
        }
    }

    private void update() {
        for (GameObject obj : gameObjects) {
            obj.update();
        }
        drop = false;
        checkCollisions();
        DayTime.update();
    }

    private void checkCollisions() {
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

    public void addGameObject(GameObject gameObject) {
        SwingUtilities.invokeLater(() -> gameObjects.add(gameObject));
    }

    public void addMultipleGameObjects(List<GameObject> gameObjects) {
        SwingUtilities.invokeLater(() -> this.gameObjects.addAll(gameObjects));
    }

    public void removeGameObject(GameObject gameObject) {
        logger.info("Attempting to remove GameObject");
        SwingUtilities.invokeLater(() -> {
            gameObjects.remove(gameObject);
            logger.info("Removed GameObject: " + gameObject);
        });
    }

    public CopyOnWriteArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public static Furnace getFurnace() {
        for (GameObject obj : game.gameObjects) {
            if (obj instanceof Furnace furnace) {
                return furnace;
            }
        }
        return null;
    }

    public static CraftingTable getCraftingTable() {
        for (GameObject obj : game.gameObjects) {
            if (obj instanceof CraftingTable craftingTable) {
                return craftingTable;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (loadedGame) {
            Renderer.paint((Graphics2D) g);
        }
    }

    public void unloadGame() {
        loadedGame = false;
        settings.Hide();
        WorldSaving.saveGame(WorldSaving.gameName);
        repaint();
        revalidate();
        stop();
        initSavePanel();
        displaySaveFrame();
    }
}
