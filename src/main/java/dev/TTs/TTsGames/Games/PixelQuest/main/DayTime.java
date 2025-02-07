package dev.TTs.TTsGames.Games.PixelQuest.main;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

public class DayTime implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;

    private short dayTime;

    public DayTime() {
        dayTime = Short.MAX_VALUE / 2;
    }

    public Color dayColor() {
        return new Color(12, 4, 48, Math.max(150, calculateDarkness()));
    }

    public int calculateDarkness() {
        int dayTimeNormalized = (int) ((dayTime - Short.MIN_VALUE) / (double) (Short.MAX_VALUE - Short.MIN_VALUE) * Short.MAX_VALUE);
        return Math.min(255, (int) (255 * (1 - Math.abs(((2 * dayTimeNormalized) / (double) Short.MAX_VALUE) - 1))));
    }

    public void update() {
        dayTime++;
        if (dayTime == Short.MAX_VALUE) {
            dayTime = Short.MIN_VALUE;
        }
    }

    public boolean isDay() {
        return calculateDarkness() > 80;
    }

    public boolean isNight() {
        return calculateDarkness() > 80;
    }
}
