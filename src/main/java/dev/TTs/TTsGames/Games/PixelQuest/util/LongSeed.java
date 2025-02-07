package dev.TTs.TTsGames.Games.PixelQuest.util;

public class LongSeed implements Seed<Long> {
    private final Long seed;

    public LongSeed(Long seed) {
        this.seed = seed;
    }

    @Override
    public Long getJavaUtilRandomSeed() {
        return seed;
    }

    @Override
    public Long getActualSeed() {
        return seed;
    }
}
