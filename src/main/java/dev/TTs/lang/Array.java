package dev.TTs.lang;

import java.util.Arrays;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings("unused")
public class Array {
    public static boolean Contains(String[] array, String target) {
        for (String s : array) {
            if (s.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean Contains(T[] array, T target) {
        for (T s : array) {
            if (s.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static boolean stringContainsAnyOf(String string, String[] array) {
        if (string != null) {
            for (String s : array) {
                if (string.contains(s)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public static int getIndexOf(String string, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(string)) {
                return i;
            }
        }
        logger.error("Did not find string: %s in array: %s", string, Arrays.toString(array));
        return 0;
    }

    public static boolean dontContains(String[] array, String target) {
        for (String s : array) {
            if (s.equals(target)) {
                return false;
            }
        }
        return true;
    }

    public static boolean dontContains(int[] array, int target) {
        for (int s : array) {
            if (s == target) {
                return false;
            }
        }
        return true;
    }

    public static boolean ContainsIgnoreCase(String[] array, String target) {
        for (String s : array) {
            if (s.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public static boolean Contains(char[] array, char target) {
        for (char c : array) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean dontContains(char[] array, char target) {
        for (char c : array) {
            if (c == target) {
                return true;
            }
        }
        return false;
    }
}
