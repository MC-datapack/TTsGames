package dev.TTs.TTsGames.Games.PixelQuest.util;

public class StringSeed implements Seed<String> {
    private final String seed;

    public StringSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public Long getJavaUtilRandomSeed() {
       char[] chars = seed.toCharArray();
       long output = 0;
        for (int i = 0; i < chars.length; i++) {
            long chaR;
            try {
                chaR = Long.parseLong(String.valueOf(chars[i]));
            } catch (NumberFormatException e) {
                chaR = (int) chars[i];
            }

            output += (10L * i) + chaR;
        }
        return output;
    }

    @Override
    public String getActualSeed() {
        return seed;
    }
}
