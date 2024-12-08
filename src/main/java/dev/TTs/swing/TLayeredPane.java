package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TLayeredPane extends JLayeredPane {
    public TLayeredPane() {
        super();
    }

    public Component[] add(Object constrains, Component... components) {
        for (Component component : components) {
            this.add(component, constrains);
        }
        return components;
    }

    public Component[][] add(Object constrains, Component[]... components) {
        for (Component[] component : components) {
            this.add(constrains, component);
        }
        return components;
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
