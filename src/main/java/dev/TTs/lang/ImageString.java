package dev.TTs.lang;

import dev.TTs.TTsGames.Resources.Json.formats.AnimatedJSONFormat;
import dev.TTs.swing.AnimatedImagePanel;
import dev.TTs.swing.ImagePanel;

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

    public ImageString(String path) {
        this.path = path;
        this.jsonPath = path + ".json";
        this.jsonFormat = jsonReader.readAnimatedJsonFile(this.jsonPath);
    }

    public static ImageString[] parseImageString(String[] paths) {
        return Arrays.stream(paths)
                .map(ImageString::new)
                .toArray(ImageString[]::new);
    }

    @Override
    public String toString() {
        return this.path;
    }

    public URL toURL() {
        URL resource = ImageString.class.getResource("/" + this.toString());
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

    public ImagePanel toImagePanel() {
        return new ImagePanel(this);
    }

    public AnimatedImagePanel toAnimatedImagePanel() {
        return new AnimatedImagePanel(this);
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
