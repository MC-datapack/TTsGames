package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public abstract class AbstractProvider {
    protected final String basePath;
    protected Gson gson;
    protected Logger logger;
    protected ErrorHandlingStrategy errorStrategy;

    public AbstractProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        this.basePath = basePath;
        this.gson = gson;
        this.logger = logger;
        this.errorStrategy = errorStrategy;
        logger.debug("Starting AbstractProvider");
    }

    public abstract void generate();
    public abstract void run();

    protected void write(String mainPath, Object object) {
        String path = basePath + "/" + mainPath;
        if (!checkDictionary(path)) return;
        String json = gson.toJson(object);
        try (FileWriter writer = new FileWriter(path)) {
            logger.debug("Writing file at %s", path);
            writer.write(json);
        } catch (IOException e) {
            switch (errorStrategy) {
                case PRINT -> logger.error("Error writing JSON to file: %s", e.getMessage());
                case THROW -> throw new RuntimeException(e.getMessage());
            }
        }
    }

    protected boolean checkDictionary(String path) {
        String directoryPath = dictionaryPath(path);

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean dirsCreated = directory.mkdirs();
            if (!dirsCreated) {
                switch (errorStrategy) {
                    case PRINT -> logger.error("Failed to create directories: %s", directoryPath);
                    case THROW -> throw new RuntimeException("Failed to create directories: " + directoryPath);
                }
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
