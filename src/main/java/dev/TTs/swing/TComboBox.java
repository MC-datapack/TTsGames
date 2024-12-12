package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TComboBox<E> extends JComboBox<E> implements TComponent {

    @SafeVarargs
    public TComboBox(E... items) {
        super(items);
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
    public void setOpaqueT() {
        this.setOpaque(true);
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

    public void addActionListener(Runnable event) {
        this.addActionListener(e -> event.run());
    }
}
