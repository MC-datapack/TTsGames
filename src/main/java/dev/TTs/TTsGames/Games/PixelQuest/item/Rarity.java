package dev.TTs.TTsGames.Games.PixelQuest.item;

import java.awt.*;

public enum Rarity {
    COMMON(Color.WHITE),
    UNCOMMON(Color.YELLOW),
    RARE(Color.CYAN),
    EPIC(Color.MAGENTA);

    private final Color color;

    Rarity(Color color) {
        this.color = color;
    }


    public Color getColor() {
        return color;
    }
}
