package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TLayeredPane extends JLayeredPane implements TContainer {
    public TLayeredPane() {
        super();
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

    @Override
    public void size(Dimension dimension) {
        this.setSize(dimension);
    }

    @Override
    public Component addC(Component component) {
        return this.add(component);
    }

    @Override
    public Component addC(Component component, String constrains) {
        this.add(component, constrains);
        return component;
    }
}
