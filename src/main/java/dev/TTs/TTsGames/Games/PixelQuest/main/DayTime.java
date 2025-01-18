package dev.TTs.TTsGames.Games.PixelQuest.main;

import java.awt.*;

public class DayTime {
    private static short dayTime = Short.MAX_VALUE / 2;

    public static Color dayColor() {
        return new Color(12, 4, 48, Math.max(150, calculateDarkness()));
    }

    private static int calculateDarkness() {
        int dayTimeNormalized = (int) ((dayTime - Short.MIN_VALUE) / (double) (Short.MAX_VALUE - Short.MIN_VALUE) * Short.MAX_VALUE);
        return Math.min(255, (int) (255 * (1 - Math.abs(((2 * dayTimeNormalized) / (double) Short.MAX_VALUE) - 1))));
    }

    public static void update() {
        dayTime += 10;
        if (dayTime == Short.MAX_VALUE) {
            dayTime = Short.MIN_VALUE;
        }
    }

    public static boolean isDay() {
        return calculateDarkness() > 100;
    }

    public static boolean isNight() {
        return calculateDarkness() > 100;
    }
}
