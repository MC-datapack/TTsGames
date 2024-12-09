package dev.TTs.TTsGames.Resources.Json;

import dev.TTs.TTsGames.Resources.Json.formats.AnimatedJSONFormat;
import dev.TTs.TTsGames.Resources.Json.formats.ColorJSONFormat;
import dev.TTs.TTsGames.Resources.Json.formats.SoundJSONFormat;
import dev.TTs.TTsGames.Resources.Json.formats.TTsGamesJSONFormat;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

import static dev.TTs.TTsGames.Main.logger;

public class JsonReader {
    public final TTsGamesJSONFormat MainJSON;
    public final ColorJSONFormat colorJSON;
    ColorJSONFormat.ColorRepresentation[] colorReps;
    public JsonReader(String TTsGamesJsonPath) {
        MainJSON = readTTsJsonFile(TTsGamesJsonPath + "TTsGames.json");
        colorJSON = readColorJsonFile(MainJSON.getData()[1][0]);
    }

    private static final Gson gson = new Gson();

    public String[] UnallowedUsernames() {
        return ValuesStandard("values", MainJSON.getData()[0][0], String[].class);
    }
    public boolean[] AMCorrect() {
        return ValuesStandard("values", MainJSON.getData()[1][1], boolean[].class);
    }
    public int[] AMSelectedAnimal() {
        return ValuesStandard("values", MainJSON.getData()[1][2], int[].class);
    }

    public String[] TTsTranslationKeys(String key) {
        return ValuesStandard(key, MainJSON.getData()[0][1], String[].class);
    }

    public String[] AMTranslationKeys(String key) {
        return ValuesStandard(key, MainJSON.getData()[1][3], String[].class);
    }

    public String[] DATranslationKeys(String key) {
        return ValuesStandard(key, MainJSON.getData()[2][0], String[].class);
    }

    public String[] DTTranslationKeys(String key) {
        return ValuesStandard(key, MainJSON.getData()[3][0], String[].class);
    }

    public Integer AInf(String s) {
        return ValuesStandard(s, MainJSON.getData()[1][4], Integer.class);
    }

    public String AInfS(String s) {
        return ValuesStandard(s, MainJSON.getData()[1][4], String.class);
    }

    public Color[] AMColors() {
        Color[] colors = new Color[colorReps.length];
        for (int i = 0; i < colorReps.length; i++) {
            ColorJSONFormat.ColorRepresentation colorRep = colorReps[i];
            if (colorRep.getR() == 0 && colorRep.getG() == 0 && colorRep.getB() == 0) {
                colors[i] = new Color(colorRep.getRGB());
            } else {
                colors[i] = new Color(colorRep.getR(), colorRep.getG(), colorRep.getB());
            }
        }
        return colors;
    }



    private <T> T ValuesStandard(String element, String path, Class<T> tClass) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: %s", path);
                return null;
            }

            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement jsonElement1 = jsonObject.get(element);

            return gson.fromJson(jsonElement1, tClass);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: %s", e);
            return null;
        }
    }

    private <T> T readJsonFile(String filePath, Class<T> tClass) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: %s", filePath);
                return null;
            }

            return gson.fromJson(reader, tClass);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: %s", e);
            return null;
        }
    }

    private TTsGamesJSONFormat readTTsJsonFile(String filePath) {
        return readJsonFile(filePath, TTsGamesJSONFormat.class);
    }

    public AnimatedJSONFormat readAnimatedJsonFile(String filePath) {
        AnimatedJSONFormat result = readJsonFile(filePath, AnimatedJSONFormat.class);
        if (result != null) {
            logger.debug("Animation File found: %s", filePath);
        }
        return result;
    }

    public SoundJSONFormat readSoundJsonFile(String filePath) {
        return readJsonFile(filePath, SoundJSONFormat.class);
    }

    private ColorJSONFormat readColorJsonFile(String filePath) {
        ColorJSONFormat colorJsonFormat = readJsonFile(filePath, ColorJSONFormat.class);
        if (colorJsonFormat != null) {
            colorReps = gson.fromJson(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath))),
                    ColorJSONFormat.ColorRepresentation[].class);
        }
        return colorJsonFormat;
    }
    protected Map<String, String> readTranslationJsonFile(String filePath) {
        Type mapType = new TypeToken<Map<String, String>>() {}.getType();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: %s", filePath);
                return null;
            }

            return gson.fromJson(reader, mapType);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: %s", e);
            return null;
        }
    }
}
