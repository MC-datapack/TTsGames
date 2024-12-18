package dev.TTs.lang;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Logging {
    void writeMessage(LogLevel level, Object message, Object... args);
    void close();

    Instance getInstance();
    void setInstance(Instance instance);

    default String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSSSSSS"));
    }

    default String formatMessage(LogLevel level, String message, Object... args) {
        return String.format("%s[%s] <%c> %s<%s> %s%s[%s] %s%s",
                level.getColorCode(), getTime(), level.getImportance(),
                getInstance().getColorCode(), getInstance().getName(), "\u001B[0m",
                level.getColorCode(), level.getName(), String.format(message, args), "\u001B[0m");
    }

    default void unimportant (Object message, Object... objects) {writeMessage(LogLevel.UNIMPORTANT, message, objects);}
    default void debug (Object message, Object... objects) {writeMessage(LogLevel.DEBUG, message, objects);}
    default void check (Object message, Object... objects) {writeMessage(LogLevel.CHECK, message, objects);}
    default void info (Object message, Object... objects) {writeMessage(LogLevel.INFO, message, objects);}
    default void warn (Object message, Object... objects) {writeMessage(LogLevel.WARN, message, objects);}
    default void error (Object message, Object... objects) {writeMessage(LogLevel.ERROR, message, objects);}
}
