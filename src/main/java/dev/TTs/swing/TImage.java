package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class TImage extends TPanel {
    private final Image[] images;
    private int currentIndex = 0;
    private Timer timer;
    private final boolean isAnimation;

    public TImage(ImageString imagePath) {
        ImageString[] strings = imagePath.getStrings();
        this.images = new Image[strings.length];
        for (int i = 0; i < strings.length; i++) {
            this.images[i] = strings[i].toImage();
        }
        isAnimation = strings.length > 1;

        if (isAnimation) {
            startImageRotation(imagePath.getDelay(), imagePath.getRepeat());
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
        this.setCurrentIndex(0);
    }

    public void setCurrentIndex(int index) {
        if (isAnimation) {
            this.currentIndex = index;
            repaint();
        }
    }

    public void setAnimationIndex(int index) {
        if (isAnimation) {
            this.currentIndex = index;
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
                g.drawImage(currentImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }
}
