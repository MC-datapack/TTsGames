package TTs.TTsGames.Resources.Json;

import TTs.TTsGames.Resources.Json.formats.AnimatedJSONFormat;
import TTs.TTsGames.Resources.Json.formats.ColorJSONFormat;
import TTs.TTsGames.Resources.Json.formats.TTsGamesJSONFormat;
import TTs.lang.ImageString;
import TTs.lang.SoundString;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static TTs.TTsGames.Main.logger;

public class JsonReader {
    public final TTsGamesJSONFormat tTsGamesJSONFormat;
    public final ColorJSONFormat colorJSONFormat;
    List<ColorJSONFormat.ColorRepresentation> colorReps;
    public JsonReader() {
        tTsGamesJSONFormat = readJsonFile("TTsGames.json");
        colorJSONFormat = readColorJsonFile(tTsGamesJSONFormat.getAnimal_master_button_colors());
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
        return ValuesStandard("values", UnallowedUsernamePath(), String[].class);
    }
    public boolean[] AMCorrect() {
        return ValuesStandard("values", AMCorrectPath(), boolean[].class);
    }
    public int[] AMSelectedAnimal() {
        return ValuesStandard("values", AMSelectedPath(), int[].class);
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
            Type colorListType = new TypeToken<List<ColorJSONFormat.ColorRepresentation>>(){}.getType();
             colorReps = gson.fromJson(reader, colorListType);
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

    public String[] getVersions() {
        return tTsGamesJSONFormat != null ? tTsGamesJSONFormat.toStringArray() : new String[0];
    }
    public String[] getLanguages() {
        return tTsGamesJSONFormat != null && tTsGamesJSONFormat.getLanguages() != null ? tTsGamesJSONFormat.getLanguages().toArray(new String[0]) : new String[0];
    }
    public String getNoTextureFile() {
        return tTsGamesJSONFormat.getNo_texture_file();
    }
    public ImageString[][] getTextures() {
        if (tTsGamesJSONFormat != null && tTsGamesJSONFormat.getTextures() != null) {
            List<List<String>> animalsList = tTsGamesJSONFormat.getTextures();
            ImageString[][] animalsArray = new ImageString[animalsList.size()][];

            for (int i = 0; i < animalsList.size(); i++) {
                List<String> sublist = animalsList.get(i);
                animalsArray[i] = ImageString.parseImageString(sublist.toArray(new String[0]));
            }
            return animalsArray;
        }
        return new ImageString[0][0];
    }
    public SoundString[][] getSounds() {
        if (tTsGamesJSONFormat != null && tTsGamesJSONFormat.getSounds() != null) {
            List<List<String>> soundList = tTsGamesJSONFormat.getSounds();
            SoundString[][] soundArray = new SoundString[soundList.size()][];

            for (int i = 0; i < soundList.size(); i++) {
                List<String> sublist = soundList.get(i);
                soundArray[i] = SoundString.parseSoundsString(sublist.toArray(new String[0]));
            }
            return soundArray;
        }
        return new SoundString[0][0];
    }

    public Color[] getColors() {
        Color[] colors = new Color[colorReps.size()];
        for (int i = 0; i < colorReps.size(); i++) {
            ColorJSONFormat.ColorRepresentation colorRep = colorReps.get(i);
            if (colorRep.getR() == 0 && colorRep.getG() == 0 && colorRep.getB() == 0) {
                colors[i] = new Color(colorRep.getRGB());
            } else {
                colors[i] = new Color(colorRep.getR(), colorRep.getG(), colorRep.getB());
            }
        }
        return colors;
    }

    public String UnallowedUsernamePath() {
        return tTsGamesJSONFormat.getUnallowedUsernames();
    }

    public String AMCorrectPath() {
        return tTsGamesJSONFormat.getAnimal_master_correct();
    }

    public String AMSelectedPath() {
        return tTsGamesJSONFormat.getAnimal_master_selected();
    }
}
