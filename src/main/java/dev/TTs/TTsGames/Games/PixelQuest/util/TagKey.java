package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.lang.Array;
import dev.TTs.util.Identifier;

public record TagKey<T>(TagType<T> type, Identifier id) {
    public static <T> TagKey<T> of(TagType<T> type, Identifier id) {
        return new TagKey<>(type, id);
    }

    public boolean contains(T object) {
        return Array.Contains(PixelQuestJsonReader.readTag(this), object);
    }

    public String path() {
        return id().getTag(type);
    }
}
