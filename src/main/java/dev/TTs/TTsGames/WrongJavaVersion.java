package dev.TTs.TTsGames;

import dev.TTs.swing.TFrame;
import dev.TTs.swing.TLabel;
import dev.TTs.swing.WindowInformation;

import javax.swing.*;
import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.WrongJava;

public class WrongJavaVersion {
    public WrongJavaVersion() {
        windows[6] = new TFrame(WrongJava[0]);

        TLabel text = new TLabel(WrongJava[1] + javaVersion);

        WindowOperations(6, new WindowInformation(false, new Point(500, 500), new Dimension(300, 100), Textures[0][0][3],
                WindowConstants.EXIT_ON_CLOSE), text);
    }
}
