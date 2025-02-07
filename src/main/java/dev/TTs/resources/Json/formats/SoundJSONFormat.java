package dev.TTs.resources.Json.formats;

import java.util.Map;

@SuppressWarnings({"unused", "MismatchedQueryAndUpdateOfCollection"})
public class SoundJSONFormat {
    private String base_path;
    private Map<String, String> files;

    public String getFiles(String language) {
        return base_path + "/" + files.get(language) + ".wav";
    }
}
