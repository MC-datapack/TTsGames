package dev.TTs.resources.Json.formats;

import dev.TTs.lang.ImageString;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"unused", "MismatchedReadAndWriteOfArray"})
public class AnimatedJSONFormat {
    @Nullable
    private String base_path;
    @Nullable
    private String file_format;
    private String[] paths;
    private int delay;
    private boolean repeat;
    private boolean blend;

    public ImageString[] getPaths() {
        ImageString[] newPaths = new ImageString[paths.length];
        for (int i = 0; i < paths.length; i++) {
            if (base_path == null) {
                newPaths[i] = (new ImageString(paths[i] + file_format));
            }
            if (file_format == null) {
                newPaths[i] = (new ImageString( base_path + "/" + paths[i]));
            } else {
                newPaths[i] = (new ImageString( base_path + "/" + paths[i] + file_format));
            }
        }
        return newPaths;
    }
    public int getDelay() {return delay;}
    public boolean getRepeat() {return repeat;}
    public boolean getBlend() {return blend;}
}
