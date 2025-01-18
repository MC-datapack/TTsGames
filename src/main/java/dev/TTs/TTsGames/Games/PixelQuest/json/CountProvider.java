package dev.TTs.TTsGames.Games.PixelQuest.json;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class CountProvider {
    private Integer fixed;
    private Map<String, Integer> uniform;

    private CountProvider(Integer fixed) {
        this.fixed = fixed;
    }

    private CountProvider(Integer min, Integer max) {
        this.uniform = new HashMap<>();
        this.uniform.put("min", min);
        this.uniform.put("max", max);
    }

    public static CountProvider fixed(int fixed) {
        return new CountProvider(fixed);
    }

    public static CountProvider uniform(int min, int max) {
        return new CountProvider(min, max);
    }

    public int getCount() {
        if (uniform != null) {
            Integer min = uniform.get("min");
            Integer max = uniform.get("max");
            if (min == null || max == null || min > max) {
                logger.error("Invalid uniform range: min = %s, max = %s", min, max);
                return 0;
            }
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        } else {
            return fixed;
        }
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        if (fixed != null) {
            json.put("fixed", fixed);
        }
        if (uniform != null) {
            json.put("uniform", uniform);
        }
        return json;
    }
}
