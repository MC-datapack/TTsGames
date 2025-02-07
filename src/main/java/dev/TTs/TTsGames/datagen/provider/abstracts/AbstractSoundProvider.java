package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.util.Identifier;
import dev.TTs.lang.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.TTs.TTsGames.Main.jsonReader;

public abstract class AbstractSoundProvider extends AbstractProvider {
    private final List<SoundFormat> sounds;

    public AbstractSoundProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
        sounds = new ArrayList<>();
    }

    public SoundBuilder createSound(Identifier id) {
        return new SoundBuilder(id);
    }

    @Override
    public void run() {
        for (SoundFormat sound : sounds) {
            write(Identifier.ofPixelQuest(sound.name()).soundJSONPath(), sound);
        }
    }

    public class SoundBuilder {
        private final Identifier name;
        private final Map<String, String> files = new HashMap<>();
        private final String basePath;

        public SoundBuilder(Identifier name) {
            this.name = name;
            this.basePath = new File(name.soundJSONPath()).getParentFile().getPath().replace("\\", "/");
        }

        public SoundBuilder addLanguage(String language, String path) {
            this.files.put(language, path);
            return this;
        }

        public SoundBuilder addLanguageUsingLangPrefix(String name) {
            for (String lang : jsonReader.MainJSON.getLanguages()) {
                this.files.put(lang, jsonReader.MainJSON.getLanguageFiles(lang) + "/" + name);
            }
            return this;
        }

        public SoundBuilder addForAllLanguages(String path) {
            for (String lang : jsonReader.MainJSON.getLanguages()) {
                this.files.put(lang, path);
            }
            return this;
        }

        public void build() {
            sounds.add(new SoundFormat(name.toString(), this.basePath, files));
        }
    }

    public record SoundFormat(String name, String base_path, Map<String, String> files) {}
}
