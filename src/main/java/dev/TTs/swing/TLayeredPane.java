package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TLayeredPane extends JLayeredPane implements TContainer {
    public TLayeredPane() {
        super();
    }

    @Override
    public Component[] add(Component... components) {
        for (Component component : components) {
            this.add(component);
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
    public Component[] add(String constrains, Component... components) {
        for (Component component : components) {
            this.add(component, constrains);
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

    @Override
    public void SetOpaqueT() {
        this.setOpaque(true);
    }

    @Override
    public void SetOpaqueF() {
        this.setOpaque(false);
    }
}
