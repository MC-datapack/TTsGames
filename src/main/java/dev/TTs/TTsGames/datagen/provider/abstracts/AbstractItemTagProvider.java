package dev.TTs.TTsGames.datagen.provider.abstracts;

import com.google.gson.Gson;
import dev.TTs.TTsGames.Games.PixelQuest.item.Item;
import dev.TTs.TTsGames.Games.PixelQuest.util.TagKey;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractItemTagProvider extends AbstractProvider {
    private final List<Tag> tags;

    public AbstractItemTagProvider(String basePath, Gson gson) {
        super(basePath, gson);
        this.tags = new ArrayList<>();
    }

    public TagBuilder createTag(TagKey tag) {
        return new TagBuilder(tag);
    }

    @Override
    public void run() {
        for (Tag tag : tags) {
            String path = basePath + "/" + tag.name.path();
            if (!checkDictionary(path)) continue;

            String json = gson.toJson(tag.json());

            write(path, json);
        }
    }

    public class TagBuilder {
        private final TagKey tagKey;
        private final List<String> values = new ArrayList<>();

        public TagBuilder(TagKey tagKey) {
            this.tagKey = tagKey;
        }

        public TagBuilder add(Item... items) {
            for (Item item : items) {
                this.values.add(item.identifier().itemId());
            }
            tags.add(new Tag(tagKey, values));
            return this;
        }

        public TagBuilder addTag(TagKey tag) {
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
