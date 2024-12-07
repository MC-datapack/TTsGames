package dev.TTs.TTsGames.Resources.Json.formats;

import dev.TTs.lang.ImageString;
import dev.TTs.lang.SoundString;

import java.util.*;

@SuppressWarnings("unused")
public final class TTsGamesJSONFormat {
    private boolean prerelease;
    private Map<String, GameVersion> version;
    private Config config;
    private String[] languages;
    private Map<String, String> language_files;
    private String[] textures_base_dictionaries;
    private String no_texture_file;
    private String[][] textures;
    private String[] sounds_base_dictionaries;
    private String[] sounds_file_types;
    private String[][] sounds;
    private String[] data_base_dictionaries;
    private String[][] data;

    public boolean getPrerelease() {
        return prerelease;
    }
    public Map<String, GameVersion> getVersion() {
        return version;
    }
    public String[] getLanguages() {
        return languages;
    }
    public Map<String, String> getLanguageFiles() {
        return language_files;
    }
    public ImageString getNoTextureFile() {
        return new ImageString(textures_base_dictionaries[0] + "/" + no_texture_file);
    }
    public ImageString[][] getTextures() {
        ImageString[][] newTextures = new ImageString[textures.length][];
        for (int i = 0; i < textures.length; i++) {
            newTextures[i] = new ImageString[textures[i].length];
            for (int j = 0; j < textures[i].length; j++) {
                newTextures[i][j] = new ImageString (textures_base_dictionaries[i] + "/" + textures[i][j]);
            }
        }
        return newTextures;
    }
    public SoundString[][] getSounds() {
        SoundString[][] newTextures = new SoundString[textures.length][];
        for (int i = 0; i < sounds.length; i++) {
            newTextures[i] = new SoundString[sounds[i].length];
            for (int j = 0; j < sounds[i].length; j++) {
                newTextures[i][j] = new SoundString(sounds_base_dictionaries[i] + "/" + sounds[i][j] + sounds_file_types[i]);
            }
        }
        return newTextures;
    }

    public String[][] getData() {
        String[][] newData = new String[data.length][];
        for (int i = 0; i < data.length; i++) {
            newData[i] = new String[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                newData[i][j] = data_base_dictionaries[i] + "/" + data[i][j];
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
        private String dir_path, file_path;

        public String getDir_path() {return System.getProperty("user.home") + "/" + dir_path;}
        public String getFile_path() {return System.getProperty("user.home") + "/" + dir_path + "/" + file_path;}
    }

    public String[] getVersions() {
        String[] versions = new String[8];
        if (!prerelease) {versions[0] = version.get("TTsGames").getRelease();}
        else {versions[0] = version.get("TTsGames").getPrerelease();}
        versions[1] = version.get("AnimalMaster").getRelease();
        versions[2] = version.get("AnimalMaster").getPrerelease();
        versions[3] = version.get("DetectiveEagle").getRelease();
        versions[4] = version.get("DetectiveEagle").getPrerelease();
        versions[5] = version.get("DetectiveThunder").getRelease();
        versions[6] = version.get("DetectiveThunder").getPrerelease();
        versions[7] = version.get("TTsGames").getGroup();
        return versions;
    }
}
