package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Logger;
import dev.TTs.util.Identifier;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractColorJSONProvider extends AbstractProvider {
    private final Map<Identifier, ColorElement[]> colors;

    public AbstractColorJSONProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
        colors = new HashMap<>();
    }

    public ColorBuilder create(Identifier name) {
        return new ColorBuilder(name);
    }

    @Override
    public void run() {
        for (Identifier identifier : colors.keySet()) {
            write(identifier.dataJSONPath(), colors.get(identifier));
        }
    }

    public class ColorBuilder {
        private final Identifier name;
        private final List<ColorElement> colorElements;

        public ColorBuilder(Identifier name) {
            this.name = name;
            this.colorElements = new ArrayList<>();
        }

        public ColorBuilder add(Color... colors) {
            for (Color color : colors) {
                colorElements.add(new ColorElement(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha()));
            }
            return this;
        }

        public void build() {
            colors.put(name, colorElements.toArray(new ColorElement[0]));
        }
    }

    public record ColorElement(int r, int g, int b, int a) {}
}
