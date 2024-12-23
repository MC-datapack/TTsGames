package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public record BasicItem(Identifier identifier, Item.Settings settings) implements Item {
    @Override
    public BasicItem toItem() {
        return this;
    }
}
