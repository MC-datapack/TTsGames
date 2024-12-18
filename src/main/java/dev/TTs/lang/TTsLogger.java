package dev.TTs.lang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings({"ResultOfMethodCallIgnored", "FieldCanBeLocal"})
public final class TTsLogger implements Logging {
    private final String logFilePath;
    private BufferedWriter bufferedWriter;
    private Instance instance = Instance.TTS_GAMES;
    private final boolean debug;

    public TTsLogger(String logDirPath, boolean debug) {
        this.debug = debug;
        File logDir = new File(logDirPath);
        logDir.mkdirs();
        this.logFilePath = logDirPath + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".log";
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFilePath, true));
        } catch (IOException e) {
            System.err.println("Error Initializing TTsLogger");
            System.exit(ExitCodes.LOGGER_ERROR);
        }
    }

    @Override
    public void writeMessage(LogLevel level, Object message, Object... args) {
        if (!debug && level.getImportance() == 'A') {
            return;
        }
        final String formattedMessage = formatMessage(level, String.valueOf(message), args);
        System.out.println(formattedMessage);
        if (bufferedWriter == null) {
            System.err.println(formatMessage(LogLevel.ERROR, "BufferedWriter not initialized."));
            return;
        }
        try {
            bufferedWriter.write(formattedMessage + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(formatMessage(LogLevel.ERROR, "Error using Buffered Writer \"%s\" to write the message \"%s\": %s", bufferedWriter, formattedMessage, e));
        }
    }

    @Override
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

    @Override
    public Instance getInstance() {
        return instance;
    }

    @Override
    public void setInstance(Instance newInstance) {
        instance = newInstance;
    }

    @Override
    public String toString() {
        return "instance = " + instance.getName() + " Buffered Writer = " + bufferedWriter.toString();
    }
}
