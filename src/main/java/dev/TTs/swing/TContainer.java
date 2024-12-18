package dev.TTs.swing;

import java.awt.*;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface TContainer extends TComponent {
    default Component addSouth(Component component) {
        return this.addC(component, "South");
    }
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
    default Component[] addSouth(Component... components) {
        return this.add("South", components);
    }
    default Component[][] add(String constrains, Component[]... components) {
        for (Component[] componentArray : components) {
            for (Component component : componentArray) {
                this.addC(component, constrains);
            }
        }
        return components;
    }
    default Component[][] addSouth(Component[]... components) {
        return this.add("South", components);
    }

    Component addC(Component component);
    Component addC(Component component, String constrains);
}