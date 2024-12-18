package dev.TTs.swing;

@SuppressWarnings("unused")
public interface Animation extends Blending {
    void startAnimation();
    void stopAnimation();
    void restartAnimation();

    default void restartCompletely() {
        this.stopAnimation();
        this.startAnimation();
    }
}
