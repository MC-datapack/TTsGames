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
    public void SetOpaqueF() {
        this.setOpaque(false);
    }

    @Override
    public void SetOpaqueT() {
        this.setOpaque(true);
    }

    @Override
    public void Hide() {
        this.setVisible(false);
    }

    @Override
    public void Show() {
        this.setVisible(true);
    }

    @Override
    public void setPSize(Dimension size) {
        this.setPreferredSize(size);
    }

    @Override
    public void setPSize(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }
}
