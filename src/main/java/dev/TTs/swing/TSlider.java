package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TSlider extends JSlider implements TComponent {
    public TSlider(int min, int max) {
        super(min, max);
    }

    public TSlider(int min, int max, int value) {
        super(min, max, value);
    }

    @Override
    public void background(Color color) {
        this.setBackground(color);
    }

    @Override
    public void foreground(Color color) {
        this.setForeground(color);
    }

    @Override
    public void focusable(boolean bool) {
        this.setFocusable(bool);
    }

    @Override
    public void opaque(boolean bool) {
        this.setOpaque(bool);
    }

    @Override
    public void visible(boolean bool) {
        this.setVisible(bool);
    }

    @Override
    public void pSize(Dimension dimension) {
        this.setPreferredSize(dimension);
    }
}
