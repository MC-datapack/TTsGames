package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

public class TLabel extends JLabel {

    public TLabel() {
        super();
    }

    public TLabel(String text) {
        super(text);
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
