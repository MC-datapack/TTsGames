package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TButton extends JButton {

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
