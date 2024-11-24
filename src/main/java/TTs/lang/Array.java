package TTs.lang;

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
}
