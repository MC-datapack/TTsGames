package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.lang.ImageString;
import dev.TTs.resources.Json.Text;

public record Identifier(String project, String name) {

    public Identifier(String name) {
        this(name.contains(":") ? name.split(":")[0] : "pixel_quest",
                name.contains(":") ? name.split(":")[1] : name);
    }

    public String getLootTablePath() {
        return "data/" + project + "/loot_table/" + name + ".json";
    }

    public ImageString getItemTexture() {
        return new ImageString("assets/textures/" + project + "/item/" + name + ".png");
    }

    public ImageString getEntityTexture() {
        return new ImageString("assets/textures/" + project + "/entity/" + name + ".png");
    }

    public String itemTranslationKey() {
        return project + ".item." + name;
    }

    public String translatedItemName() {
        return Text.translatable(itemTranslationKey());
    }

    public String itemId() {
        return project + ":" + name;
    }
}
