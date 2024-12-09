package dev.TTs.lang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TTsLogger {
    private final String logFilePath;
    private BufferedWriter bufferedWriter;
    private Instance instance = Instance.TTS_GAMES;
    private final boolean debug;
    private final String resetColor = "\u001B[0m";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public TTsLogger(String logDirPath, boolean debug) {
        this.debug = debug;
        File logDir = new File(logDirPath);
        logDir.mkdirs();
        this.logFilePath = logDirPath + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd_HH-mm-ss")) + ".log";
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true));
        } catch (IOException e) {
            System.err.println("Error Initializing TTsLogger");
            System.exit(ExitCodes.LOGGER_ERROR);
        }
    }

    public void unimportant (Object message, Object... objects) {writeLogMessage(LogLevel.UNIMPORTANT, message, objects);}
    public void debug (Object message, Object... objects) {writeLogMessage(LogLevel.DEBUG, message, objects);}
    public void check (Object message, Object... objects) {writeLogMessage(LogLevel.CHECK, message, objects);}
    public void info (Object message, Object... objects) {writeLogMessage(LogLevel.INFO, message, objects);}
    public void warn (Object message, Object... objects) {writeLogMessage(LogLevel.WARN, message, objects);}
    public void error (Object message, Object... objects) {writeLogMessage(LogLevel.ERROR, message, objects);}

    private void writeLogMessage(LogLevel level, Object message, Object... args) {
        if (!debug && level.getImportance() == 'A') {
            return;
        }
        final String formattedMessage = formatMessage(level, String.format(String.valueOf(message), args));
        System.out.println(formattedMessage);
        if (bufferedWriter == null) {
            System.err.println("BufferedWriter not initialized.");
            return;
        }
        try {
            bufferedWriter.write(formattedMessage + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            error("Error using Buffered Writer \"%s\" to write the message \"%s\": ", bufferedWriter, formattedMessage, e);
        }
    }

    private String formatMessage(LogLevel level, String message) {
        return String.format("%s[%s] <%c> %s<%s> %s%s[%s] %s%s",
                level.getColorCode(), getTime(), level.getImportance(),
                instance.getColorCode(), instance.getName(), resetColor,
                level.getColorCode(), level.getName(), message, resetColor);
    }

    private String getTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSSSSSS"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void close() {
        debug("Closing logger");
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
                File file = new File(logFilePath);
                file.setReadOnly();
            }
        } catch (IOException e) {
            error("Error closing BufferedWriter: %s", e);
        }
    }

    public void setInstance(Instance newInstance) {
        instance = newInstance;
    }

    @Override
    public String toString() {
        return "instance = " + instance.getName() + " Buffered Writer = " + bufferedWriter.toString();
    }
}
