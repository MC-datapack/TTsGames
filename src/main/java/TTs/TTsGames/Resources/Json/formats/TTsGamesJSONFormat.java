package TTs.TTsGames.Resources.Json.formats;

import java.util.*;

@SuppressWarnings("unused")
public class TTsGamesJSONFormat {
    private boolean prerelease;
    private Map<String, GameVersion> version;
    private String[] languages;
    private Map<String, String> language_files;
    private String textures_base_dictionary;
    private String no_texture_file;
    private String[][] textures;
    private String sounds_base_dictionary;
    private String[][] sounds;
    private String data_base_dictionary;
    private String[][] data;
    //private String unallowed_usernames, animal_master_button_colors, animal_master_correct, animal_master_selected, animal_master_answer_trans_key;

    public boolean getPrerelease() {return prerelease;}
    public Map<String, GameVersion> getVersion() {return version;}
    public String[] getLanguages() {return languages;}
    public Map<String, String> getLanguageMap() {return language_files;}
    public String getNo_texture_file() {return textures_base_dictionary + "/" + no_texture_file;}
    public String[][] getTextures() {
        String[][] newTextures = new String[textures.length][];
        for (int i = 0; i < textures.length; i++) {
            newTextures[i] = new String[textures[i].length];
            for (int j = 0; j < textures[i].length; j++) {
                newTextures[i][j] = textures_base_dictionary + "/" + textures[i][j];
            }
        }
        return newTextures;
    }
    public String[][] getSounds() {
        String[][] newSounds = new String[sounds.length][];
        for (int i = 0; i < sounds.length; i++) {
            newSounds[i] = new String[sounds[i].length];
            for (int j = 0; j < sounds[i].length; j++) {
                newSounds[i][j] = sounds_base_dictionary + "/" + sounds[i][j];
            }
        }
        return newSounds;
    }

    public String[][] getData() {
        String[][] newData = new String[data.length][];
        for (int i = 0; i < data.length; i++) {
            newData[i] = new String[data[i].length];
            for (int j = 0; j < data[i].length; j++) {
                newData[i][j] = data_base_dictionary + "/" + data[i][j];
            }
        }
        return newData;
    }

    public static class GameVersion {
        private String release, prerelease, group;

        public String getRelease() {return release;}
        public String getPrerelease() {return prerelease;}
        public String getGroup() {return group;}
    }

    public String[] getVersions() {
        String[] versions = new String[8];
        if (!prerelease) {versions[0] = version.get("TTsGames").getRelease();}
        else {versions[0] = version.get("TTsGames").getPrerelease();}
        versions[1] = version.get("AnimalMaster").getRelease();
        versions[2] = version.get("AnimalMaster").getPrerelease();
        versions[3] = version.get("DetektivAdler").getRelease();
        versions[4] = version.get("DetektivAdler").getPrerelease();
        versions[5] = version.get("DetektivThunder").getRelease();
        versions[6] = version.get("DetektivThunder").getPrerelease();
        versions[7] = version.get("TTsGames").getGroup();
        return versions;
    }
}
