package dev.TTs.TTsGames.Resources.Json;

import dev.TTs.TTsGames.Resources.Json.formats.AnimatedJSONFormat;
import dev.TTs.TTsGames.Resources.Json.formats.ColorJSONFormat;
import dev.TTs.TTsGames.Resources.Json.formats.TTsGamesJSONFormat;
import dev.TTs.lang.ImageString;
import dev.TTs.lang.SoundString;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

import static dev.TTs.TTsGames.Main.logger;

public class JsonReader {
    public final TTsGamesJSONFormat mainJSON;
    public final ColorJSONFormat colorJSON;
    ColorJSONFormat.ColorRepresentation[] colorReps;
    public JsonReader(String TTsGamesJsonPath) {
        mainJSON = readJsonFile(TTsGamesJsonPath + "TTsGames.json");
        colorJSON = readColorJsonFile(mainJSON.getData()[1][0]);
    }

    private static final Gson gson = new Gson();
    private static final Type MAP_TYPE = new TypeToken<Map<String, String>>() {}.getType();
    private static final Type VERSION_TYPE = new TypeToken<TTsGamesJSONFormat>() {}.getType();
    private static final Type ANIMATED_TYPE = new TypeToken<AnimatedJSONFormat>() {}.getType();

    public Map<String, String> TranslationJsonFile(String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: " + filePath);
                return null;
            }

            return gson.fromJson(reader, MAP_TYPE);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: ", e);
            return null;
        }
    }

    public String[] UnallowedUsernames() {
        return ValuesStandard("values", mainJSON.getData()[0][0], String[].class);
    }
    public boolean[] AMCorrect() {
        return ValuesStandard("values", mainJSON.getData()[1][1], boolean[].class);
    }
    public int[] AMSelectedAnimal() {
        return ValuesStandard("values", mainJSON.getData()[1][2], int[].class);
    }

    public String[] TTsTranslationKeys(String key) {
        return ValuesStandard(key, mainJSON.getData()[0][1], String[].class);
    }

    public String[] AMTranslationKeys(String key) {
        return ValuesStandard(key, mainJSON.getData()[1][3], String[].class);
    }

    public String[] DATranslationKeys(String key) {
        return ValuesStandard(key, mainJSON.getData()[2][0], String[].class);
    }

    public String[] DTTranslationKeys(String key) {
        return ValuesStandard(key, mainJSON.getData()[3][0], String[].class);
    }

    public Integer AInf(String s) {
        return ValuesStandard(s, mainJSON.getData()[1][4], Integer.class);
    }

    public String AInfS(String s) {
        return ValuesStandard(s, mainJSON.getData()[1][4], String.class);
    }

    public TTsGamesJSONFormat readJsonFile(String filePath) {
        try (InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {
            if (inputStream == null) {
                logger.error("File not found: " + filePath); return null;
            } return gson.fromJson(reader, VERSION_TYPE);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: ", e); return null;
        }
    }

    public AnimatedJSONFormat readAnimatedJsonFile(String filePath) {
        try (InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {
            if (inputStream == null) {
                return null;
            }
            logger.debug("Animation File found: " + filePath);
            return gson.fromJson(reader, ANIMATED_TYPE);
        } catch (IOException e) {
            return null;
        }
    }

    public ColorJSONFormat readColorJsonFile(String filePath) {
        try (InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {
            if (inputStream == null) {
                logger.error("File not found: " + filePath); return null;
            }
            colorReps = gson.fromJson(reader, ColorJSONFormat.ColorRepresentation[].class);
            return gson.fromJson(reader, VERSION_TYPE);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: ", e); return null;
        }
    }


    private <T> T ValuesStandard(String element, String path, Class<T> tClass) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
             InputStreamReader reader = inputStream == null ? null : new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                logger.error("File not found: " + path);
                return null;
            }

            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            JsonElement jsonElement1 = jsonObject.get(element);
            return gson.fromJson(jsonElement1, tClass);
        } catch (IOException e) {
            logger.error("Failed to read the Json File: ", e);
            return null;
        }
    }

    public TTsGamesJSONFormat getMainJSON() {
        return mainJSON;
    }

    public Color[] getColors() {
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
}
