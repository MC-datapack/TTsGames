package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagKey;
import dev.TTs.lang.ErrorHandlingStrategy;
import dev.TTs.lang.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTagProvider<T> extends AbstractProvider {
    private final List<Tag> tags;

    public AbstractTagProvider(String basePath, Gson gson, Logger logger, ErrorHandlingStrategy errorStrategy) {
        super(basePath, gson, logger, errorStrategy);
        this.tags = new ArrayList<>();
    }

    public TagBuilder<T> createTag(TagKey<T> tag) {
        return new TagBuilder<>(tag);
    }

    @Override
    public void run() {
        for (Tag tag : tags) {
            write(tag.name.path(), tag.json());
        }
    }

    public class TagBuilder<T> {
        private final TagKey<T> tagKey;
        private final List<String> values = new ArrayList<>();

        public TagBuilder(TagKey<T> tagKey) {
            this.tagKey = tagKey;
        }

        @SafeVarargs
        public final TagBuilder<T> add(T... ts) {
            for (T t : ts) {
                if (t instanceof Item tItem) this.values.add(tItem.identifier().itemId());
                if (t instanceof Entity entity) this.values.add(entity.id().toString());
            }
            tags.add(new Tag(tagKey, values));
            return this;
        }

        public TagBuilder<T> addTag(TagKey<T> tag) {
            this.values.add("#" + tag.id().toString());
            tags.add(new Tag(tagKey, values));
            return this;
        }
    }

    private record Tag(TagKey name, List<String> values) {
        public BaseTag json() {
            return new BaseTag(name.id().toString(), values);
        }
        private record BaseTag(String name, List<String> values) {}
    }
}
