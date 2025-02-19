package dev.TTs.lang;

import dev.TTs.resources.Json.formats.AnimatedJSONFormat;
import dev.TTs.swing.TImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings("unused")
public final class ImageString implements Serializable {
    private final String path, jsonPath;
    private final AnimatedJSONFormat jsonFormat;
    private final boolean jsonFileExists;
    private BufferedImage image;

    public ImageString(String path) {
        this.path = path;
        this.jsonPath = path + ".json";
        this.jsonFormat = jsonReader.readAnimatedJsonFile(this.jsonPath);
        this.jsonFileExists = this.jsonFormat != null;
    }

    public static BufferedImage loadImage(String path) {
        try (InputStream inputStream = ImageString.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            } else {
                logger.warn("Did not find texture: %s", path);
                return noTexture;
            }
        } catch (IOException e) {
            logger.error("Did not find No Texture File: %s", e);
            return noTexture;
        }
    }

    public BufferedImage toImage() {
        if (image == null) {
            image = ImageCaches.getImage(path);
        }
        return image;
    }

    @Override
    public String toString() {
        return "Image Path: " + path + " , Json Path" + jsonPath + " , Animated Json File exists: " + jsonFileExists;
    }

    public URL toURL() {
        URL resource = ImageString.class.getResource("/" + path);
        if (resource == null) {
            return ImageString.class.getResource("/" + jsonReader.MainJSON.getNoTextureFile());
        }
        return resource;
    }

    public Icon toIcon() {
        return ImageCaches.getIcon(path);
    }

    public TImage toTImage() {
        return new TImage(this);
    }

    public ImageString[] getStrings() {
        if (jsonFormat == null) {
            return new ImageString[]{this};
        }
        try {
            return jsonFormat.getPaths();
        } catch (Exception e) {
            logger.warn("Error getting strings for path: %s: %s", this.jsonPath, e);
            return new ImageString[0];
        }
    }

    public int getDelay() {
        try {
            return jsonFormat.getDelay();
        } catch (Exception e) {
            logger.warn("Error getting Delay for path: %s: %s", this.jsonPath, e);
            return 100000;
        }
    }

    public boolean getRepeat() {
        try {
            return jsonFormat.getRepeat();
        } catch (Exception e) {
            logger.warn("Error getting Repeat setting for path: %s: %s", this.jsonPath, e);
            return true;
        }
    }

    public boolean getBlend() {
        try {
            return jsonFormat.getBlend();
        } catch (Exception e) {
            logger.warn("Error getting Repeat setting for path: %s: %s", this.jsonPath, e);
            return true;
        }
    }

    public BufferedImage tintImage(Color tintColor) {
        return tintImage(tintColor, 0.5F);
    }

    public BufferedImage tintImage(Color tintColor, float opacity) {
        if (tintColor == null || tintColor.getAlpha() == 0 || opacity <= 0 || opacity > 1) {
            return this.toImage();
        }

        return ImageCaches.getTintedImage(this.path, tintColor, opacity);
    }
}
