package dev.TTs.TTsGames.Games.PixelQuest.json;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class CountProvider {
    private int fixed;
    private Map<String, Integer> uniform;
    private Integer min;
    private Integer max;

    public int getCount() {
        if (uniform != null) {
            Integer min = uniform.get("min");
            Integer max = uniform.get("max");
            if (min == null || max == null || min > max) {
                logger.error("Invalid uniform range: min = %s, max = %s", min, max);
                return 0;
            }
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        } else if (min != null && max != null) {
            if (min > max) {
                logger.error("Invalid min/max range: min = %s, max = %s", min, max);
                return 0;
            }
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        } else {
            return fixed;
        }
    }
}
