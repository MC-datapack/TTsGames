package dev.TTs.resources;

import java.io.*;
import java.util.Properties;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings("unchecked")
public class ConfigLoader {
    private final Properties properties = new Properties();
    private static final String CONFIG_DIR_PATH = jsonReader.MainJSON.getConfig().getFolder_path();
    private static final String CONFIG_FILE_PATH = jsonReader.MainJSON.getConfig().getFile_name();

    public ConfigLoader() {
        ensurePropertiesFileExists();
        loadProperties();
        ensurePropertiesAreValid();
    }

    private void ensurePropertiesFileExists() {
        File configDir = new File(CONFIG_DIR_PATH);
        if (!configDir.exists() && !configDir.mkdirs()) {
            logger.error("Failed to create config directory -> %s", CONFIG_DIR_PATH);
        }

        File configFile = new File(CONFIG_FILE_PATH);
        if (!configFile.exists()) {
            for (Configs config : Configs.values()) {
                properties.setProperty(config.getKey(), config.getDefaultValue());
            }
            saveProperties();
            logger.debug("Default config.properties file created.");
        } else {
            logger.unimportant("config.properties exists.");
        }
    }

    private void ensurePropertiesAreValid() {
        for (Configs config : Configs.values()) {
            String value = properties.getProperty(config.getKey());
            if (value == null || !config.isValid(value)) {
                setProperty(config.getKey(), config.getDefaultValue());
            }
        }
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            logger.debug("Properties loaded.");
        } catch (IOException e) {
            logger.error("Error loading properties -> %s", e);
        }
    }

    private void saveProperties() {
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
            properties.store(output, null);
            logger.unimportant("Properties saved.");
        } catch (IOException e) {
            logger.error("Error saving properties -> %s", e);
        }
    }

    private void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    public <T> T get(Configs config) {
        String value = properties.getProperty(config.getKey());
        if (value == null) return null;
        return switch (config.getType()) {
            case STRING -> (T) value;
            case BOOLEAN -> (T) Boolean.valueOf(value);
            case INT -> (T) Integer.valueOf(value);
            case DOUBLE -> (T) Double.valueOf(value);
            case FLOAT -> (T) Float.valueOf(value);
            case LONG -> (T) Long.valueOf(value);
            case SHORT -> (T) Short.valueOf(value);
            case BYTE -> (T) Byte.valueOf(value);
            default -> throw new IllegalArgumentException("Unsupported format: " + config);
        };
    }

    public void set(Configs config, Object value) {
        setProperty(config.getKey(), String.valueOf(value));
    }
}