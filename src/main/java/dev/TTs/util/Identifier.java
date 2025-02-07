package dev.TTs.util;

import dev.TTs.TTsGames.Games.PixelQuest.util.TagType;
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

    private Identifier(String project, String name) {
        this.project = project;
        this.name = name;
    }

    private Identifier(String name) {
        this(name.contains(":") ? name.split(":")[0] : "pixel_quest",
                name.contains(":") ? name.split(":")[1] : name);
    }

    public static Identifier of(String project, String name)  {
        return new Identifier(project, name);
    }

    public static Identifier of(String name) {
        return new Identifier(name);
    }

    public static Identifier ofAnimalMaster(String name) {
        return new Identifier("animal_master", name);
    }

    public static Identifier ofDetectiveEagle(String name) {
        return new Identifier("detective_eagle", name);
    }

    public static Identifier ofDetectiveThunder(String name) {
        return new Identifier("detective_thunder", name);
    }

    public static Identifier ofPixelQuest(String name) {
        return new Identifier(name);
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

    public String getLootTablePath() {
        return "data/" + project + "/loot_table/" + name + ".json";
    }

    public ImageString getItemTexture() {
        return IdentifierCache.getImage("assets/textures/" + project + "/item/" + name + ".png");
    }

    public ImageString getEntityTexture() {
        return IdentifierCache.getImage("assets/textures/" + project + "/entity/" + name + ".png");
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

    public String soundJSONPath() {
        return "assets/sounds/" + project + "/" + name + ".json";
    }

    public String animationJSONPath() {
        return "assets/textures/" + project + "/" + name + ".json";
    }

    public String dataJSONPath() {
        return "data/" + project + "/" + name + ".json";
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
