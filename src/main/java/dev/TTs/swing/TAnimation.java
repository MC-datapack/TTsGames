package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressWarnings("unused")
public class TAnimation extends TPanel {
    private final List<Image> images;
    private int currentIndex = 0;
    private Timer timer;

    public TAnimation(ImageString imagePath) {
        this.images = new CopyOnWriteArrayList<>();
        List<ImageString> strings = imagePath.getStrings();
        for (ImageString string : strings) {
            this.images.add(string.toImage());
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
            if (repeat || currentIndex < images.size() - 1) {
                currentIndex = (currentIndex + 1) % images.size();
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!images.isEmpty()) {
            Image currentImage = images.get(currentIndex);
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
