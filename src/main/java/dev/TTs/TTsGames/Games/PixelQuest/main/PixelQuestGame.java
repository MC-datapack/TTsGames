package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.PixelQuest;
import dev.TTs.TTsGames.Games.PixelQuest.item.Items;
import dev.TTs.resources.Configs;
import dev.TTs.resources.Translations;
import dev.TTs.swing.*;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import static dev.TTs.TTsGames.Main.*;

public class PixelQuestGame extends TPanel implements Runnable, Serializable {
    public static int width, height;
    public static PixelQuestGame game;
    public static Settings settings;
    public final Set<Integer> pressedKeys;
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
    private TButton exit;
    private TPanel savePanel;
    public boolean loadedGame;
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "invisible cursor");

    public PixelQuestGame(int refreshRate, Rectangle screen, GraphicsDevice device) {
        this.setBounds(screen);
        width = screen.width;
        height = screen.height + 50;
        File dict = new File(System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves");
        if (!dict.exists()) {
            logger.info(dict.mkdirs() ? "Created dictionary" : "Failed to create dictionary or found dictionary");
        } else {
            logger.info("Dictionary exists");
        }
        this.refreshRate = refreshRate == 0 ? 60 : refreshRate;
        Items.load();
        pressedKeys = new HashSet<>();
        settings = new Settings();
        settings.setPreferredSize(new Dimension(300, 400));
        setLayout(null);
        initSettingsComponents();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                if (loadedGame) {
                    WorldSaving.world.player.keyPressed(e);
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        toggleSettings();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    configLoader.set(Configs.FULLSCREEN, !((Boolean) configLoader.get(Configs.FULLSCREEN)));
                    windows[7].getContentPane().setCursor(Cursor.getDefaultCursor());
                    windows[7].dispose();
                    loadedGame = false;
                    showSettings = false;
                    running = false;
                    new PixelQuest(new WindowInformation(false, new Point(windows[0].getX() - 300, windows[0].getY() - 300),
                            new Dimension(1200, 1000), Textures[4][0][0], Translations.Games[3] + Versions[13]));
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (loadedGame) {
                    pressedKeys.remove(e.getKeyCode());
                    WorldSaving.world.player.keyReleased(e);
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (WorldSaving.world != null && loadedGame) {
                    WorldSaving.world.player.inventory.handleMouseMoved();
                    mousePosition = e.getPoint();
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickPosition = e.getPoint();
            }
        });
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

    public void initSavePanel() {
        saveListModel = new DefaultListModel<>();
        saveList = new JList<>(saveListModel);
        saveList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                WorldSaving.loadGame(saveList.getSelectedValue());
                this.remove(savePanel);
                revalidate();
                repaint();
                start();
            }
        });
        TButton newGameButton = getTButton();
        exit = new TButton(Translations.PixelQuest[11]);
        exit.event(() -> windows[7].getWindowListeners()[0].windowClosing(new WindowEvent(windows[7], WindowEvent.WINDOW_CLOSING)));
        exit.Hide();
        savePanel = new TPanel();
        savePanel.setLayout(null);
        JScrollPane pane = new JScrollPane(saveList);
        pane.setBounds(0, 40, 750, 760);
        newGameButton.setBounds(0, 0, 325, 40);
        exit.setBounds(325, 0, 325, 40);
        savePanel.addP(pane, newGameButton, exit);
        savePanel.setSize(800, 800);
        savePanel.setVisible(false);
        savePanel.setBounds(50, 50, 800, 800);
        this.setLayout(null);
        add(savePanel);
    }

    private TButton getTButton() {
        TButton newGameButton = new TButton(Translations.PixelQuest[12]);
        newGameButton.addActionListener(e -> {
            String newGameName = JOptionPane.showInputDialog(this, Translations.PixelQuest[13]);
            String newGameSeed = (String) JOptionPane.showInputDialog(this, Translations.PixelQuest[14], new Random().nextLong());
            if (newGameName != null && newGameSeed != null && !newGameName.trim().isEmpty() && !newGameSeed.trim().isEmpty()) {
                this.remove(savePanel);
                WorldSaving.createNewWorld(newGameName, newGameSeed);
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
        exit.Show();
    }

    @Override
    public void run() {
        if (settings == null) {
            settings = new Settings();
        }
        long lastUpdateTime = System.nanoTime();
        long lastRepaintTime = System.nanoTime();
        double nsPerUpdate = 1000000000.0 / 60.0;
        double nsPerRepaint;
        long fpsLastTime = System.currentTimeMillis();
        int frames = 0;
        int i = 0;
        while (running) {
            if (showSettings || WorldSaving.world.anyInventoryOpened()) windows[7].getContentPane().setCursor(Cursor.getDefaultCursor());
            else windows[7].getContentPane().setCursor(invisibleCursor);

            if (settings.VSync) {
                nsPerRepaint = 1000000000.0 / refreshRate;
            } else {
                nsPerRepaint = 1000000000.0 / Double.MAX_VALUE;
            }
            long now = System.nanoTime();
            if ((now - lastUpdateTime) >= nsPerUpdate) {
                WorldSaving.world.update();
                lastUpdateTime += (long) nsPerUpdate;
                i++;
                if (i > 1000) {
                    WorldSaving.saveGame();
                    i = 0;
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
        windows[7].getContentPane().setCursor(Cursor.getDefaultCursor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (loadedGame) {
            Renderer.paint((Graphics2D) g, WorldSaving.world);
        }
    }
}