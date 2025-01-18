package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.TTsGames.Games.PixelQuest.json.TagType;

public record TagKey(TagType type, Identifier id) {
    public static TagKey of(TagType type, Identifier id) {
        return new TagKey(type, id);
    }

    public String path() {
        return id().getTag(type);
    }
}
