package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TSlider extends JSlider {
    public TSlider(int min, int max) {
        super(min, max);
    }

    public TSlider(int min, int max, int value) {
        super(min, max, value);
    }

    public void setOpaqueF() {
        this.setOpaque(false);
    }

    public void Hide() {
        this.setVisible(false);
    }

    public void Show() {
        this.setVisible(true);
    }

    public void setPSize(Dimension size) {
        this.setPreferredSize(size);
    }
}
