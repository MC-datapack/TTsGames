package dev.TTs.TTsGames.Games.PixelQuest.item;

import java.awt.*;

public enum Rarity {
    COMMON(0, Color.WHITE),
    UNCOMMON(1, Color.YELLOW),
    RARE(2, Color.CYAN),
    EPIC(3, Color.MAGENTA);

    private final int index;
    private final Color color;

    Rarity(int index, Color color) {
        this.index = index;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getIndex() {
        return index;
    }
}
