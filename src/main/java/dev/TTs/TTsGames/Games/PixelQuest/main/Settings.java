package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.lang.TranslatedString;
import dev.TTs.resources.Configs;
import dev.TTs.swing.TButton;
import dev.TTs.swing.TPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static dev.TTs.TTsGames.Main.configLoader;
import static dev.TTs.resources.Translations.PixelQuest;

public class Settings extends TPanel {
    public final TButton VSyncButton, BackToGameButton, SaveWorldButton;
    public boolean VSync;
    public long lastSettings = 1000;

    public final KeyBindingPanel keyBindingPanel;

    public Settings() {
        setOpaqueF();

        this.VSync = configLoader.get(Configs.V_SYNC);

        VSyncButton = new TButton("V Sync");
        VSyncButton.event(() -> {
            VSync = !VSync;
            configLoader.set(Configs.V_SYNC, VSync);
            VSyncButton.setText(TranslatedString.valueOf(VSync));
            VSyncButton.repaint();
            PixelQuestGame.game.repaint();
        });

        VSyncButton.setFocusableF();
        VSyncButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                VSyncButton.setText(TranslatedString.valueOf(VSync));
                VSyncButton.repaint();
                PixelQuestGame.game.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                VSyncButton.setText("V Sync");
                VSyncButton.repaint();
                PixelQuestGame.game.repaint();
            }
        });
        VSyncButton.setPreferredSize(new Dimension(300, 30));

        SaveWorldButton = new TButton(PixelQuest[7]);
        SaveWorldButton.event(() -> {
            if (WorldSaving.gameName != null && !WorldSaving.gameName.trim().isEmpty()) {
                WorldSaving.saveGame();
                PixelQuestGame.game.unloadGame();
            }
        });

        SaveWorldButton.setFocusableF();
        SaveWorldButton.setPreferredSize(new Dimension(300, 30));

        BackToGameButton = new TButton(PixelQuest[5]);
        BackToGameButton.event(() -> {
            PixelQuestGame.game.repaint();
            PixelQuestGame.game.showSettings = false;
            lastSettings = System.currentTimeMillis();
            PixelQuestGame.game.start();
            setVisible(false);
        });
        BackToGameButton.setFocusableF();
        BackToGameButton.setPreferredSize(new Dimension(300, 30));

        keyBindingPanel = new KeyBindingPanel();
        keyBindingPanel.setFocusableF();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(keyBindingPanel);
        add(VSyncButton);
        add(SaveWorldButton);
        add(BackToGameButton);

        setVisible(false);
    }
}
