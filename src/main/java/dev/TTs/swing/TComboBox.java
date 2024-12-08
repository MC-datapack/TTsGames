package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TComboBox<E> extends JComboBox<E> {

    public TComboBox(E... items) {
        super(items);
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

    public void addActionListener(Runnable event) {
        this.addActionListener(e -> event.run());
    }
}
