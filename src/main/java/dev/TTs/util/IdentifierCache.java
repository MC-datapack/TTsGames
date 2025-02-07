package dev.TTs.util;

import dev.TTs.lang.ImageString;

import java.util.concurrent.ConcurrentHashMap;

class IdentifierCache {
    private static final ConcurrentHashMap<String, ImageString> cache = new ConcurrentHashMap<>();

    public static ImageString getImage(String path) {
        return cache.computeIfAbsent(path, ImageString::new);
    }
}
