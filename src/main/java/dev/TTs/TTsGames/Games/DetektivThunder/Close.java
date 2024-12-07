package dev.TTs.TTsGames.Games.DetektivThunder;

import dev.TTs.lang.Instance;
import dev.TTs.lang.TTsLogger;

import javax.swing.*;
import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.TTsGames.Games.DetektivThunder.DetektivThunder.*;
import static dev.TTs.TTsGames.Resources.Translations.DetektivThunder;

public final class Close {
    public Close(JFrame DetektivThunderWindow, Point location) {
        location = new Point(location.x + 500, location.y + 250);
        windows[4] = new JFrame(DetektivThunder[5]);

        JButton close = new JButton(DetektivThunder[6]);
        JButton score = new JButton(DetektivThunder[7]);

        JPanel panel = new JPanel();
        panel.setSize(240,120);

        panel.add(close);
        panel.add(score);


        close.addActionListener(e -> {
            logger.setInstance(Instance.TTS_GAMES);
            DetektivThunderWindow.setVisible(false);
            windows[4].dispose();
            startedClose = false;
            MainWindow = true;
            started = null;
        });
        score.addActionListener(e -> {
            showScore = true;
            startedClose = false;
        });

        WindowOperations(4, false, new Dimension(240, 120), location, Textures[3][0], panel);
    }
}
