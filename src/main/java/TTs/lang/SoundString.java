package TTs.lang;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static TTs.TTsGames.Main.Audio.setVolume;
import static TTs.TTsGames.Main.*;

@SuppressWarnings("unused")
public final class SoundString {
    private final String path;

    public SoundString(String path) {
        this.path = "/" + path;
    }

    @Override
    public String toString() {
        return path;
    }

    public static SoundString[] parseSoundsString(String[] paths) {
        return Arrays.stream(paths)
                .map(SoundString::new)
                .toArray(SoundString[]::new);
    }

    public URL toURL() {
        URL resource = SoundString.class.getResource(this.toString());
        if (resource == null) {
            logger.error("Sound is null: " + this.toString());
            return null;
        }
        return resource;
    }

    public Clip playSound() {
        return playSound(1.0f);
    }

    public Clip playSound(float VolumeMultiplier) {
        try {
            URL url = this.toURL();
            if (url == null) {
                throw new IllegalArgumentException("File not found: " + this.toURL().toString());
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
            logger.error("Unsupported audio file format for: " + this.path, e);
        } catch (IOException e) {
            logger.error("I/O error while playing sound from: " + this.path, e);
        } catch (LineUnavailableException e) {
            logger.error("Audio line unavailable for: " + this.path, e);
        }
        return null;
    }
}
