package dev.TTs.swing;

import java.awt.*;

public interface TContainer extends TComponent {
    default Component[] add(Component... components) {
        for (Component component : components) {
            this.addC(component);
        }
        return components;
    }
    default Component[][] add(Component[]... components) {
        for (Component[] componentArray : components) {
            for (Component component : componentArray) {
                this.addC(component);
            }
        }
        return components;
    }
    default Component[] add(String constrains, Component... components) {
        for (Component component : components) {
            this.addC(component, constrains);
        }
        return components;
    }
    default Component[][] add(String constrains, Component[]... components) {
        for (Component[] componentArray : components) {
            for (Component component : componentArray) {
                this.addC(component, constrains);
            }
        }
        return components;
    }

    Component addC(Component component);
    Component addC(Component component, String constrains);
}