package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel implements TContainer {

    public TPanel() {
        super();
    }

    public TPanel(FlowLayout flowLayout) {
        super(flowLayout);
    }

    @Override
    public Component[] add(Component... components) {
        for (Component component : components) {
            this.add(component);
        }
        return components;
    }

    @Override
    public Component[] add(String constrains, Component... components) {
        for (Component component : components) {
            this.add(component, constrains);
        }
        return components;
    }

    @Override
    public Component[][] add(Component[]... components) {
        for (Component[] component : components) {
            this.add(component);
        }
        return components;
    }

    @Override
    public Component[][] add(String constrains, Component[]... components) {
        for (Component[] component : components) {
            this.add(constrains, component);
        }
        return components;
    }

    public void setBorder(int top, int left, int bottom, int right) {
        this.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }

    public void setBorderLayout(int hgap, int vgap) {
        this.setLayout(new BorderLayout(hgap, vgap));
    }

    @Override
    public void setOpaqueF() {
        this.setOpaque(false);
    }

    @Override
    public void setFocusableT() {
        this.setFocusable(true);
    }

    @Override
    public void setFocusableF() {
        this.setFocusable(false);
    }

    @Override
    public void setColor(Region region, Color color) {
        switch (region) {
            case BACKGROUND -> this.setBackground(color);
            case FOREGROUND -> this.setForeground(color);
        }
    }

    @Override
    public void setOpaqueT() {
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
