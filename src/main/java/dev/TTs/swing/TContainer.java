package dev.TTs.swing;

import java.awt.*;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface TContainer extends TComponent {
    default Component addSouthP(Component component) {
        return this.addC(component, "South");
    }
    default Component[] addP(Component... components) {
        for (Component component : components) {
            this.addC(component);
        }
        return components;
    }
    default Component[][] addP(Component[]... components) {
        for (Component[] componentArray : components) {
            for (Component component : componentArray) {
                this.addC(component);
            }
        }
        return components;
    }
    default Component[] addP(String constrains, Component... components) {
        for (Component component : components) {
            this.addC(component, constrains);
        }
        return components;
    }
    default Component[] addSouthP(Component... components) {
        return this.addP("South", components);
    }
    default Component[][] addP(String constrains, Component[]... components) {
        for (Component[] componentArray : components) {
            for (Component component : componentArray) {
                this.addC(component, constrains);
            }
        }
        return components;
    }
    default Component[][] addSouthP(Component[]... components) {
        return this.addP("South", components);
    }

    Component addC(Component component);
    Component addC(Component component, String constrains);
}