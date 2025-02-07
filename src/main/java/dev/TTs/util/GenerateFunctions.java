package dev.TTs.util;

class GenerateFunctions {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(generateInterface(i));
        }
    }

    private static String generateInterface(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("@FunctionalInterface\n");
        sb.append("public interface Functions_").append(n).append("<");
        for (int i = 0; i < n; i++) {
            sb.append(getColumnName(i)).append(", ");
        }
        sb.append("T_Type> {\n");
        sb.append("    T_Type apply(");
        for (int i = 0; i < n; i++) {
            String columnName = getColumnName(i);
            char firstChar = columnName.charAt(0);
            sb.append(columnName).append(" ").append(columnName.toLowerCase()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(");\n");
        sb.append("}\n");
        return sb.toString();
    }

    private static String getColumnName(int index) {
        index++;
        StringBuilder sb = new StringBuilder();
        while (index > 0) {
            index--;
            sb.insert(0, (char) ('A' + (index % 26)));
            index /= 26;
        }
        return sb.toString();
    }
}
