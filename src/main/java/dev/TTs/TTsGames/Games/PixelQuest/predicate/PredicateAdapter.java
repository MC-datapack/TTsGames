package dev.TTs.TTsGames.Games.PixelQuest.predicate;

import com.google.gson.*;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;

import java.lang.reflect.Type;

import static dev.TTs.TTsGames.Main.logger;

public class PredicateAdapter implements JsonSerializer<Predicate>, JsonDeserializer<Predicate> {
    @Override
    public JsonElement serialize(Predicate src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        if (src instanceof KilledByPlayerPredicate) {
            jsonObject.addProperty("type", "pixel_quest:killed_by_player");
        } else {
            logger.error("Unknown Predicate type: %s", src);
        }
        return jsonObject;
    }

    @Override
    public Predicate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get("type");

        if (typeElement == null || typeElement.isJsonNull()) {
            logger.error("Missing or null type field in Predicate JSON");
            return entity -> true;
        }

        String type = typeElement.getAsString();
        if ("pixel_quest:killed_by_player".equals(type)) {
            return new KilledByPlayerPredicate();
        } else {
            logger.error("Unknown Predicate type: " + type);
            return null;
        }
    }
}
