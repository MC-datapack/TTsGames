package dev.TTs.TTsGames.Games.PixelQuest.predicate;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static dev.TTs.TTsGames.Main.logger;

public class PredicateAdapter implements JsonSerializer<Predicate<?>[]>, JsonDeserializer<Predicate<?>[]> {

    @Override
    public JsonElement serialize(Predicate<?>[] src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        for (Predicate<?> predicate : src) {
            jsonArray.add(serializePredicate(predicate));
        }
        return jsonArray;
    }

    private JsonObject serializePredicate(Predicate<?> predicate) {
        JsonObject jsonObject = new JsonObject();
        if (predicate instanceof KilledByPlayerPredicate) {
            jsonObject.addProperty("type", "pixel_quest:killed_by_player");
        } else if (predicate instanceof InRangePredicate(Integer minX, Integer maxX, Integer minY, Integer maxY)) {
            jsonObject.addProperty("type", "pixel_quest:in_range");
            if (minX != null) jsonObject.addProperty("minX", minX);
            if (maxX != null) jsonObject.addProperty("maxX", maxX);
            if (minY != null) jsonObject.addProperty("minY", minY);
            if (maxY != null) jsonObject.addProperty("maxY", maxY);
        } else {
            logger.error("Unknown Predicate type: %s", predicate);
        }
        return jsonObject;
    }

    @Override
    public Predicate<?>[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        logger.warn("Starting deserialization of Predicate array");
        List<Predicate<?>> predicates = new ArrayList<>();

        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                predicates.add(deserializePredicate(element));
            }
        } else {
            predicates.add(deserializePredicate(json));
        }
        logger.warn("Completed deserialization of Predicate array");
        return predicates.toArray(new Predicate<?>[0]);
    }

    private Predicate<?> deserializePredicate(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeElement = jsonObject.get("type");

        if (typeElement == null || typeElement.isJsonNull()) {
            logger.error("Missing or null type field in Predicate JSON");
            return entity -> true;
        }

        String type = typeElement.getAsString();
        if ("pixel_quest:killed_by_player".equals(type)) {
            return new KilledByPlayerPredicate();
        } else if ("pixel_quest:in_range".equals(type)) {
            Integer minX = jsonObject.has("minX") ? jsonObject.get("minX").getAsInt() : null;
            Integer maxX = jsonObject.has("maxX") ? jsonObject.get("maxX").getAsInt() : null;
            Integer minY = jsonObject.has("minY") ? jsonObject.get("minY").getAsInt() : null;
            Integer maxY = jsonObject.has("maxY") ? jsonObject.get("maxY").getAsInt() : null;
            return new InRangePredicate(minX, maxX, minY, maxY);
        } else {
            logger.error("Unknown Predicate type: " + type);
            return entity -> false;
        }
    }
}
