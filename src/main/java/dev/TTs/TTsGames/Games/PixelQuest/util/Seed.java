package dev.TTs.TTsGames.Games.PixelQuest.util;

import java.io.Serial;
import java.io.Serializable;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

public interface Seed<T> extends Serializable {
    @Serial
    long serialVersionUID = serialVersion;

    Long getJavaUtilRandomSeed();
    T getActualSeed();

    static Seed<?> getOptimal(String input) {
        try {
            return new LongSeed(Long.parseLong(input));
        } catch (NumberFormatException e) {
            return new StringSeed(input);
        }
    }
}
