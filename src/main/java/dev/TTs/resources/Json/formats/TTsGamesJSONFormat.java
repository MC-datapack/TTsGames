package dev.TTs.resources.Json.formats;

import dev.TTs.lang.ImageString;
import dev.TTs.lang.SoundString;

import java.util.*;

@SuppressWarnings({"unused", "MismatchedReadAndWriteOfArray", "MismatchedQueryAndUpdateOfCollection"})
public class TTsGamesJSONFormat {
    private String[] supported_java_versions;
    private boolean prerelease;
    private Map<String, GameVersion> version;
    private Config config;
    private String language_files_base_dictionary;
    private String[] languages;
    private Map<String, String> language_files;
    private String[][] textures_base_dictionaries;
    private String[][] textures_file_types;
    private String no_texture_file;
    private String[][][] textures;
    private String[] sounds_base_dictionaries;
    private String[][] sounds;
    private String[] data_base_dictionaries;
    private String[] data_file_types;
    private String[][] data;

    public String[] getSupportedJavaVersions() {
        return supported_java_versions;
    }

    public boolean getPrerelease() {
        return prerelease;
    }
    public Map<String, GameVersion> getVersion() {
        return version;
    }
    public String[] getLanguages() {
        return languages;
    }
    public String getLanguageFile(String language) {
        return language_files_base_dictionary + "/" + language_files.get(language) + ".json";
    }
    public ImageString getNoTextureFile() {
        return new ImageString(no_texture_file);
    }
    public ImageString[][][] getTextures() {
        ImageString[][][] newTextures = new ImageString[textures.length][][];
        for (int i = 0; i < textures.length; i++) {
            newTextures[i] = new ImageString[textures[i].length][];
            for (int j = 0; j < textures[i].length; j++) {
                newTextures[i][j] = new ImageString[textures[i][j].length];
                for (int k = 0; k < textures[i][j].length; k++) {
                    newTextures[i][j][k] = new ImageString(textures_base_dictionaries[i][j] + "/" + textures[i][j][k] + textures_file_types[i][j]);
                }
            }
        }
        return newTextures;
    }

    public SoundString[][] getSounds() {
        SoundString[][] newTextures = new SoundString[textures.length][];
        for (int i = 0; i < sounds.length; i++) {
            newTextures[i] = new SoundString[sounds[i].length];
            for (int j = 0; j < sounds[i].length; j++) {
                newTextures[i][j] = new SoundString(sounds_base_dictionaries[i] + "/" + sounds[i][j] + ".json", i);
            }
        }
        return newTextures;
    }

    public String[][] getData() {
        String[][] newData = new String[data.length][];
        for (int i = 0; i < data.length; i++) {
            newData[i] = new String[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                newData[i][j] = data_base_dictionaries[i] + "/" + data[i][j] + data_file_types[i];
            }
        }
        return newData;
    }

    public Config getConfig() {
        return config;
    }

    public static class GameVersion {
        private String release, prerelease, group;

        public String getRelease() {return release;}
        public String getPrerelease() {return prerelease;}
        public String getGroup() {return group;}
    }

    public static class Config {
        private String folder_path, file_name;

        public String getFolder_path() {return System.getProperty("user.home") + "/" + folder_path;}
        public String getFile_name() {return System.getProperty("user.home") + "/" + folder_path + "/" + file_name;}
    }

    public String[] getVersions() {
        String[] versions = new String[14];
        if (!prerelease) {versions[0] = version.get("tts_games").getRelease();}
        else {versions[0] = version.get("tts_games").getPrerelease();}
        versions[1] = version.get("tts_games").getGroup();
        versions[2] = version.get("animal_master").getRelease();
        versions[3] = version.get("animal_master").getPrerelease();
        versions[4] = version.get("animal_master").getGroup();
        versions[5] = version.get("detective_eagle").getRelease();
        versions[6] = version.get("detective_eagle").getPrerelease();
        versions[7] = version.get("detective_eagle").getGroup();
        versions[8] = version.get("detective_thunder").getRelease();
        versions[9] = version.get("detective_thunder").getPrerelease();
        versions[10] = version.get("detective_thunder").getGroup();
        versions[11] = version.get("pixel_quest").getRelease();
        versions[12] = version.get("pixel_quest").getPrerelease();
        versions[13] = version.get("pixel_quest").getGroup();
        return versions;
    }
}
