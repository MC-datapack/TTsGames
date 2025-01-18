package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static dev.TTs.TTsGames.Main.logger;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public abstract class AbstractProvider {
    protected final String basePath;
    protected Gson gson;

    public AbstractProvider(String basePath, Gson gson) {
        this.basePath = basePath;
        this.gson = gson;
    }

    public abstract void generate();
    public abstract void run();

    protected void write(String path, String json) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
        } catch (IOException e) {
            logger.error("Error writing JSON to file: %s", e.getMessage());
        }
    }

    protected boolean checkDictionary(String path) {
        String directoryPath = dictionaryPath(path);

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean dirsCreated = directory.mkdirs();
            if (!dirsCreated) {
                logger.error("Failed to create directories: %s", directoryPath);
                return false;
            }
            return true;
        }
        return true;
    }

    private String dictionaryPath(String path) {
        if (path.contains("/")) {
            return path.substring(0, path.lastIndexOf('/'));
        } else {
            return basePath;
        }
    }
}
