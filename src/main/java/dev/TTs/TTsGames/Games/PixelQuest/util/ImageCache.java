package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.lang.ImageString;

import java.util.concurrent.ConcurrentHashMap;

class ImageCache {
    private static final ConcurrentHashMap<String, ImageString> cache = new ConcurrentHashMap<>();

    public static ImageString getImage(String path) {
        return cache.computeIfAbsent(path, ImageString::new);
    }
}
