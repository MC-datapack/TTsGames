package dev.TTs.lang;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;

class ImageCaches {
    private static final ConcurrentHashMap<String, BufferedImage> cache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ImageIcon> iconCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, BufferedImage> tintCache = new ConcurrentHashMap<>();

    public static BufferedImage getImage(String path) {
        return cache.computeIfAbsent(path, ImageString::loadImage);
    }


    public static BufferedImage getTintedImage(String path, Color tintColor, float opacity) {
        String key = path + tintColor.toString() + opacity;
        return tintCache.computeIfAbsent(key, k -> {
            BufferedImage originalImage = getImage(path);
            BufferedImage tintedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2D = tintedImage.createGraphics();
            g2D.drawImage(originalImage, 0, 0, null);
            g2D.dispose();

            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    int argb = originalImage.getRGB(x, y);
                    int alpha = (argb >> 24) & 0xff;
                    if (alpha != 0) {
                        int red = (argb >> 16) & 0xff;
                        int green = (argb >> 8) & 0xff;
                        int blue = argb & 0xff;
                        int tintedRed = (int) ((1 - opacity) * red + opacity * tintColor.getRed());
                        int tintedGreen = (int) ((1 - opacity) * green + opacity * tintColor.getGreen());
                        int tintedBlue = (int) ((1 - opacity) * blue + opacity * tintColor.getBlue());
                        int tintedARGB = (alpha << 24) | (tintedRed << 16) | (tintedGreen << 8) | tintedBlue;
                        tintedImage.setRGB(x, y, tintedARGB);
                    }
                }
            }
            return tintedImage;
        });
    }

    public static ImageIcon getIcon(String path) {
        return iconCache.computeIfAbsent(path, p -> new ImageIcon(ImageCaches.getImage(p)));
    }
}
