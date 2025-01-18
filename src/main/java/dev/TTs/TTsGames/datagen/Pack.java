package dev.TTs.TTsGames.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractProvider;

import java.io.File;
import java.util.function.BiFunction;
import java.util.function.Function;

import static dev.TTs.TTsGames.Main.logger;

public class Pack {
    private final String basePath;
    private final Gson gson;

    public Pack() {
        this("src/main/generated");
    }

    public Pack(String basePath) {
        this.basePath = basePath;
        gson = new GsonBuilder().setPrettyPrinting().create();
        File dict = new File(basePath);
        if (dict.isDirectory()) {
            deleteAllFiles(dict);
        }
    }

    public void addProvider(BiFunction<String, Gson, ? extends AbstractProvider> providerConstructor) {
        AbstractProvider provider = providerConstructor.apply(basePath, gson);
        provider.generate();
        provider.run();
    }

    private void deleteAllFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteAllFiles(file);
                    file.delete();
                }
            }
        }
    }
}
