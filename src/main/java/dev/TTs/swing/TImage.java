package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class TImage extends TPanel {
    private final Image[] images;
    private int currentIndex = 0;
    private Timer timer;
    private final boolean isAnimation;
    private boolean blend;

    public TImage(ImageString imagePath) {
        ImageString[] strings = imagePath.getStrings();
        this.images = new Image[strings.length];
        for (int i = 0; i < strings.length; i++) {
            this.images[i] = strings[i].toImage();
        }
        isAnimation = strings.length > 1;

        if (isAnimation) {
            startImageRotation(imagePath.getDelay(), imagePath.getRepeat());
            blend = imagePath.getBlend();
        }
    }

    private void startImageRotation(int delay, boolean repeat) {
        timer = new Timer(delay, e -> {
            if (repeat || currentIndex < images.length - 1) {
                currentIndex = (currentIndex + 1) % images.length;
            }
            repaint();
        });
        timer.start();
    }

    public void restartAnimation() {
        if (isAnimation) {
            this.currentIndex = 0;
            repaint();
        }
    }

    public void stopAnimation() {
        if (isAnimation && timer != null) {
            timer.stop();
        }
    }

    public void resumeAnimation() {
        if (isAnimation && timer != null) {
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (images.length != 0) {
            Image currentImage = images.length > 1 ? images[currentIndex] : images[0];

            if (currentImage != null) {
                if (blend && isAnimation) {
                    Image nextImage = images[(currentIndex + 1) % images.length];
                    currentImage = blendImages((BufferedImage) currentImage, (BufferedImage) nextImage);
                }
                g.drawImage(currentImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    private BufferedImage blendImages(BufferedImage img1, BufferedImage img2) {
        int width = Math.min(img1.getWidth(), img2.getWidth());
        int height = Math.min(img1.getHeight(), img2.getHeight());
        BufferedImage blendedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                final int blendedRGB = blendRGB(rgb1, rgb2);
                blendedImage.setRGB(x, y, blendedRGB);
            }
        }

        return blendedImage;
    }

    private int blendRGB(int rgb1, int rgb2) {
        int a1 = (rgb1 >> 24) & 0xFF;
        int r1 = (rgb1 >> 16) & 0xFF;
        int g1 = (rgb1 >> 8) & 0xFF;
        int b1 = rgb1 & 0xFF;

        int a2 = (rgb2 >> 24) & 0xFF;
        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = rgb2 & 0xFF;

        int a = (a1 + a2) / 2;
        int r = (r1 + r2) / 2;
        int g = (g1 + g2) / 2;
        int b = (b1 + b2) / 2;

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
