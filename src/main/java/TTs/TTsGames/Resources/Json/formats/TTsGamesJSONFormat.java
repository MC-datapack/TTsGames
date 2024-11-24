package TTs.TTsGames.Resources.Json.formats;

import java.io.File;
import java.util.*;

@SuppressWarnings("unused")
public class TTsGamesJSONFormat {
    private boolean prerelease;
    private String unallowed_usernames, animal_master_button_colors, animal_master_correct, animal_master_selected;
    private Map<String, GameVersion> version;
    private Map<String, String> language_files;
    private String no_texture_file, language_files_base_dictionary, textures_base_dictionary, sounds_base_dictionary;
    private List<String> languages;
    private List<List<String>> textures, sounds;

    public String getUnallowedUsernames() {return unallowed_usernames;}
    public Map<String, GameVersion> getVersion() {return version;}
    public List<String> getLanguages() {return languages;}
    public Map<String, String> getLanguageMap() {return language_files;}
    public String getNo_texture_file() {return textures_base_dictionary + "/" + no_texture_file;}
    public List<List<String>> getTextures() {
        List<List<String>> newTextures = new java.util.ArrayList<>();
        for (List<String> textureList : textures) {
            List<String> newList = new java.util.ArrayList<>();
            for (String animal : textureList) {
                newList.add(textures_base_dictionary + "/" + animal);
            }
            newTextures.add(newList);
        }
        return newTextures;
    }
    public boolean getPrerelease() {return prerelease;}
    public List<List<String>> getSounds() {
        List<List<String>> newTextures = new java.util.ArrayList<>();
        for (List<String> soundList : sounds) {
            List<String> newList = new java.util.ArrayList<>();
            for (String sound : soundList) {
                newList.add(sounds_base_dictionary + "/" + sound);
            }
            newTextures.add(newList);
        }
        return newTextures;
    }

    public List<List<String>> getMainTextures() {return textures;}
    public List<List<String>> getMainSounds() {return sounds;}

    public String getAnimal_master_button_colors() {return animal_master_button_colors;}
    public String getAnimal_master_selected() {return animal_master_selected;}
    public String getAnimal_master_correct() {return animal_master_correct;}

    public static class GameVersion {
        private String release, prerelease, group;

        public String getRelease() {return release;}
        public String getPrerelease() {return prerelease;}
        public String getGroup() {return group;}
    }

    public String[] toStringArray() {
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
