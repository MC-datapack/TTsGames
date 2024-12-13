package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TTextField extends JTextField implements TComponent {

    public TTextField() {
        super();
    }

    public TTextField(String text) {
        super(text);
    }

    public TTextField(int columns) {
        super(columns);
    }

    public TTextField(String text, int columns) {
        super(text, columns);
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
