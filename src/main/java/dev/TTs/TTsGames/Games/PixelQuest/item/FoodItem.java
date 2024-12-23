package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public record FoodItem(Identifier identifier, int saturation, Item.Settings settings) implements Item {
    @Override
    public BasicItem toItem() {
        return new BasicItem(identifier, settings);
    }
}
