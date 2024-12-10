package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import java.awt.*;

public record FrameInformationT(boolean resizable, Point location, Dimension size, String title, ImageString icon) {
    public FrameInformation frameInformation() {
        return new FrameInformation(resizable, location, size, icon);
    }
}