package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entity.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.lang.Array;
import dev.TTs.lang.IgnoreSerialVersionUIDObjectInputStream;
import dev.TTs.swing.Region;
import dev.TTs.swing.TLabel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static dev.TTs.TTsGames.Main.logger;
import static dev.TTs.resources.Translations.PixelQuest;

public class WorldSaving {
    public static final long serialVersion = 37L;

    public static final String[] SaveFormats = {
            ".testsav",
            ".sav",
            ".pq"
    };
    public static final TLabel restart = new TLabel(PixelQuest[10]);
    public static String gameName;

    private static String savePath(String name) {
        return Array.stringContainsAnyOf(name, SaveFormats) ? System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves/" + name :
                System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves/" + name + ".pq";
    }

    public static void saveGame() {
        if (gameName != null) saveGame(gameName);
        else logger.info("No PixelQuest game was started");
    }

    public static void saveGame(String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath(name)))) {
            oos.writeObject(PixelQuestGame.game.gameObjects);
        } catch (IOException e) {
            logger.error("Error Saving Game: %s", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadGame(String name) {
        gameName = name;
        PixelQuestGame.game.gameObjects.clear();
        logger.info("Attempting to load game: " + name);

        if (name.endsWith(".sav")) {
            throwLoadingError(PixelQuest[8]);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath(name)))) {
                PixelQuestGame.game.gameObjects = (CopyOnWriteArrayList<GameObject>) ois.readObject();
                PixelQuestGame.game.player = (Player) PixelQuestGame.game.gameObjects.get(0);
                PixelQuestGame.game.loadedGame = true;
            } catch (IOException | ClassNotFoundException e) {
                handleLoadingException(e);
            }
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath(name)))) {
            logger.info("Opened file input stream for save file: " + savePath(name));
            while (true) {
                try {
                    GameObject obj = (GameObject) ois.readObject();
                    logger.info("Successfully read object: " + obj);
                    if (obj != null) {
                        PixelQuestGame.game.addGameObject(obj);
                    }
                } catch (EOFException eof) {
                    logger.info("Reached end of file.");
                    break;
                } catch (Exception ex) {
                    logger.error("Failed to read a game object: %s", ex.getMessage());
                    break;
                }
            }

            // Check and assign player
            boolean playerFound = false;
            for (GameObject obj : PixelQuestGame.game.gameObjects) {
                if (obj instanceof Player) {
                    PixelQuestGame.game.player = (Player) obj;
                    playerFound = true;
                    logger.info("Player found and assigned: " + obj);
                    break;
                }
            }

            if (!playerFound) {
                PixelQuestGame.game.player = new Player(0, 0);
                PixelQuestGame.game.addGameObject(PixelQuestGame.game.player);
                logger.warn("Player not found. Created new player object.");
            }

            PixelQuestGame.game.loadedGame = true;
            logger.info("Game loaded successfully.");
        } catch (InvalidClassException e) {
            handleInvalidClassException(e, name);
        } catch (IOException e) {
            handleLoadingException(e);
        }
    }

    private static void handleLoadingException(Exception e) {
        logger.error("Failed loading game: %s", e.getMessage());
        try {
            if (e.getMessage() != null && e.getMessage().contains("local class incompatible: stream classdesc serialVersionUID")) {
                String[] split = e.getMessage().split("serialVersionUID = ");
                long save = Long.parseLong(split[1].split(",")[0]);
                long local = Long.parseLong(split[2]);
                throwLoadingError(String.format(PixelQuest[6], save, local));
            }
        } catch (Exception f) {
            logger.error("Error processing exception message: %s", f.getMessage());
        }
    }

    private static void handleInvalidClassException(InvalidClassException e, String name) {
        logger.warn("Invalid class exception occurred: %s", e.getMessage());
        int response = JOptionPane.showConfirmDialog(
                null,
                "The save file version is incompatible. Attempt to load anyway?",
                "Incompatible Save File",
                JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            try (IgnoreSerialVersionUIDObjectInputStream ois = new IgnoreSerialVersionUIDObjectInputStream(new FileInputStream(savePath(name)))) {
                PixelQuestGame.game.gameObjects = new CopyOnWriteArrayList<>();
                while (true) {
                    try {
                        GameObject obj = (GameObject) ois.readObject();
                        logger.info("Read object: " + obj);
                        if (obj != null) {
                            PixelQuestGame.game.addGameObject(obj);
                        }
                    } catch (EOFException eof) {
                        break;
                    } catch (Exception ex) {
                        logger.error("Failed to read a game object: %s", ex.getMessage());
                        break;
                    }
                }
                boolean playerFound = false;
                for (GameObject obj : PixelQuestGame.game.gameObjects) {
                    if (obj instanceof Player player) {
                        PixelQuestGame.game.player = player;
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound) {
                    PixelQuestGame.game.player = new Player(0, 0);
                }
                PixelQuestGame.game.loadedGame = true;
            } catch (IOException ex) {
                logger.error("Failed to load game: %s", ex.getMessage());
                throwLoadingError("Failed to load game even with an incompatible save file.");
            }
        } else {
            throwLoadingError("Save file loading aborted due to incompatibility.");
        }
    }

    private static void throwLoadingError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        PixelQuestGame.game.setBackground(Color.RED);
        restart.setBounds(250, 250, 250, 250);
        restart.setColor(Region.FOREGROUND, Color.WHITE);
        PixelQuestGame.game.add(restart);
    }
}
