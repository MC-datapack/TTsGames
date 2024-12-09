package dev.TTs.swing;

import dev.TTs.lang.ImageString;

import java.awt.*;

public record FrameInformation(boolean resizable, Point location, Dimension size, ImageString icon) {}