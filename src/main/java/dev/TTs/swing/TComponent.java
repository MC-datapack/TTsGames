package dev.TTs.swing;

import java.awt.*;

@SuppressWarnings("unused")
public interface TComponent {
    default void Hide() {
        this.visible(false);
    }

    default void Show() {
        this.visible(true);
    }

    default void setPSize(Dimension size) {
        this.pSize(size);
    }

    default void setPSize(int width, int height) {
        this.pSize(new Dimension(width, height));
    }

    default void setOpaqueT() {
        this.opaque(true);
    }

    default void setOpaqueF() {
        this.opaque(false);
    }

    default void setFocusableT() {
        this.focusable(true);
    }

    default void setFocusableF() {
        this.focusable(false);
    }

    default void setColor(Region region, Color color) {
        switch (region) {
            case BACKGROUND -> this.background(color);
            case FOREGROUND -> this.foreground(color);
        }
    }

    default void setPSize(double d1, double d2) {
        this.pSize(new Dimension((int) d1, (int) d2));
    }

    default void setSize(double d1, double d2) {
        this.size(new Dimension((int) d1, (int) d2));
    }

    void background(Color color);
    void foreground(Color color);
    void focusable(boolean bool);
    void opaque(boolean bool);
    void visible(boolean bool);
    void pSize(Dimension dimension);
    void size(Dimension dimension);
}
