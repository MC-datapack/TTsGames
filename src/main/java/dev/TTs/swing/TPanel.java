package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel {

    public TPanel() {
        super();
    }

    public TPanel(FlowLayout flowLayout) {
        super(flowLayout);
    }

    public Component[] add(Component... components) {
        for (Component component : components) {
            this.add(component);
        }
        return components;
    }

    public Component[] add(String constrains, Component... components) {
        for (Component component : components) {
            this.add(component, constrains);
        }
        return components;
    }

    public Component[][] add(Component[]... components) {
        for (Component[] component : components) {
            this.add(component);
        }
        return components;
    }

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
