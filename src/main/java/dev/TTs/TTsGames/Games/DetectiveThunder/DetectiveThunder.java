package dev.TTs.TTsGames.Games.DetectiveThunder;

import dev.TTs.lang.ExitCodes;
import dev.TTs.swing.*;

import javax.swing.*;
import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.*;
import static dev.TTs.resources.Translations.DetectiveThunder;

/**
 * <blockquote><pre>
 * Spinner: BRIAN
 * Detektiv Thunder: WILL
 * Magritte: LILY
 *
 * Sprecher: ALICE
 * </pre></blockquote>
 */

@SuppressWarnings("unused")
public class DetectiveThunder {
    int Score = 0;
    public static boolean showScore = false, startedClose = false;

    public DetectiveThunder(WindowInformation inf) {
        startedVersion = Versions[10];
        SwingUtilities.invokeLater(() -> {
            windows[3] = new TFrame(Games[2] + Versions[10]);

            TButton erklaerung = new TButton(DetectiveThunder[0]);
            //160 Buchstaben max pro Zeile
            TLabel line1 = new TLabel(DetectiveThunder[1]);
            TLabel line2 = new TLabel(DetectiveThunder[2]);
            TLabel line3 = new TLabel(DetectiveThunder[3]);

            TButton start = new TButton(DetectiveThunder[4]);

            TButton[] entscheidungen = new TButton[4];
            entscheidungen[0] = new TButton(ButtonsDT[0]);
            entscheidungen[1] = new TButton(ButtonsDT[1]);
            entscheidungen[2] = new TButton(ButtonsDT[2]);
            entscheidungen[3] = new TButton(ButtonsDT[3]);

            TButton[][] doStandardButtonStuff = {
                    entscheidungen,
                    {erklaerung, start}
            };
            for (TButton[] componentArray : doStandardButtonStuff) {
                for (TButton component : componentArray) {
                    component.setOpaque(false);
                    component.setContentAreaFilled(false);
                    component.setForeground(Color.WHITE);
                    component.setFocusable(false);
                }
            }

            Component[] doStandardLabelStuff = {
                    line1, line2, line3
            };
            for (Component component : doStandardLabelStuff) {
                component.setForeground(Color.WHITE);
                component.setFocusable(false);
            }

            TImage background = Textures[3][0][1].toTImage();

            TImage Wache = Textures[3][0][2].toTImage();
            TImage Kunstgalerie = Textures[3][0][3].toTImage();
            TImage FingerabdruckSuche = Textures[3][0][4].toTImage();


            TImage[] doStandardPanelStuff = {
                    Wache, Kunstgalerie, FingerabdruckSuche
            };

            for (TImage component : doStandardPanelStuff) {
                windows[3].add(component);
                component.setLayout(new BorderLayout());
                component.setSize(1330, 750);
                component.Hide();
            }


            Component[][] setVisibleFalse = {
                    entscheidungen,
                    {line1, line2, line3}
            };
            for (Component[] componentArray : setVisibleFalse) {
                for (Component component : componentArray) {
                    component.setVisible(false);
                }
            }

            TPanel panel = new TPanel();
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            background.add(panel);

            Component[][] addToPanel = {
                    {erklaerung, line1, line2, line3, start},
                    entscheidungen
            };
            for (Component[] componentArray : addToPanel) {
                for (Component component : componentArray) {
                    panel.add(component);
                }
            }

            erklaerung.event( () -> {
                erklaerung.Hide();
                line1.Show();
                line2.Show();
                line3.Show();
            });
            start.event( () -> {
                start.Hide();
                erklaerung.Hide();
                background.Hide();
                line1.Hide();
                line2.Hide();
                line3.Hide();
                Wache.Show();
                Wache.add(panel);
                timer(() -> {
                    Sounds[3][0].playSound();
                    timer(() -> entscheidungen[0].Show(), Sounds[3][0].getLengthMilliSeconds());
                }, 1000);
            });
            entscheidungen[0].event( () -> {
                entscheidungen[0].Hide();
                Wache.Hide();
                Kunstgalerie.Show();
                Kunstgalerie.add(panel);
                Sounds[3][1].playSound();
                timer(() -> {
                    Sounds[3][2].playSound();
                    timer(() -> {
                        Sounds[3][3].playSound();
                        timer(() -> {
                            for (int i = 1; i < 4; i++) {
                                entscheidungen[i].Show();
                            }
                        }, Sounds[3][3].getLengthMilliSeconds());
                    }, Sounds[3][2].getLengthMilliSeconds());
                }, Sounds[3][1].getLengthMilliSeconds());
            });
            entscheidungen[1].event( () -> {
                entscheidungen[1].Hide();
                entscheidungen[2].Hide();
                entscheidungen[3].Hide();
                Kunstgalerie.Hide();
                Wache.Show();
                Wache.add(panel);
                Sounds[3][4].playSound();
                timer(() -> System.exit(ExitCodes.NO_ERROR), Sounds[3][4].getLengthMilliSeconds());
            });
            entscheidungen[3].event( () -> {
                entscheidungen[1].Hide();
                entscheidungen[2].Hide();
                entscheidungen[3].Hide();
                Sounds[3][5].playSound();
                timer(() -> {
                    Sounds[3][6].playSound();
                    timer(() -> System.exit(ExitCodes.NO_ERROR), Sounds[3][6].getLengthMilliSeconds());
                }, Sounds[3][5].getLengthMilliSeconds());
            });
            entscheidungen[2].event( () -> {
                entscheidungen[1].Hide();
                entscheidungen[2].Hide();
                entscheidungen[3].Hide();
                Kunstgalerie.Hide();
                FingerabdruckSuche.Show();
                FingerabdruckSuche.restartAnimation();
                FingerabdruckSuche.add(panel);
            });

            WindowOperations(3, inf, background);
        });
    }
}
