package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TLabel extends JLabel implements TComponent {

    public TLabel() {
        super();
    }

    public TLabel(String text) {
        super(text);
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
