package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TButton extends JButton implements TComponent {

    public TButton() {
        super();
    }

    public TButton(Icon icon) {
        super(icon);
    }

    public TButton(String s) {
        super(s);
    }

    public TButton(String s, Icon icon) {
        super(s, icon);
    }

    public void addActionListener(Runnable event) {
        this.addActionListener(e -> event.run());
    }

    public void clickAction(Runnable event) {
        this.addActionListener(e -> event.run());
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
