package dev.TTs.resources.Json.formats;

import java.util.Map;

@SuppressWarnings("unused")
public class SoundJSONFormat {
    private String base_dictionary;
    private Map<String, String> files;

    public String getFiles(String language) {
        return base_dictionary + "/" + files.get(language) + ".wav";
    }
}
