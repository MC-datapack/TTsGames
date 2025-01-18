package dev.TTs.TTsGames.Games.PixelQuest;

import dev.TTs.TTsGames.Games.PixelQuest.main.KeyBindings;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.swing.TFrame;
import dev.TTs.swing.WindowInformation;

import java.awt.*;

import static dev.TTs.TTsGames.Main.*;

public class PixelQuest {
    public PixelQuest(WindowInformation information) {
        KeyBindings.loadBindings();
        if (PixelQuestGame.game == null) {
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
            DisplayMode displayMode = graphicsDevice.getDisplayMode();

            PixelQuestGame.game = new PixelQuestGame(displayMode.getRefreshRate());
        }
        windows[7] = new TFrame(information.title());

        WindowOperations(7, information, PixelQuestGame.game);
    }
}
