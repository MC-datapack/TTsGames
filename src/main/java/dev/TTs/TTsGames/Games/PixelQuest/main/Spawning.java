package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entity.*;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.*;
import dev.TTs.TTsGames.Games.PixelQuest.entity.usable.Chest;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.world.World;

import java.awt.Point;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

public class Spawning implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;

    private static final int[] defaultSpeeds = {
            2, // Fox
            1, // Sheep
            2  // Enemy
    };
    
    private final World world;
    private final Random rand;
    
    public Spawning(World world) {
        this.world = world;
        this.rand = new Random(world.seed.getJavaUtilRandomSeed());
    }

    private final List<Point> existingTreeLocations = new ArrayList<>();
    private final List<Point> existingChestLocations = new ArrayList<>();

    public void initGameObjects() {
        //world.addGameObject(new Furnace());
        //world.addGameObject(new CraftingTable());
        world.addGameObject(new Fox(200, 200, defaultSpeeds[0]));
        world.addGameObject(new Sheep(500, 500, defaultSpeeds[1]));

        checkAndSpawnTrees();
        checkAndSpawnChests();
    }

    public void gameObjectActions() {
        double randomCondition = world.dayTime.isNight() ? 0.00275 : 0.00025;
        double maxMobs = world.dayTime.isNight() ? 50 : 25;
        int mobs = 0;

        for (Entity entity : world.getGameObjects()) {
            if (entity instanceof Enemy damageable && world.dayTime.isDay()) {
                damageable.damage(1);
            }
            if (entity instanceof Mob) {
                mobs++;
                if (!isWithinRange(entity.x, entity.y, world.player.x, world.player.y, 4500)) {
                    world.getGameObjects().remove(entity);
                }
            }
        }

        if (rand.nextDouble(1) < randomCondition && mobs < maxMobs) {
            world.addGameObject(randomMob(
                    world.player.x - 500, world.player.x + 500,
                    world.player.y - 500, world.player.y + 500));
        }

        checkAndSpawnTrees();
        checkAndSpawnChests();
    }

    private Mob randomMob(int minX, int maxX, int minY, int maxY) {
        int mob = rand.nextInt(world.dayTime.isDay() ? 2 : 6);
        return switch (mob) {
            case 0 -> new Fox(rand.nextInt(minX, maxX), rand.nextInt(minY, maxY), defaultSpeeds[0]);
            case 1 -> new Sheep(rand.nextInt(minX, maxX), rand.nextInt(minY, maxY), defaultSpeeds[1]);
            case 2, 3, 4, 5 -> new Monster(rand.nextInt(minX, maxX), rand.nextInt(minY, maxY), defaultSpeeds[2]);
            default -> null;
        };
    }

    private void checkAndSpawnTrees() {
        int treeCount = 2;
        int oakToSpruceRatio = 5;
        int playerX = world.player.x;
        int playerY = world.player.y;

        for (int i = 0; i < treeCount; i++) {
            int x = rand.nextInt(playerX - 1500, playerX + 1500);
            int y = rand.nextInt(playerY - 1500, playerY + 1500);

            if (isWithinRange(x, y, playerX, playerY, 1500) && isTreeNotNearby(x, y)) {
                world.addGameObject(new Tree(x, y));
                existingTreeLocations.add(new Point(x, y));
            }
        }
    }

    private boolean isTreeNotNearby(int x, int y) {
        for (Point point : existingTreeLocations) {
            int dx = x - point.x;
            int dy = y - point.y;
            int distance = (int) Math.sqrt(dx * dx + dy * dy);
            if (distance < 800) {
                return false;
            }
        }
        return true;
    }

    private void checkAndSpawnChests() {
        int chestCount = 2;
        int playerX = world.player.x;
        int playerY = world.player.y;

        for (int i = 0; i < chestCount; i++) {
            int x = rand.nextInt(playerX - 1500, playerX + 1500);
            int y = rand.nextInt(playerY - 1500, playerY + 1500);

            if (isWithinRange(x, y, playerX, playerY, 1500) && isChestNotNearby(x, y)) {
                world.addGameObject(new Chest(x, y, PixelQuestJsonReader.getRandomChestLootTable()));
                existingChestLocations.add(new Point(x, y));
            }
        }
    }

    private boolean isChestNotNearby(int x, int y) {
        for (Point point : existingChestLocations) {
            int dx = x - point.x;
            int dy = y - point.y;
            int distance = (int) Math.sqrt(dx * dx + dy * dy);
            if (distance < 800) {
                return false;
            }
        }
        return true;
    }

    private boolean isWithinRange(int x, int y, int playerX, int playerY, int range) {
        int dx = x - playerX;
        int dy = y - playerY;
        return (dx * dx + dy * dy) <= (range * range);
    }
}
