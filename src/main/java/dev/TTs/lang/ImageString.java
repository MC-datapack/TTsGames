package dev.TTs.lang;

import dev.TTs.TTsGames.Resources.Json.formats.AnimatedJSONFormat;
import dev.TTs.swing.TAnimation;
import dev.TTs.swing.TImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings("unused")
public final class ImageString {
    private final String path, jsonPath;
    private final AnimatedJSONFormat jsonFormat;
    private final boolean jsonFileExists;

    public ImageString(String path) {
        this.path = path;
        this.jsonPath = path + ".json";
        this.jsonFormat = jsonReader.readAnimatedJsonFile(this.jsonPath);
        this.jsonFileExists = this.jsonFormat != null;
    }

    public static ImageString[] parseImageString(String[] paths) {
        return Arrays.stream(paths)
                .map(ImageString::new)
                .toArray(ImageString[]::new);
    }

    @Override
    public String toString() {
        return "Image Path: " + path + " , Json Path" +  jsonPath + " , Animated Json File exists: " + jsonFileExists;
    }

    public URL toURL() {
        URL resource = ImageString.class.getResource("/" + path);
        if (resource == null) {
            return ImageString.class.getResource("/" + jsonReader.MainJSON.getNoTextureFile());
        }
        return resource;
    }

    public Icon toIcon() {
        return new ImageIcon(this.toURL());
    }

    public Image toImage() {
        try {
            InputStream inputStream = ImageString.class.getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            } else {
                logger.warn("Did not find texture " + path);
                return noTexture;
            }
        } catch (IOException e) {
            logger.error("Did not find No Texture File", e);
            return null;
        }
    }

    public TImage toTImage() {
        return new TImage(this);
    }

    public TAnimation toTAnimation() {
        return new TAnimation(this);
    }

    public List<ImageString> getStrings() {
        try {
            return Collections.unmodifiableList(jsonFormat.getPaths());
        } catch (Exception e) {
            logger.warn("Error getting strings for path: " + this.jsonPath, e);
            return Collections.emptyList();
        }
    }

    public int getDelay() {
        try {
            return jsonFormat.getDelay();
        } catch (Exception e) {
            logger.warn("Error getting Delay for path: " + this.jsonPath, e);
            return 1;
        }
    }
    public boolean getRepeat() {
        try {
            return jsonFormat.getRepeat();
        } catch (Exception e) {
            logger.warn("Error getting Delay for path: " + this.jsonPath, e);
            return true;
        }
    }
}
