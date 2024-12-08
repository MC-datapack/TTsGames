package dev.TTs.swing;

import java.awt.*;

public record FrameInformationT(boolean resizable, Point location, Dimension size, String title) {
    public FrameInformation frameInformation() {return new FrameInformation(resizable, location, size);}
}