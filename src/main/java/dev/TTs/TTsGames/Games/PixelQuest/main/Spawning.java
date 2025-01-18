package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entity.*;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.*;
import dev.TTs.TTsGames.Games.PixelQuest.entity.tree.OakTree;
import dev.TTs.TTsGames.Games.PixelQuest.entity.tree.SpruceTree;
import dev.TTs.TTsGames.Games.PixelQuest.entity.tree.Tree;
import dev.TTs.TTsGames.Games.PixelQuest.usable.CraftingTable;
import dev.TTs.TTsGames.Games.PixelQuest.usable.Furnace;

import java.util.concurrent.ThreadLocalRandom;

public class Spawning {
    private static final int[] defaultSpeeds = {
            2, // Fox
            1, // Sheep
            2  // Enemy
    };

    public static void initGameObjects() {
        PixelQuestGame.game.addGameObject(new Furnace());
        PixelQuestGame.game.addGameObject(new CraftingTable());
        PixelQuestGame.game.addGameObject(new Fox(200, 200, defaultSpeeds[0]));
        PixelQuestGame.game.addGameObject(new Sheep(500, 500, defaultSpeeds[1]));

        // Initial tree spawning
        checkAndSpawnTrees();
    }

    public static void gameObjectActions() {
        for (GameObject gameObject : PixelQuestGame.game.getGameObjects()) {
            if (gameObject instanceof Enemy enemy && DayTime.isDay()) {
                enemy.damage(1);
            }
        }

        for (GameObject gameObject : PixelQuestGame.game.getGameObjects()) {
            if (gameObject instanceof Mob || gameObject instanceof Tree) {
                if (!isWithinRange(gameObject.x, gameObject.y, PixelQuestGame.game.player.x, PixelQuestGame.game.player.y, 4500)) {
                    PixelQuestGame.game.getGameObjects().remove(gameObject);
                }
            }
        }

        double randomCondition = DayTime.isNight() ? 0.0125 : 0.001;
        double maxMobs = DayTime.isNight() ? 50 : 25;

        int mobs = 0;
        for (GameObject gameObject : PixelQuestGame.game.getGameObjects()) {
            if (gameObject instanceof Mob) {
                mobs++;
            }
        }

        if (Math.random() < randomCondition && mobs < maxMobs) {
            PixelQuestGame.game.addGameObject(randomMob(
                    PixelQuestGame.game.player.x - 500, PixelQuestGame.game.player.x + 500,
                    PixelQuestGame.game.player.y - 500, PixelQuestGame.game.player.y + 500));
        }

        checkAndSpawnTrees();
    }

    private static Mob randomMob(int minX, int maxX, int minY, int maxY) {
        int mob = (int) (Math.random() * (DayTime.isDay() ? 2 : 6));
        return switch (mob) {
            case 0 -> new Fox(ThreadLocalRandom.current().nextInt(minX, maxX), ThreadLocalRandom.current().nextInt(minY, maxY), defaultSpeeds[0]);
            case 1 -> new Sheep(ThreadLocalRandom.current().nextInt(minX, maxX), ThreadLocalRandom.current().nextInt(minY, maxY), defaultSpeeds[1]);
            case 2, 3, 4, 5 -> new Monster(ThreadLocalRandom.current().nextInt(minX, maxX), ThreadLocalRandom.current().nextInt(minY, maxY), defaultSpeeds[2]);
            default -> null;
        };
    }

    private static void checkAndSpawnTrees() {
        int treeCount = 2;
        int oakToSpruceRatio = 5;
        int playerX = PixelQuestGame.game.player.x;
        int playerY = PixelQuestGame.game.player.y;

        for (int i = 0; i < treeCount; i++) {
            int x = ThreadLocalRandom.current().nextInt(playerX - 1500, playerX + 1500);
            int y = ThreadLocalRandom.current().nextInt(playerY - 1500, playerY + 1500);

            // Ensure distance between trees and player
            if (isWithinRange(x, y, playerX, playerY, 1500) && isTreeNotNearby(x, y)) {
                if (i % oakToSpruceRatio != 0) {
                    PixelQuestGame.game.addGameObject(new OakTree(x, y));
                } else {
                    PixelQuestGame.game.addGameObject(new SpruceTree(x, y));
                }
            }
        }
    }

    private static boolean isWithinRange(int x, int y, int playerX, int playerY, int range) {
        int dx = x - playerX;
        int dy = y - playerY;
        return (dx * dx + dy * dy) <= (range * range);
    }

    private static boolean isTreeNotNearby(int x, int y) {
        for (GameObject gameObject : PixelQuestGame.game.getGameObjects()) {
            if (gameObject instanceof Tree) {
                int dx = x - gameObject.x;
                int dy = y - gameObject.y;
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance < 800) {
                    return false; // Tree is too close
                }
            }
        }
        return true; // No nearby tree
    }
}
