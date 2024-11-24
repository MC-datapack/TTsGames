package TTs.TTsGames.Games.DetektivThunder;

import TTs.TTsGames.Logger.TTsLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static TTs.TTsGames.Main.*;
import static TTs.TTsGames.Games.DetektivThunder.DetektivThunder.*;
import static TTs.TTsGames.Resources.Translations.DetektivThunder;

public class Close {
    public Close(JFrame DetektivThunderWindow, Point location) {
        location = new Point(location.x + 500, location.y + 250);
        windows[4] = new JFrame(DetektivThunder[6]);

        JButton close = new JButton(DetektivThunder[7]);
        JButton score = new JButton(DetektivThunder[8]);

        JPanel panel = new JPanel();
        panel.setSize(240,120);

        panel.add(close);
        panel.add(score);


        close.addActionListener(e -> {
            logger.setInstance(TTsLogger.Instance.TTS_GAMES);
            DetektivThunderWindow.setVisible(false);
            windows[4].setVisible(false);
            startedClose = false;
            MainWindow = true;
            started = null;
        });
        score.addActionListener(e -> {
            showScore = true;
            windows[4].setVisible(false);
            startedClose = false;
        });

        WindowOperations(4, false, new Dimension(240, 120), location, Textures[3][0], panel);
    }
}
