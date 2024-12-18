package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import javax.swing.*;
        import java.awt.*;

public record WindowInformation(boolean resizable, Point location, Dimension size, ImageString icon, String title, int defaultCloseOperation) {
    public WindowInformation(boolean resizable, Point location, Dimension size, ImageString icon, int defaultCloseOperation) {
        this(resizable, location, size, icon, null, defaultCloseOperation);
    }

    public WindowInformation(boolean resizable, Point location, Dimension size, ImageString icon, String title) {
        this(resizable, location, size, icon, title, WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public WindowInformation(boolean resizable, Point location, Dimension size, ImageString icon) {
        this(resizable, location, size, icon, null);
    }
}