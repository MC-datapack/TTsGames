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

    @Override
    public void setOpaqueT() {
        this.setOpaque(true);
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
}
