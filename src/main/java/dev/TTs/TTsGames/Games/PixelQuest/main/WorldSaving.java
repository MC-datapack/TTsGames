package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.util.LongSeed;
import dev.TTs.TTsGames.Games.PixelQuest.util.Seed;
import dev.TTs.TTsGames.Games.PixelQuest.world.World;
import dev.TTs.lang.Array;
import dev.TTs.swing.Region;
import dev.TTs.swing.TLabel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static dev.TTs.TTsGames.Main.logger;
import static dev.TTs.resources.Translations.PixelQuest;

public class WorldSaving {
    public static final long serialVersion = 105L;

    public static final String[] SaveFormats = {
            ".testsav",
            ".sav",
            ".pq"
    };
    public static final TLabel restart = new TLabel(PixelQuest[10]);
    public static World world;

    private static String savePath(String name) {
        return Array.stringContainsAnyOf(name, SaveFormats) ? System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves/" + name :
                System.getProperty("user.home") + "/AppData/Roaming/TTsGames/PixelQuest/saves/" + name + ".pq";
    }

    public static void saveGame() {
        if (world != null) saveGame(world);
    }

    public static void saveGame(World world) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath(world.name)))) {
            oos.writeObject(world);
            oos.flush();
        } catch (IOException e) {
            logger.error("Error Saving Game: %s", e.getMessage());
        }
    }

    public static void loadGame(String name) {
        if (name.endsWith(".sav")) {
            throwLoadingError(PixelQuest[8]);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath(name)))) {
                world = (World) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                handleLoadingException(e);
            }
            return;
        }

        world = new World(name, false, false, new LongSeed(0L));
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath(name)))) {
            world = (World) ois.readObject();

            // Check and assign player
            boolean playerFound = false;
            for (Entity obj : world.getGameObjects()) {
                if (obj instanceof Player) {
                    world.player = (Player) obj;
                    playerFound = true;
                    logger.info("Player found and assigned: " + obj);
                    break;
                }
            }

            if (!playerFound) {
                world.player = new Player(0, 0);
                world.addGameObject(world.player);
                logger.warn("Player not found. Created new player object.");
            }

            PixelQuestGame.game.loadedGame = true;
            logger.info("Game loaded successfully.");
        } catch (InvalidClassException | ClassNotFoundException e) {
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

    private static void handleInvalidClassException(Exception e, String name) {
        logger.warn("Invalid class exception occurred: %s", e.getMessage());
        JOptionPane.showConfirmDialog(
                null,
                "The save file version is incompatible.",
                "Incompatible Save File",
                JOptionPane.OK_CANCEL_OPTION
        );
    }

    private static void throwLoadingError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        PixelQuestGame.game.setBackground(Color.RED);
        restart.setBounds(250, 250, 250, 250);
        restart.setColor(Region.FOREGROUND, Color.WHITE);
        PixelQuestGame.game.add(restart);
    }

    public static void createNewWorld(String name, String seed) {
        world = new World(name, true, logger.debug(Seed.getOptimal(seed)));
    }

    public static void unloadGame() {
        logger.debug("Try unloading Game: %s: %s", world.name, world);
        PixelQuestGame.game.loadedGame = false;
        PixelQuestGame.settings.Hide();
        WorldSaving.saveGame();
        PixelQuestGame.game.repaint();
        PixelQuestGame.game.revalidate();
        PixelQuestGame.game.stop();
        PixelQuestGame.game.initSavePanel();
        PixelQuestGame.game.displaySaveFrame();
    }
}
