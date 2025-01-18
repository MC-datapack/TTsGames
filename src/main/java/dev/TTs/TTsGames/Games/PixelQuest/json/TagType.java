package dev.TTs.TTsGames.Games.PixelQuest.json;

import dev.TTs.TTsGames.Games.PixelQuest.item.Item;

public enum TagType {
    ITEM("item", Item.class);

    private final String identifier;
    private final Class<?> clazz;

    TagType(String identifier, Class<?> clazz) {
        this.identifier = identifier;
        this.clazz = clazz;
    }

    public String getIdentifier() {
        return identifier;
    }


    public Class<?> getClazz() {
        return clazz;
    }
}
