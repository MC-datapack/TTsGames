package dev.TTs.TTsGames.Games.AnimalMaster;

import dev.TTs.swing.WindowInformation;
import dev.TTs.swing.TImage;
import dev.TTs.swing.TFrame;

import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.OtherButtons;

public class Big {
    public Big(int picture) {
        windows[5] = new TFrame(OtherButtons[4]);

        TImage image = Textures[1][0][picture].toTImage();

        WindowOperations(5, new WindowInformation(false, new Point(windows[1].getLocation().x - 700, windows[1].getLocation().y - 400),
                new Dimension(Textures[1][0][picture].toIcon().getIconWidth(), Textures[1][0][picture].toIcon().getIconHeight()), Textures[1][0][37]), image);
    }
}
