package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Provider;
import dev.TTs.util.Identifier;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractAnimationProvider;
import dev.TTs.lang.Logger;

@Provider(name = "Animation Provider")
public class AnimationProvider extends AbstractAnimationProvider {
    public AnimationProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
    }

    @Override
    public void generate() {
        createAnimation(Identifier.ofDetectiveThunder("locations/Kunstgalerie_Fingerabdruck_0.png"))
                .addPathsUsingUnderscoreCounter(3, "Kunstgalerie_Fingerabdruck")
                .setRepeat(false)
                .build();
        createAnimation(Identifier.ofDetectiveThunder("locations/Wache_0.png"))
                .addPathsUsingUnderscoreCounter(10, "Wache")
                .build();
    }
}
