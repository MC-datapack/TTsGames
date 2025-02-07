package dev.TTs.util;

import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.util.Seed;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagKey;

import java.util.Collection;
import java.util.Random;

import static dev.TTs.TTsGames.Main.logger;

public class RandomUtil {
    private static Random random = new Random();

    public static void setSeed(Seed<?> seed) {
        random = new Random(seed.getJavaUtilRandomSeed());
    }

    public static <T> T getRandom(Collection<T> values) {
        return values.stream().toList().get(random.nextInt(values.size()));
    }

    public static <T> T getRandom(T[] values) {
        return values[random.nextInt(values.length)];
    }

    public static <T> T getRandom(TagKey<T> tag) {
        try {
            return getRandom(PixelQuestJsonReader.readTag(tag));
        } catch (NullPointerException e) {
            logger.error("Failed to load Tag: %s", e.getMessage());
            return null;
        }
    }
}
