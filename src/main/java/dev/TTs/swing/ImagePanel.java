package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private final Image backgroundImage;

    public ImagePanel(ImageString imagePath) {
        this.backgroundImage = imagePath.toImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null) {
            g.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
