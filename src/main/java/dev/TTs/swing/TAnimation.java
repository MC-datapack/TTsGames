package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class TAnimation extends TPanel {
    private final Image[] images;
    private int currentIndex = 0;
    private Timer timer;

    public TAnimation(ImageString imagePath) {
        ImageString[] strings = imagePath.getStrings();
        this.images = new Image[strings.length];
        for (int i = 0; i < strings.length; i++) {
            this.images[i] = (strings[i].toImage());
        }
        startImageRotation(imagePath.getDelay(), imagePath.getRepeat());
    }

    public void restartAnimation() {
        this.currentIndex = 0;
        repaint();
    }

    public void setAnimationIndex(int index) {
        this.currentIndex = index;
        repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (images.length != 0) {
            Image currentImage = images[currentIndex];
            if (currentImage != null) {
                g.drawImage(currentImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    public void stopAnimation() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void resumeAnimation() {
        if (timer != null) {
            timer.start();
        }
    }
}
