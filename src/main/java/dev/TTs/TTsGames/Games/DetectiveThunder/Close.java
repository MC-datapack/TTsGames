package dev.TTs.TTsGames.Games.DetectiveThunder;

import dev.TTs.lang.Instance;
import dev.TTs.swing.*;

import javax.swing.*;
import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.TTsGames.Games.DetectiveThunder.DetectiveThunder.*;
import static dev.TTs.resources.Translations.DetectiveThunder;

public class Close {
    public Close(TFrame DetektivThunderWindow, Point location) {
        location = new Point(location.x + 500, location.y + 250);
        windows[4] = new TFrame(DetectiveThunder[5]);

        TButton close = new TButton(DetectiveThunder[6]);
        TButton score = new TButton(DetectiveThunder[7]);

        TPanel panel = new TPanel();
        panel.setSize(240,120);

        panel.add(close);
        panel.add(score);


        close.event( () -> {
            logger.setInstance(Instance.TTS_GAMES);
            DetektivThunderWindow.Hide();
            windows[4].dispose();
            startedClose = false;
            MainWindow = true;
            started = null;
        });
        score.event( () -> {
            showScore = true;
            startedClose = false;
        });

        WindowOperations(4, new WindowInformation(false, location, new Dimension(240, 120), Textures[3][0]), panel);
    }
}
