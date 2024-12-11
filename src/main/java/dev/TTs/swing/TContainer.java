package dev.TTs.swing;

import java.awt.*;

public interface TContainer extends TComponent {
    Component[] add(Component... components);
    Component[][] add(Component[]... components);
    Component[] add(String constrains, Component... components);
    Component[][] add(String constrains, Component[]... components);
}