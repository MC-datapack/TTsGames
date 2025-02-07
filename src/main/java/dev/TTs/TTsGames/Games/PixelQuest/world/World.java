package dev.TTs.TTsGames.Games.PixelQuest.world;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.entity.InventoryOwner;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.main.DayTime;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.main.Spawning;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;
import dev.TTs.TTsGames.Games.PixelQuest.entity.usable.CraftingTable;
import dev.TTs.TTsGames.Games.PixelQuest.entity.usable.Furnace;
import dev.TTs.TTsGames.Games.PixelQuest.util.Seed;
import dev.TTs.util.RandomUtil;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;
import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.world;
import static dev.TTs.TTsGames.Main.logger;

public class World implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;

    public String name;
    public Seed<?> seed;

    private CopyOnWriteArrayList<Entity> entities;
    public transient Player player;
    public Spawning spawning;
    public DayTime dayTime;

    public World(String name, boolean create, boolean Do, Seed<?> seed) {
        this.name = name;
        this.seed = seed;
        RandomUtil.setSeed(seed);
        entities = new CopyOnWriteArrayList<>();
        if (Do) {
            player = new Player(0, 0);
            player.setPressedKeys(PixelQuestGame.game.pressedKeys);
            entities.add(player);
            if (create) {
                this.spawning = new Spawning(this);
                this.dayTime = new DayTime();
                spawning.initGameObjects();
                WorldSaving.saveGame(this);
            }
            WorldSaving.loadGame(name);
        }
    }

    public World(String name, boolean create, Seed<?> seed) {
        this(name, create, true, seed);
    }

    public void addGameObject(Entity entity) {
        entities.add(entity);
    }

    public void addMultipleGameObjects(List<Entity> entities) {
       this.entities.addAll(entities);
    }

    public void removeGameObject(Entity entity) {
        entities.remove(entity);
    }

    public CopyOnWriteArrayList<Entity> getGameObjects() {
        return entities;
    }

    public void update() {
        for (Entity obj : entities) {
            obj.update();
        }
        PixelQuestGame.drop = false;
        checkCollisions();
        dayTime.update();
        spawning.gameObjectActions();
    }

    private void checkCollisions() {
        for (int i = 0; i <  entities.size(); i++) {
            for (int j = i + 1; j <  entities.size(); j++) {
                Entity obj1 =  entities.get(i);
                Entity obj2 =  entities.get(j);
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

    public static Furnace getFurnace() {
        for (Entity obj : world.getGameObjects()) {
            if (obj instanceof Furnace furnace) {
                return furnace;
            }
        }
        return null;
    }

    public static CraftingTable getCraftingTable() {
        for (Entity obj : world.getGameObjects()) {
            if (obj instanceof CraftingTable craftingTable) {
                return craftingTable;
            }
        }
        return null;
    }

    public boolean anyInventoryOpened() {
        for (Entity entity : entities) {
            if (entity instanceof InventoryOwner inventoryOwner && inventoryOwner.opened()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name:").append(name).append(", Game Objects: \n");
        for (Entity object : entities) {
            builder.append(object).append('\n');
        }
        return builder.toString();
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        logger.debug("World name: %s", world == null ? "unknown" : world.name);
        logger.debug("Writing Size: %s", entities.size());
        oos.writeObject(name);
        oos.writeObject(spawning);
        oos.writeObject(seed);
        oos.writeObject(dayTime);
        oos.writeInt(entities.size());
        for (Entity entity : entities) {
            logger.debug("Saving: %s", entity);
            try {
                oos.writeObject(entity);
                logger.debug("Saved: %s", entity);
            } catch (IOException e) {
                logger.warn("Failed to save: %s", entity);
            }
        }
        oos.flush();
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        entities = new CopyOnWriteArrayList<>();
        this.name = (String) ois.readObject();
        this.spawning = (Spawning) ois.readObject();
        this.seed = (Seed<?>) ois.readObject();
        this.dayTime = (DayTime) ois.readObject();
        int size = ois.readInt();
        logger.debug("Size: %s", size);
        for (int i = 0; i < size; i++) {
            logger.debug("Reading index: %s", i);
            try {
                entities.add((Entity) ois.readObject());
                logger.debug("Read, all game Objects: %s", entities);
            } catch (IOException e) {
                logger.warn("Failed to read: %s", e);
            }
        }
        for (Entity obj : entities) {
            if (obj instanceof Player p) {
                this.player = p;
                return;
            }
        }
        logger.error("Did not find player: %s", entities);
        world = this;
    }
}
