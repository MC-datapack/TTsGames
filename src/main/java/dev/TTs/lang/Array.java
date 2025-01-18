package dev.TTs.lang;

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

    public static boolean stringContainsAnyOf(String string, String[] array) {
        for (String s : array) {
            if (string.contains(s)) {
                return true;
            }
        }
        return false;
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
