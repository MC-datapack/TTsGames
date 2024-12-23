package dev.TTs.TTsGames.Games.PixelQuest.entities;

public interface Damageable {
    void damage(int i);
    default void damage(int i, DamageReason reason) {}
    void heal(int i);
    boolean died();
    default String deathReason() {
        return "";
    }
}
