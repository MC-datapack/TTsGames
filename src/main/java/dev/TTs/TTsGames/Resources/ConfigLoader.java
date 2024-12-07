package dev.TTs.TTsGames.Resources;

import dev.TTs.lang.Array;

import java.io.*;
import java.util.Properties;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings("unused")
public class ConfigLoader {
    private final Properties properties = new Properties();
    private static final String CONFIG_DIR_PATH = jsonReader.getMainJSON().getConfig().getDir_path();
    private static final String CONFIG_FILE_PATH = jsonReader.getMainJSON().getConfig().getFile_path();
    private static final String[] BOOLEAN = {"true", "false"};

    // Property Keys
    private static final String[] KEYS = {
            "language", "devVersions", "volume", "username", "animalMasterTimeRecord", "animal_master_size_multiplier"
    }, DEFAULT_SETTINGS = {
            "English", "false", "100", "௹⨌{UsernameDe}", "-1", "1.0"
    };
    private final String[][] ALLOWED_SETTINGS = {
            Languages, BOOLEAN, IntS(0, 100), {}, {}, {}
    };

    public ConfigLoader() {
        ensurePropertiesFileExists();
        loadProperties();
        ensurePropertiesExist();
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);
            logger.debug("Properties loaded.");
        } catch (IOException e) {
            logger.error("Error loading properties: ", e);
        }
    }

    private void ensurePropertiesFileExists() {
        File configDir = new File(CONFIG_DIR_PATH);
        if (!configDir.exists()) {
            boolean m = configDir.mkdirs();
        }
        File configFile = new File(CONFIG_FILE_PATH);
        if (!configFile.exists()) {
            try (OutputStream output = new FileOutputStream(configFile)) {
                properties.setProperty(KEYS[0], DEFAULT_SETTINGS[0]);
                properties.setProperty(KEYS[1], DEFAULT_SETTINGS[1]);
                properties.setProperty(KEYS[2], DEFAULT_SETTINGS[2]);
                properties.setProperty(KEYS[3], DEFAULT_SETTINGS[3]);
                properties.setProperty(KEYS[4], DEFAULT_SETTINGS[4]);
                properties.setProperty(KEYS[5], DEFAULT_SETTINGS[5]);
                properties.store(output, null);
                logger.debug("Default config.properties file created.");
            } catch (IOException e) {
                logger.error("Error creating default properties file: ", e);
            }
        } else {
            logger.debug("config.properties exists.");
        }
    }
    
    private void ensurePropertiesExist() {
        if (Array.dontContains(ALLOWED_SETTINGS[0], properties.getProperty(KEYS[0]))) {
            properties.setProperty(KEYS[0], DEFAULT_SETTINGS[0]);
            saveProperties();
        }
        if (Array.dontContains(ALLOWED_SETTINGS[1], properties.getProperty(KEYS[1]))) {
            properties.setProperty(KEYS[1], DEFAULT_SETTINGS[1]);
            saveProperties();
        }
        if (Array.dontContains(ALLOWED_SETTINGS[2], properties.getProperty(KEYS[2]))) {
            properties.setProperty(KEYS[2], DEFAULT_SETTINGS[2]);
            saveProperties();
        }
        if (properties.getProperty(KEYS[3]) == null) {
            properties.setProperty(KEYS[3], DEFAULT_SETTINGS[3]);
            saveProperties();
        }
        if (properties.getProperty(KEYS[4]) == null) {
            properties.setProperty(KEYS[4], DEFAULT_SETTINGS[4]);
            saveProperties();
        }
        if (properties.getProperty(KEYS[5]) == null) {
            properties.setProperty(KEYS[5], DEFAULT_SETTINGS[5]);
            saveProperties();
        }
    }

    private void saveProperties() {
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
            properties.store(output, null);
            logger.unimportant("Properties saved.");
        } catch (IOException e) {
            logger.error("Error saving properties: ", e);
        }
    }

    public String getLanguage() {return properties.getProperty(KEYS[0]);}

    public void setLanguage(String newLanguage) {
        properties.setProperty(KEYS[0], newLanguage);
        saveProperties();
    }

    public boolean isDevVersionsEnabled() {return Boolean.parseBoolean(properties.getProperty(KEYS[1]));}

    public void setDevVersions(boolean isEnabled) {
        properties.setProperty(KEYS[1], Boolean.toString(isEnabled));
        saveProperties();
    }

    public int getVolume() {return Integer.parseInt(properties.getProperty(KEYS[2]));}

    public void setVolume(int volume) {
        properties.setProperty(KEYS[2], String.valueOf(volume));
        saveProperties();
    }

    public String getUsername() {return properties.getProperty(KEYS[3]);}

    public void setUsername(String username) {
        properties.setProperty(KEYS[3], username);
        saveProperties();
    }

    public float getAnimalMasterTimeRecord() {return Float.parseFloat(properties.getProperty(KEYS[4]));}

    public void setAnimalMasterTimeRecord(float timeRecord) {
        properties.setProperty(KEYS[4], String.valueOf(timeRecord));
        saveProperties();
    }

    public double getAnimal_master_size_multiplier() {return Double.parseDouble(properties.getProperty(KEYS[5]));}
    public void setAnimal_master_size_multiplier(double value) {
        properties.setProperty(KEYS[5], String.valueOf(value));
        saveProperties();
    }
}
