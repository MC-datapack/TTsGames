package dev.TTs.TTsGames.Games.PixelQuest.entity;

public interface Damageable {
    void damage(int i);
    default void damage(int i, DamageReason reason) {
        damage(i);
    }
    void heal(int i);
    boolean died();
    String deathMessage();
    DamageReason deathReason();
}
