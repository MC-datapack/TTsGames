package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TFrame extends JFrame implements TContainer {

    public TFrame() {
        super();
    }

    public TFrame(String title) {
        super(title);
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
    public void opaque(boolean bool) {}

    @Override
    public void visible(boolean bool) {
        this.setVisible(bool);
    }

    @Override
    public void pSize(Dimension dimension) {
        this.setPreferredSize(dimension);
    }

    public void closingOperation(Runnable task) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                task.run();
            }
        });
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
