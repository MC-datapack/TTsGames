package dev.TTs.lang;

import dev.TTs.resources.Configs;
import dev.TTs.resources.Json.Text;
import dev.TTs.resources.Json.formats.SoundJSONFormat;
import dev.TTs.swing.TSubtitles;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static dev.TTs.TTsGames.Main.*;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class SoundString {
    private final String jsonPath;
    private String soundPath;
    private String soundKey;
    public static FloatControl volumeControl;
    private TSubtitles subtitles;

    public SoundString(String path, int game) {
        SoundJSONFormat format = jsonReader.readSoundJsonFile(path);
        this.jsonPath = path;
        Timer Timer = new Timer();
        Timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (translations != null) {
                    File defaultF = new File(format.getFiles("English"));
                    String Game;
                    switch (game) {
                        case 0 -> Game = "common";
                        case 1 -> Game = "animal_master";
                        case 2 -> Game = "detective_eagle";
                        case 3 -> Game = "detective_thunder";
                        case 4 -> Game = "pixel_quest";
                        default -> Game = "error";
                    }
                    soundKey = Text.translatable(String.format("sound.%s.%s", Game, defaultF.getName()));
                    soundPath = "/" + jsonReader.readSoundJsonFile(path).getFiles(configLoader.get(Configs.LANGUAGE));
                    Timer.cancel();
                }
            }
        }, 0, 1);
    }

    @Override
    public String toString() {
        return "JSON Path: " + jsonPath + " , Sound File Path: " + soundPath;
    }

    public String getSoundKey() {
        return soundKey;
    }

    public URL toURL() {
        URL resource = SoundString.class.getResource(this.soundPath);
        if (resource == null) {
            logger.error("Sound does not exist: " + this);
            return null;
        }
        return resource;
    }

    public void addSubtitles(TSubtitles subtitles) {
        if (subtitles != null) {
            this.subtitles = subtitles;
        }
    }

    public long getLengthMilliSeconds() {
        try {
            URL url = this.toURL();
            if (url == null) {
                throw new IllegalArgumentException("File not found: " + Objects.requireNonNull(this.toURL()));
            }
            AudioInputStream aud = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(aud);
            aud.close();
            return clip.getMicrosecondLength() / 1000L;
        } catch (UnsupportedAudioFileException e) {
            logger.error("Unsupported audio file format for: " + this.soundPath, e);
            return 0L;
        } catch (IOException e) {
            logger.error("I/O error while playing sound from: " + this.soundPath, e);
            return 0L;
        } catch (LineUnavailableException e) {
            logger.error("Audio line unavailable for: " + this.soundPath, e);
            return 0L;
        }
    }

    public Clip playSound() {
        return playSound(1.0f);
    }

    public Clip playSound(float VolumeMultiplier) {
        try {
            URL url = this.toURL();
            if (url == null) {
                throw new IllegalArgumentException("File not found: " + Objects.requireNonNull(this.toURL()));
            }
            if (subtitles != null) {
                subtitles.setString(soundKey);
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

    public static void beep() {
        Toolkit.getDefaultToolkit().beep();
    }
}
