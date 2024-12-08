package dev.TTs.math;

public class Time {

    public static float milliTime() {
        return nanoToMilli(System.nanoTime());
    }

    public static float nanoToMilli(long nanos) {
        return nanos / 1000000000F;
    }

    public static float nanoToMilli(float nanos) {
        return nanos / 1000000000;
    }
}
