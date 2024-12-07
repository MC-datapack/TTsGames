package dev.TTs.lang;

/**
 * <blockquote><pre>
 * Reset: 0m
 * Text Formatting
 *  Bold: 1m
 *  Dim: 2m
 *  Italic: 3m
 *  Underline: 4m
 *  Blink: 5m
 *  Inverse: 7m
 *  Hidden: 8m
 *  Strikethrough: 9m
 *
 * Foreground (Text) Colors
 *  Black: 30m
 *  Red: 31m
 *  Green: 32m
 *  Yellow: 33m
 *  Blue: 34m
 *  Purple: 35m
 *  Cyan: 36m
 *  White: 37m
 *
 * Background Colors
 *  Black: 40m
 *  Red: 41m
 *  Green: 42m
 *  Yellow: 43m
 *  Blue: 44m
 *  Purple: 45m
 *  Cyan: 46m
 *  White: 47m
 *
 * Bright Foreground (Text) Colors
 *  Bright Black: 90m
 *  Bright Red: 91m
 *  Bright Green: 92m
 *  Bright Yellow: 93m
 *  Bright Blue: 94m
 *  Bright Purple: 95m
 *  Bright Cyan: 96m
 *  Bright White: 97m
 *
 * Bright Background Colors
 *  Bright Black: 100m
 *  Bright Red: 101m
 *  Bright Green: 102m
 *  Bright Yellow: 103m
 *  Bright Blue: 104m
 *  Bright Purple: 105m
 *  Bright Cyan: 106m
 *  Bright White: 107m
 * </pre>
 */
public enum LogLevel {
    UNIMPORTANT("Unimportant", "94m", 'A'),
    DEBUG("Debug","36m", 'A'),
    CHECK("Check", "32m", 'B'),
    INFO("Info","30m", 'C'),
    WARN("Warn","33m", 'D'),
    ERROR("Error","31m", 'E');

    private final String colorCode;
    private final char importance;
    private final String name;

    LogLevel(String name, String colorCode, char importance) {
        this.name = name;
        this.importance = importance;
        this.colorCode = "\u001B[" + colorCode;
    }

    public String getColorCode() {return colorCode;}
    public String getName() {return name;}
    public char getImportance() {return importance;}
}