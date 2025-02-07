package dev.TTs.TTsGames.datagen.provider;

import com.google.gson.Gson;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Provider;
import dev.TTs.util.Identifier;
import dev.TTs.TTsGames.datagen.provider.abstracts.AbstractSoundProvider;
import dev.TTs.lang.Logger;

@Provider(name = "Sound Provider")
public class SoundProvider extends AbstractSoundProvider {
    public SoundProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
    }

    @Override
    public void generate() {
        createSound(Identifier.ofPixelQuest("eat"))
                .addForAllLanguages("eat")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/001"))
                .addLanguageUsingLangPrefix("001")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/002"))
                .addLanguageUsingLangPrefix("002")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/003"))
                .addLanguageUsingLangPrefix("003")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/004"))
                .addLanguageUsingLangPrefix("004")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/005"))
                .addLanguageUsingLangPrefix("005")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/006"))
                .addLanguageUsingLangPrefix("006")
                .build();
        createSound(Identifier.ofDetectiveThunder("phrases/007"))
                .addLanguageUsingLangPrefix("007")
                .build();
        createSound(Identifier.ofAnimalMaster("correct"))
                .addForAllLanguages("correct")
                .build();
        createSound(Identifier.ofAnimalMaster("incorrect"))
                .addForAllLanguages("incorrect")
                .build();
    }
}
