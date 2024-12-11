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
}
