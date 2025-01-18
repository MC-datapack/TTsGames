package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entity.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.entity.mob.Mob;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

public class Renderer {
    public static int CameraX = 0, CameraY = 0;
    private static BufferedImage starryBackground;
    private static final Random rand = new Random();

    private static BufferedImage createStarryBackground() {
        BufferedImage bg = new BufferedImage(PixelQuestGame.width, PixelQuestGame.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bg.createGraphics();

        createStars(g, PixelQuestGame.width, PixelQuestGame.height, 4, 150,
                new Color(255, 255, 255), new Color(240, 240, 240), new Color(230, 230, 255));
        createStars(g, PixelQuestGame.width, PixelQuestGame.height, 5, 25,
                new Color(0, 0, 255), new Color(80, 80 ,255), new Color(45, 45, 255), new Color(65, 65, 255), new Color(80, 80, 255));
        createStars(g, PixelQuestGame.width, PixelQuestGame.height, 5, 10,
                new Color(255, 255, 0), new Color(255, 210, 0), new Color(255, 180, 0), new Color(255, 120, 0));

        g.dispose();
        return bg;
    }

    private static void createStars(Graphics2D g, int width, int height, int maxStarSize, int starCount, Color... colors) {
        for (int i = 0; i < starCount; i++) {
            int x = Renderer.rand.nextInt(width);
            int y = Renderer.rand.nextInt(height);
            int size = Renderer.rand.nextInt(maxStarSize) + 1;

            float[] fractions = new float[colors.length];
            for (int j = 0; j < colors.length; j++) {
                fractions[j] = Renderer.rand.nextFloat();
            }

            Arrays.sort(fractions);
            LinearGradientPaint gradient = new LinearGradientPaint(x, y, x + size, y + size, fractions, colors);
            g.setPaint(gradient);

            g.fillOval(x, y, size, size);
        }
    }

    public static void paint(Graphics2D g) {
        if (starryBackground == null) {
            starryBackground = createStarryBackground();
        }

        int bgWidth = starryBackground.getWidth();
        int bgHeight = starryBackground.getHeight();

        int startX = -(CameraX % bgWidth);
        int startY = -(CameraY % bgHeight);
        if (CameraX < 0) {
            startX -= bgWidth;
        }
        if (CameraY < 0) {
            startY -= bgHeight;
        }

        g.setColor(DayTime.dayColor());
        g.fillRect(0, 0, PixelQuestGame.width, PixelQuestGame.height);

        for (int x = startX; x < PixelQuestGame.width; x += bgWidth) {
            for (int y = startY; y < PixelQuestGame.height; y += bgHeight) {
                g.drawImage(starryBackground, x, y, null);
            }
        }

        if (!PixelQuestGame.game.player.died()) {
            for (GameObject obj : PixelQuestGame.game.getGameObjects()) {
                int objX = (obj.x - CameraX) + 400;
                int objY = (obj.y - CameraY) + 400;
                obj.render(g, objX, objY);
            }
            if (PixelQuestGame.getFurnace() != null) PixelQuestGame.getFurnace().render(g, 0, 0);
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString(PixelQuestGame.game.player.deathMessage(), 100, 200);
        }

        // Draw FPS and Mob Counter
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("FPS: " + PixelQuestGame.game.fps, 10, 20);
        int mobs = 0;
        for (GameObject gameObject : PixelQuestGame.game.getGameObjects()) if (gameObject instanceof Mob) mobs++;
        g.drawString("Mobs: " + mobs, 10, 35);
    }
}