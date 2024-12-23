package dev.TTs.TTsGames.Games.PixelQuest;

import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.swing.TFrame;
import dev.TTs.swing.WindowInformation;

import static dev.TTs.TTsGames.Main.WindowOperations;
import static dev.TTs.TTsGames.Main.windows;

public class PixelQuest {
    public PixelQuest(WindowInformation information) {
        windows[7] = new TFrame(information.title());

        WindowOperations(7, information, PixelQuestGame.game);
        PixelQuestGame.game.start();
    }
}
