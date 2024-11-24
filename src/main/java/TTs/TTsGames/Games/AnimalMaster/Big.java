package TTs.TTsGames.Games.AnimalMaster;

import TTs.TTsGames.Resources.Text;
import TTs.swing.ImagePanel;

import javax.swing.*;

import java.awt.*;

import static TTs.TTsGames.Main.*;

public class Big {
    public Big(int picture, Point location) {
        location = new Point(location.x - 700, location.y - 400);
        windows[5] = new JFrame(Text.translatable("animal_master.window.big"));

        ImagePanel image = Textures[1][picture].toImagePanel();

        WindowOperations(5, false, new Dimension(Textures[1][picture].toIcon().getIconWidth(), Textures[1][picture].toIcon().getIconHeight()),
                location, Textures[1][37], image);
    }
}
