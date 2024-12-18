package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class TImage extends TPanel implements Animation {
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
            timer = new Timer(imagePath.getDelay(), e -> {
                if (imagePath.getRepeat() || currentIndex < images.length - 1) {
                    currentIndex = (currentIndex + 1) % images.length;
                }
                repaint();
            });
            timer.start();
            blend = imagePath.getBlend();
        }
    }

    @Override
    public void restartAnimation() {
        if (isAnimation) {
            this.currentIndex = 0;
            repaint();
        }
    }

    @Override
    public void stopAnimation() {
        if (isAnimation && timer != null) {
            timer.stop();
        }
    }

    @Override
    public void startAnimation() {
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
}
