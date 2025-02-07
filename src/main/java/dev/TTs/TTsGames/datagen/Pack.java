package dev.TTs.TTsGames.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.Predicate;
import dev.TTs.TTsGames.Games.PixelQuest.predicate.PredicateAdapter;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractProvider;
import dev.TTs.lang.*;
import dev.TTs.util.ClassFinder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static dev.TTs.TTsGames.Main.logger;

public class Pack {
    private final String basePath;
    private final Gson gson;
    private final ClassFinder<AbstractProvider> privderClassFinder;

    public Pack() {
        this("src/main/generated");
    }

    public Pack(String basePath) {
        this.basePath = basePath;
        privderClassFinder = new ClassFinder<>(AbstractProvider.class);
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(Predicate[].class, new PredicateAdapter());
        gson = builder.create();
        File dict = new File(basePath);
        if (dict.isDirectory()) {
            deleteAllFiles(dict);
        }
    }

    public void addAllProvider() {
        try {
            List<Class<? extends AbstractProvider>> providerClasses = privderClassFinder
                    .getClassesWithAnnotation(dev.TTs.lang.Provider.class)
                    .stream()
                    .filter(aClass -> aClass.getAnnotation(Provider.class).run())
                    .toList();
            List<Provider> providerAnnotations = new ArrayList<>();
            providerClasses.forEach(aClass -> providerAnnotations.add(aClass.getAnnotation(Provider.class)));
            logger.info("AbstractProvider Classes: %s", providerAnnotations);
            for (Class<? extends AbstractProvider> providerClass : providerClasses) {
                addProvider(providerClass);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Failed getting subclasses of AbstractProvider: %s", e.getMessage());
        }
    }

    public <T extends AbstractProvider> void addProvider(Class<T> providerClass) {
        String name = providerClass.getAnnotation(Provider.class).name();
        try {
            AbstractProvider provider = providerClass.getDeclaredConstructor(String.class, Gson.class, Logger.class, ErrorHandlingStrategy.class)
                    .newInstance(basePath, gson, new DebugLogger(name), ErrorHandlingStrategy.PRINT);
            provider.generate();
            provider.run();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassCastException e) {
            logger.error("Failed to create an instance of class: %s", providerClass.getName(), e);
            throw new RuntimeException("Failed to create an instance of class: " + providerClass.getName(), e);
        }
    }

    private void deleteAllFiles(File directory) {
        try {
            Files.walk(Paths.get(directory.toURI()))
                    .map(java.nio.file.Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            logger.error("Failed to delete files in directory: %s", directory.getAbsolutePath(), e);
        }
    }
}
