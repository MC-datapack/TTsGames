package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.resources.Json.Text;

public enum DamageReason {
    ENEMY("enemy"),
    STARVATION("starve"),
    UNKNOWN("unknown"),
    PLAYER("player");

    private final String message;

    DamageReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return Text.translatable("pixel_quest.death." + message);
    }
}
