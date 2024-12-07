package dev.TTs.lang;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings("unused")
public final class SoundString {
    private final String jsonPath;
    private String soundPath;
    public static FloatControl volumeControl;

    public SoundString(String path) {
        this.jsonPath = path;
        Timer Timer = new Timer();
        Timer.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                if (configLoader != null) {
                    timer(() -> {
                        soundPath = "/" + jsonReader.readSoundJsonFile(path).getFiles(configLoader.getLanguage());
                    }, 200);
                    Timer.cancel();
                }
            }
        }, 0, 1);
    }

    @Override
    public String toString() {
        return "JSON Path: " + jsonPath + " , Sound File Path: " + soundPath;
    }

    @NotNull
    public static SoundString[] parseSoundsString(String[] paths) {
        return Arrays.stream(paths)
                .map(SoundString::new)
                .toArray(SoundString[]::new);
    }

    @Nullable
    public URL toURL() {
        URL resource = SoundString.class.getResource(this.soundPath);
        if (resource == null) {
            logger.error("Sound does not exist: " + this);
            return null;
        }
        return resource;
    }

    @Nullable
    public Clip playSound() {
        return playSound(1.0f);
    }

    @Nullable
    public Clip playSound(float VolumeMultiplier) {
        try {
            URL url = this.toURL();
            if (url == null) {
                throw new IllegalArgumentException("File not found: " + Objects.requireNonNull(this.toURL()));
            }
            AudioInputStream aud = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(aud);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(volume * VolumeMultiplier);
            clip.start();
            aud.close(); // Ensure the input stream is closed
            return clip;
        } catch (UnsupportedAudioFileException e) {
            logger.error("Unsupported audio file format for: " + this.soundPath, e);
        } catch (IOException e) {
            logger.error("I/O error while playing sound from: " + this.soundPath, e);
        } catch (LineUnavailableException e) {
            logger.error("Audio line unavailable for: " + this.soundPath, e);
        }
        return null;
    }

    public static void setVolume(float volume) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            volume = min + (volume / 100) * (max - min);
            if (volumeControl != null) volumeControl.setValue(Math.min(Math.max(volume, min), max));
        }
    }
}
