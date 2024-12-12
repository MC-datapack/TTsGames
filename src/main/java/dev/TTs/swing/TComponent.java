package dev.TTs.swing;

import java.awt.*;

public interface TComponent {
    void Hide();
    void Show();
    void setPSize(Dimension size);
    void setPSize(int width, int height);
    void setOpaqueT();
    void setOpaqueF();
    void setFocusableT();
    void setFocusableF();
    void setColor(Region region, Color color);
}
