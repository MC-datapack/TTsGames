package dev.TTs.swing3d;

import dev.TTs.swing.TComponent;

@SuppressWarnings("unused")
public interface Object3D extends TComponent {
    void startSpinning();
    void stopSpinning();

    void setAngleX(double x);
    void setAngleY(double y);
    void setAngleZ(double z);

    double getAngleX();
    double getAngleY();
    double getAngleZ();

    default void addToAngleX(double x) {
        this.setAngleX(getAngleX() + x);
    }
    default void addToAngleY(double y) {
        this.setAngleX(getAngleY() + y);
    }
    default void addToAngleZ(double z) {
        this.setAngleX(getAngleZ() + z);
    }

    default void removeFromAngleX(double x) {
        this.setAngleX(getAngleX() - x);
    }
    default void removeFromAngleY(double y) {
        this.setAngleX(getAngleY() - y);
    }
    default void removeFromAngleZ(double z) {
        this.setAngleX(getAngleZ() - z);
    }
}
