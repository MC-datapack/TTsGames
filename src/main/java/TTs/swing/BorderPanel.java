package TTs.swing;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class BorderPanel extends JPanel {
    JLabel textL;

    public BorderPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        setOpaque(false);
    }
    public BorderPanel(Color color) {
        setBorder(BorderFactory.createLineBorder(color, 2, false));
        setOpaque(false);
    }
    public BorderPanel(Color color, int thickness) {
        setBorder(BorderFactory.createLineBorder(color, thickness, false));
        setOpaque(false);
    }
    public BorderPanel(Color color, int thickness, boolean rounded) {
        setBorder(BorderFactory.createLineBorder(color, thickness, rounded));
        setOpaque(false);
    }
    public BorderPanel(int thickness) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, thickness, false));
        setOpaque(false);
    }
    public BorderPanel(int thickness, boolean rounded) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, thickness, rounded));
        setOpaque(false);
    }
    public BorderPanel(boolean rounded) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, rounded));
        setOpaque(false);
    }
    public BorderPanel(String text, Color textColor) {
        textL = new JLabel(text);
        textL.setForeground(textColor);
        this.add(textL);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        setOpaque(false);
    }
    public BorderPanel(String text, Color textColor, boolean Opaque) {
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

    public void setText(String text) {
        textL.setText(text);
        textL.revalidate();
        this.revalidate();
        textL.repaint();
        this.repaint();
    }

    public JLabel getLabel() {
        return textL;
    }
}
