package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TFrame extends JFrame {

    public TFrame() {
        super();
    }

    public TFrame(String title) {
        super(title);
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

    public void Hide() {
        this.setVisible(false);
    }

    public void Show() {
        this.setVisible(true);
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
