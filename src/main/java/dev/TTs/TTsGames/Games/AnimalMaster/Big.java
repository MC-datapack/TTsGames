package dev.TTs.TTsGames.Games.AnimalMaster;

import dev.TTs.swing.ImagePanel;

import javax.swing.*;

import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.TTsGames.Resources.Translations.OtherButtons;

public final class Big {
    public Big(int picture) {
        windows[5] = new JFrame(OtherButtons[4]);

        ImagePanel image = Textures[1][picture].toImagePanel();

        WindowOperations(5, false, new Dimension(Textures[1][picture].toIcon().getIconWidth(), Textures[1][picture].toIcon().getIconHeight()),
                new Point(windows[1].getLocation().x - 700, windows[1].getLocation().y - 400), Textures[1][37], image);
    }
}
