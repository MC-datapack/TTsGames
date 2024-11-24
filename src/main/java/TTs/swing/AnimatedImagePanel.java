package TTs.swing;

import TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class AnimatedImagePanel extends JPanel {
    private final List<Image> images;
    private int currentIndex = 0;
    private Timer timer;
    public AnimatedImagePanel(ImageString imagePaths) {
        this.images = new ArrayList<>();
        List<ImageString> strings = imagePaths.getStrings();
        for (ImageString string : strings) {
            this.images.add(string.toImage());
        }
        startImageRotation(imagePaths.getDelay(), imagePaths.getRepeat());
    }

    public void restartAnimation() {
        currentIndex = 0;
    }

    private void startImageRotation(int delay, boolean repeat) {
        timer = new Timer(delay, e -> {
            if (repeat) {
                currentIndex = (currentIndex + 1) % images.size();
                repaint();
            } else {
                if (currentIndex < images.size() - 1) {
                    currentIndex++;
                    repaint();
                } else {
                    repaint();
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (images != null && !images.isEmpty()) {
            Image currentImage = images.get(currentIndex);
            if (currentImage != null) {
                g.drawImage(currentImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    public void stopImageRotation() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void resumeImageRotation() {
        if (timer != null) {
            timer.start();
        }
    }
}
