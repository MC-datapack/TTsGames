package dev.TTs.TTsGames.Games.PixelQuest.util;

import dev.TTs.TTsGames.Games.PixelQuest.json.TagType;
import dev.TTs.lang.ImageString;
import dev.TTs.resources.Json.Text;

import java.io.*;
import java.util.Objects;

import static dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving.serialVersion;

public final class Identifier implements Serializable {
    @Serial
    private static final long serialVersionUID = serialVersion;
    private String project;
    private String name;

    public Identifier(String project, String name) {
        this.project = project;
        this.name = name;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(toString());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String serializedString = (String) in.readObject();
        String[] parts = serializedString.split(":");
        this.project = parts.length > 1 ? parts[0] : "pixel_quest";
        this.name = parts.length > 1 ? parts[1] : parts[0];
    }

    public Identifier(String name) {
        this(name.contains(":") ? name.split(":")[0] : "pixel_quest",
                name.contains(":") ? name.split(":")[1] : name);
    }

    public String getLootTablePath() {
        return "data/" + project + "/loot_table/" + name + ".json";
    }

    public ImageString getItemTexture() {
        return ImageCache.getImage("assets/textures/" + project + "/item/" + name + ".png");
    }

    public ImageString getEntityTexture() {
        return ImageCache.getImage("assets/textures/" + project + "/entity/" + name + ".png");
    }

    public String itemTranslationKey() {
        return project + ".item." + name;
    }

    public String translatedItemName() {
        return Text.translatable(itemTranslationKey());
    }

    public String itemId() {
        return this.toString();
    }

    public String furnaceRecipeName() {
        return "data/" + project + "/recipe/furnace/" + name + ".json";
    }

    public String craftingRecipeName() {
        return "data/" + project + "/recipe/crafting/" + name + ".json";
    }

    @Override
    public String toString() {
        return project + ":" + name;
    }

    public String getTag(TagType type) {
        return "data/" + project + "/tags/" + type.getIdentifier() + "/" + name + ".json";
    }

    public String project() {
        return project;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Identifier) obj;
        return Objects.equals(this.project, that.project) &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, name);
    }
}
