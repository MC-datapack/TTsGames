package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TLabel extends JLabel implements TComponent {

    public TLabel() {
        super();
    }

    public TLabel(String text) {
        super(text);
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
}
