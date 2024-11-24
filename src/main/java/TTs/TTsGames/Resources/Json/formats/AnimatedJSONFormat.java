package TTs.TTsGames.Resources.Json.formats;

import TTs.lang.ImageString;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AnimatedJSONFormat {
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
                newPaths.add(new ImageString( base_path + File.separator + imageString));
            } else {
                newPaths.add(new ImageString( base_path + File.separator + imageString + file_format));
            }
        }
        return newPaths;
    }
    protected List<String> getMainPaths() {return paths;}
    public int getDelay() {return delay;}
    public boolean getRepeat() {return repeat;}
}
