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

    public void unimportant (String message) {writeToLog(LogLevel.UNIMPORTANT, message);}
    public void debug       (String message) {writeToLog(LogLevel.DEBUG, message);}
    public void check       (String message) {writeToLog(LogLevel.CHECK, message);}
    public void info        (String message) {writeToLog(LogLevel.INFO, message);}
    public void warn        (String message) {writeToLog(LogLevel.WARN, message);}
    public void error       (@NotNull ImageString message) {writeToLog(LogLevel.ERROR, message + Arrays.toString(message.getStrings().toArray()));}
    public void unimportant (@NotNull ImageString message) {writeToLog(LogLevel.UNIMPORTANT, message + Arrays.toString(message.getStrings().toArray()));}
    public void debug       (@NotNull ImageString message) {writeToLog(LogLevel.DEBUG, message + Arrays.toString(message.getStrings().toArray()));}
    public void check       (@NotNull ImageString message) {writeToLog(LogLevel.CHECK, message + Arrays.toString(message.getStrings().toArray()));}
    public void info        (@NotNull ImageString message) {writeToLog(LogLevel.INFO, message + Arrays.toString(message.getStrings().toArray()));}
    public void warn        (@NotNull ImageString message) {writeToLog(LogLevel.WARN, message + Arrays.toString(message.getStrings().toArray()));}
    public void unimportant (char message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (char message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (char message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (char message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (char message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (char message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (int message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (int message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (int message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (int message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (int message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (int message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (long message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (long message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (long message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (long message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (long message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (long message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (BigInteger message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (BigInteger message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (BigInteger message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (BigInteger message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (BigInteger message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (BigInteger message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (short message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (short message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (short message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (short message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (short message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (short message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (double message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (double message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (double message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (double message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (double message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (double message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (float message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (float message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (float message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (float message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (float message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (float message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (byte message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (byte message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (byte message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (byte message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (byte message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (byte message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (boolean message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (boolean message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (boolean message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (boolean message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (boolean message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (boolean message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (Object message) {writeToLog(LogLevel.UNIMPORTANT, String.valueOf(message));}
    public void debug       (Object message) {writeToLog(LogLevel.DEBUG, String.valueOf(message));}
    public void check       (Object message) {writeToLog(LogLevel.CHECK, String.valueOf(message));}
    public void info        (Object message) {writeToLog(LogLevel.INFO, String.valueOf(message));}
    public void warn        (Object message) {writeToLog(LogLevel.WARN, String.valueOf(message));}
    public void error       (Object message) {writeToLog(LogLevel.ERROR, String.valueOf(message));}
    public void unimportant (String[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (String[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (String[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (String[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (String[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (String[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void error       (ImageString[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (ImageString[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (ImageString[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (ImageString[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (ImageString[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (ImageString[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void unimportant (char[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (char[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (char[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (char[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (char[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (char[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (int[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (int[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (int[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (int[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (int[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (int[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (long[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (long[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (long[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (long[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (long[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (long[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (BigInteger[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (BigInteger[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (BigInteger[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (BigInteger[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (BigInteger[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (BigInteger[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (double[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (double[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (double[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (double[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (double[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (double[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (float[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (float[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (float[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (float[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (float[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (float[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (byte[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (byte[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (byte[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (byte[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (byte[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (byte[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (boolean[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (boolean[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (boolean[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (boolean[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (boolean[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (boolean[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}
    public void unimportant (Object[] message) {writeToLog(LogLevel.UNIMPORTANT, Arrays.toString(message));}
    public void debug       (Object[] message) {writeToLog(LogLevel.DEBUG, Arrays.toString(message));}
    public void check       (Object[] message) {writeToLog(LogLevel.CHECK, Arrays.toString(message));}
    public void info        (Object[] message) {writeToLog(LogLevel.INFO, Arrays.toString(message));}
    public void warn        (Object[] message) {writeToLog(LogLevel.WARN, Arrays.toString(message));}
    public void error       (Object[] message) {writeToLog(LogLevel.ERROR, Arrays.toString(message));}

    public void warn        (String message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (String message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (char message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (char message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (int message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (int message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (long message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (long message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (short message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (short message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (double message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (double message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (float message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (float message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (byte message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (byte message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (boolean message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (boolean message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (Object message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, message + " " + t.getMessage());}
    public void error       (Object message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, message + " " + t.getMessage());}
    public void warn        (String[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (String[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (char[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (char[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (int[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (int[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (long[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (long[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (short[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (short[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (double[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (double[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (float[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (float[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (byte[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (byte[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (boolean[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (boolean[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}
    public void warn        (Object[] message, @NotNull Throwable t) {writeToLog(LogLevel.WARN, Arrays.toString(message) + " " + t.getMessage());}
    public void error       (Object[] message, @NotNull Throwable t) {writeToLog(LogLevel.ERROR, Arrays.toString(message) + " " + t.getMessage());}

    public void writeToLog(LogLevel level, String message) {
        if (!debug) {
            if (level.getImportance() != 'A') {
                String formattedMessage = formatMessage(level, message);
                out.println(formattedMessage);
                if (bufferedWriter == null) {
                    error("BufferedWriter not initialized.");
                    return;
                }
                try {
                    bufferedWriter.write(formattedMessage + "\n");
                    bufferedWriter.flush();
                } catch (IOException e) {
                    error("Error writing to writeToLog file: ", e);
                }
            }
        } else {
            String formattedMessage = formatMessage(level, message);
            out.println(formattedMessage);
            if (bufferedWriter == null) {
                error("BufferedWriter not initialized.");
                return;
            }
            try {
                bufferedWriter.write(formattedMessage + "\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                error("Error writing to writeToLog file: ", e);
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
            error("Error closing BufferedWriter: ", e);
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
            error("Error initializing BufferedWriter: ", e);
        }
    }
}
