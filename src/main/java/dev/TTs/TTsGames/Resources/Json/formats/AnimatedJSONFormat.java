package dev.TTs.TTsGames.Resources.Json.formats;

import dev.TTs.lang.ImageString;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class AnimatedJSONFormat {
    @Nullable
    private String base_path;
    @Nullable
    private String file_format;
    private List<String> paths;
    private int delay;
    private boolean repeat;

    public List<ImageString> getPaths() {
        List<ImageString> newPaths = new ArrayList<>();
        for (String imageString : paths) {
            if (base_path == null) {
                newPaths.add(new ImageString(imageString + file_format));
            }
            if (file_format == null) {
                newPaths.add(new ImageString( base_path + "/" + imageString));
            } else {
                newPaths.add(new ImageString( base_path + "/" + imageString + file_format));
            }
        }
        return newPaths;
    }
    protected List<String> getMainPaths() {return paths;}
    public int getDelay() {return delay;}
    public boolean getRepeat() {return repeat;}
}
