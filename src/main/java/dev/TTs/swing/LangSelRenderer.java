package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class LangSelRenderer extends JLabel implements ListCellRenderer<Object> {
    private final Color SelectedColor, UnselectedColor;

    public LangSelRenderer() {
        setOpaque(true);
        setFont(new Font("Pixel", Font.PLAIN, 14)); // Custom pixel art font
        this.SelectedColor = new Color(100, 100, 250);
        this.UnselectedColor = new Color(200, 200, 200, 150);
    }
    public LangSelRenderer(Color selectedColor, Color unselectedColor) {
        setOpaque(true);
        setFont(new Font("Pixel", Font.PLAIN, 14)); // Custom pixel art font
        this.SelectedColor = selectedColor;
        this.UnselectedColor = unselectedColor;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        setText(value.toString());

        if (isSelected) {
            setBackground(SelectedColor);
            setForeground(Color.WHITE);
        } else {
            setBackground(UnselectedColor);
            setForeground(Color.BLACK);
        }

        return this;
    }
}
