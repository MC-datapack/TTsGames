package dev.TTs.swing;

import java.awt.*;

public interface TComponent {
    void Hide();
    void Show();
    void setPSize(Dimension size);
    void setPSize(int width, int height);
    void SetOpaqueT();
    void SetOpaqueF();
}
