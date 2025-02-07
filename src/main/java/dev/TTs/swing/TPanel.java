package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel implements TContainer {

    public TPanel() {
        super();
    }

    public TPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public void setBorder(int top, int left, int bottom, int right) {
        this.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }

    public void setBorderLayout(int hgap, int vgap) {
        this.setLayout(new BorderLayout(hgap, vgap));
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
