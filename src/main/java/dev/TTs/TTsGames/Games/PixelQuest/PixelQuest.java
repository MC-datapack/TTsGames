package dev.TTs.TTsGames.Games.PixelQuest;

import dev.TTs.TTsGames.Games.PixelQuest.main.KeyBindings;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.resources.Configs;
import dev.TTs.swing.TFrame;
import dev.TTs.swing.WindowInformation;

import java.awt.*;

import static dev.TTs.TTsGames.Main.*;

public class PixelQuest {
    public PixelQuest(WindowInformation information) {
        KeyBindings.loadBindings();

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        DisplayMode displayMode = graphicsDevice.getDisplayMode();

        if (configLoader.get(Configs.FULLSCREEN)) {
            Rectangle screenBounds;
            Window fullScreenWindow = graphicsDevice.getFullScreenWindow();

            if (fullScreenWindow != null) {
                screenBounds = fullScreenWindow.getBounds();
            } else {
                screenBounds = graphicsEnvironment.getMaximumWindowBounds();
            }

            PixelQuestGame.game = new PixelQuestGame(displayMode.getRefreshRate(), screenBounds, graphicsDevice);
        } else {
            PixelQuestGame.game = new PixelQuestGame(displayMode.getRefreshRate(), new Rectangle(250, 250, information.size().width, information.size().height - 50),
                    graphicsDevice);
        }

        windows[7] = new TFrame(information.title());

        WindowOperations(7, information, PixelQuestGame.game);

        if (configLoader.get(Configs.FULLSCREEN)) {
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .setFullScreenWindow(windows[7]);
        }
    }
}
