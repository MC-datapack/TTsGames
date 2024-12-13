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
