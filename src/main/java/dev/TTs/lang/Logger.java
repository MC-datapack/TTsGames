package dev.TTs.lang;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Logger {
    <T> T writeMessage(LogLevel level, T message, Object... args);
    void close();
    void newLine();

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

    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T unimportant(@FormatString T message, Object... objects) {return writeMessage(LogLevel.UNIMPORTANT, message, objects);}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T debug (@FormatString T message, Object... objects) {return writeMessage(LogLevel.DEBUG, message, objects);}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T check (@FormatString T message, Object... objects) {return writeMessage(LogLevel.CHECK, message, objects);}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T info (@FormatString T message, Object... objects) {return writeMessage(LogLevel.INFO, message, objects);}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T warn (@FormatString T message, Object... objects) {return writeMessage(LogLevel.WARN, message, objects);}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T error (@FormatString T message, Object... objects) {return writeMessage(LogLevel.ERROR, message, objects);}


    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T unimportant (@FormatString T message, Throwable object) {return writeMessage(LogLevel.UNIMPORTANT, message, object.getMessage());}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T debug (@FormatString T message, Throwable object) {return writeMessage(LogLevel.DEBUG, message, object.getMessage());}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T check (@FormatString T message, Throwable object) {return writeMessage(LogLevel.CHECK, message, object.getMessage());}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T info (@FormatString T message, Throwable object) {return writeMessage(LogLevel.INFO, message, object.getMessage());}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T warn (@FormatString T message, Throwable object) {return writeMessage(LogLevel.WARN, message, object.getMessage());}
    @FormatMethod
    @CanIgnoreReturnValue
    default <T> T error (@FormatString T message, Throwable object) {return writeMessage(LogLevel.ERROR, message, object.getMessage());}
}
