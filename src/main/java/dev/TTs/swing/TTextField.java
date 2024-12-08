package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TTextField extends JTextField {

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

    public void Hide() {
        this.setVisible(false);
    }

    public void Show() {
        this.setVisible(true);
    }

    public void setPSize(Dimension size) {
        this.setPreferredSize(size);
    }
}
