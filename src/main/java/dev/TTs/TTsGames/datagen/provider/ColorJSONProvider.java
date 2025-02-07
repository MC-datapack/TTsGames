package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractColorJSONProvider;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Logger;
import dev.TTs.lang.Provider;
import dev.TTs.util.Identifier;

import java.awt.*;

@Provider(name = "Color Json Provider")
public class ColorJSONProvider extends AbstractColorJSONProvider {
    public ColorJSONProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
    }

    @Override
    public void generate() {
        create(Identifier.ofAnimalMaster("colors"))
                .add(new Color(255, 255, 255))
                .add(new Color(241, 241, 241))
                .add(new Color(248, 237, 237))
                .add(new Color(238, 239, 255))
                .add(new Color(255, 238, 255))
                .add(new Color(225, 225, 225))
                .add(new Color(237, 255, 218))
                .add(new Color(222, 255, 245))
                .add(new Color(255, 221, 215))
                .add(new Color(201, 185, 220))
                .build();
    }
}
