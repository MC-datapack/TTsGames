package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TComboBox<E> extends JComboBox<E> implements TComponent {

    @SafeVarargs
    public TComboBox(E... items) {
        super(items);
    }

    public void setOpaqueF() {
        this.setOpaque(false);
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

    public void addActionListener(Runnable event) {
        this.addActionListener(e -> event.run());
    }
}
