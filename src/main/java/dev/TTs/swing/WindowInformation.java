package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import java.awt.*;

public record WindowInformation(boolean resizable, Point location, Dimension size, ImageString icon, String title) {
    public WindowInformation(boolean resizable, Point location, Dimension size, ImageString icon) {
        this(resizable, location, size, icon, null);
    }
}