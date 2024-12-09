package dev.TTs.lang;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static java.lang.System.out;

@SuppressWarnings("unused")
public final class TTsLogger {
    private static TTsLogger inst;
    private static String logFilePath;
    private static BufferedWriter bufferedWriter;
    private Instance instance;
    private final boolean debug;

    public void unimportant (Object message, Object... objects) {writeToLog(LogLevel.UNIMPORTANT, message, objects);}
    public void debug       (Object message, Object... objects) {writeToLog(LogLevel.DEBUG, message, objects);}
    public void check       (Object message, Object... objects) {writeToLog(LogLevel.CHECK, message, objects);}
    public void info        (Object message, Object... objects) {writeToLog(LogLevel.INFO, message, objects);}
    public void warn        (Object message, Object... objects) {writeToLog(LogLevel.WARN, message, objects);}
    public void error       (Object message, Object... objects) {writeToLog(LogLevel.ERROR, message, objects);}

    public void unimportant (Object[] message, Object... objects) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message), objects);}
    public void debug       (Object[] message, Object... objects) {writeToLog(LogLevel.DEBUG, Arrays.toString(message), objects);}
    public void check       (Object[] message, Object... objects) {writeToLog(LogLevel.CHECK, Arrays.toString(message), objects);}
    public void info        (Object[] message, Object... objects) {writeToLog(LogLevel.INFO, Arrays.toString(message), objects);}
    public void warn        (Object[] message, Object... objects) {writeToLog(LogLevel.WARN, Arrays.toString(message), objects);}
    public void error       (Object[] message, Object... objects) {writeToLog(LogLevel.ERROR, Arrays.toString(message), objects);}

    public void writeToLog(LogLevel level, Object message, Object... objects) {
        final String formatted = String.format(String.valueOf(message), objects);
        if (!debug) {
            if (level.getImportance() != 'A') {
                String formattedMessage = formatMessage(level, formatted);
                out.println(formattedMessage);
                if (bufferedWriter == null) {
                    error("BufferedWriter not initialized.");
                    return;
                }
                try {
                    bufferedWriter.write(formattedMessage + "\n");
                    bufferedWriter.flush();
                } catch (IOException e) {
                    error("Error writing to writeToLog file: %s", e);
                }
            }
        } else {
            String formattedMessage = formatMessage(level, formatted);
            out.println(formattedMessage);
            if (bufferedWriter == null) {
                error("BufferedWriter not initialized.");
                return;
            }
            try {
                bufferedWriter.write(formattedMessage + "\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                error("Error writing to writeToLog file: %s", e);
            }
        }
    }

    private String formatMessage(LogLevel level, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSSSSSS");
        String resetColor = "\u001B[0m";
        return String.format("%s[%s] <%c> %s<%s> %s%s[%s] %s%s",
                level.getColorCode(), now.format(formatter), level.getImportance(),
                instance.getColorCode(), instance.getName(), resetColor,
                level.getColorCode(), level.getName(), message, resetColor);
    }

    public void close() {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
                File file = new File(logFilePath);
                boolean s = file.setReadOnly();
            }
        } catch (IOException e) {
            error("Error closing BufferedWriter: %s", e);
        }
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance newInstance) {
        instance = newInstance;
    }

    @Override
    public String toString() {
        return "instance = " + instance.getName();
    }

    public TTsLogger(Instance instance, boolean debugInstance) {
        this.instance = instance;
        this.debug = debugInstance;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd_HH-mm-ss");
        String timestamp = time.format(formatter);
        String logDirPath = System.getProperty("user.home") + File.separator + "AppData/Roaming/TTsGames/logs";
        File logDir = new File(logDirPath);
        if (!logDir.exists()) {
            boolean m = logDir.mkdirs();
        }
        logFilePath = logDirPath + File.separator + timestamp + ".log";
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true));
        } catch (IOException e) {
            error("Error initializing BufferedWriter: %s", e);
        }
    }
}
