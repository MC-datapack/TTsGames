package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class TBorderPanel extends TPanel {
    JLabel textL;

    public TBorderPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        setOpaque(false);
    }
    public TBorderPanel(Color color) {
        setBorder(BorderFactory.createLineBorder(color, 2, false));
        setOpaque(false);
    }
    public TBorderPanel(Color color, int thickness) {
        setBorder(BorderFactory.createLineBorder(color, thickness, false));
        setOpaque(false);
    }
    public TBorderPanel(Color color, int thickness, boolean rounded) {
        setBorder(BorderFactory.createLineBorder(color, thickness, rounded));
        setOpaque(false);
    }
    public TBorderPanel(int thickness) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, thickness, false));
        setOpaque(false);
    }
    public TBorderPanel(int thickness, boolean rounded) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, thickness, rounded));
        setOpaque(false);
    }
    public TBorderPanel(boolean Opaque) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        setOpaque(Opaque);
    }
    public TBorderPanel(String text, Color textColor) {
        textL = new JLabel(text);
        textL.setForeground(textColor);
        this.add(textL);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        setOpaque(false);
    }
    public TBorderPanel(String text, Color textColor, boolean Opaque) {
        textL = new JLabel(text);
        textL.setForeground(textColor);
        this.add(textL);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        setOpaque(Opaque);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public JLabel getLabel() {
        return textL;
    }
}
