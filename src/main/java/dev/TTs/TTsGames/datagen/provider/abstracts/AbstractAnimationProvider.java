package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.util.Identifier;
import dev.TTs.lang.Logger;

import java.io.File;
import java.util.*;

public abstract class AbstractAnimationProvider extends AbstractProvider {
    private final List<AnimationFormat> animations;

    public AbstractAnimationProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
        animations = new ArrayList<>();
    }

    public AnimationBuilder createAnimation(Identifier id) {
        return new AnimationBuilder(id);
    }

    @Override
    public void run() {
        for (AnimationFormat animation : animations) {
            write(Identifier.of(animation.name()).animationJSONPath(), animation);
        }
    }

    public class AnimationBuilder {
        private final Identifier name;
        private Map<String, String> files = new HashMap<>();
        private final String basePath;
        private List<String> paths = new ArrayList<>();
        private String fileFormat = ".png";
        private int delay = 100;
        private boolean repeat = true;
        private boolean blend = true;

        public AnimationBuilder(Identifier name) {
            this.name = name;
            this.basePath = new File(name.animationJSONPath()).getParentFile().getPath().replace("\\", "/");
        }

        public AnimationBuilder addPath(String... paths) {
            this.paths.addAll(Arrays.asList(paths));
            return this;
        }

        public AnimationBuilder addPathsUsingUnderscoreCounter(int max, String baseName) {
            for (int i = 0; i <= max; i++) {
                this.paths.add(baseName + "_" + i);
            }
            return this;
        }

        public AnimationBuilder setFileFormat(String fileFormat) {
            this.fileFormat = fileFormat;
            return this;
        }

        public AnimationBuilder setDelay(int delay) {
            this.delay = delay;
            return this;
        }

        public AnimationBuilder setRepeat(boolean repeat) {
            this.repeat = repeat;
            return this;
        }

        public AnimationBuilder setBlend(boolean blend) {
            this.blend = blend;
            return this;
        }

        public void build() {
            animations.add(new AnimationFormat(name.toString(), basePath, fileFormat, paths, delay, repeat, blend));
        }
    }

    public record AnimationFormat(String name, String base_path, String file_format, List<String> paths, int delay, boolean repeat, boolean blend) { }
}
