package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.TTsGames.Games.PixelQuest.item.Item;

public class TagType<T> {
    public static final TagType<Item> ITEM = new TagType<>("item",Item.class);

    private final String identifier;
    private final Class<?> clazz;

    public TagType(String identifier, Class<T> clazz) {
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
