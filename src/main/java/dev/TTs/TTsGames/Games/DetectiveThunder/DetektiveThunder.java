package dev.TTs.TTsGames.Games.DetectiveThunder;

import dev.TTs.lang.ExitCodes;
import dev.TTs.swing.AnimatedImagePanel;
import dev.TTs.swing.ImagePanel;

import javax.swing.*;
import java.awt.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.TTsGames.Resources.Translations.*;
import static dev.TTs.TTsGames.Resources.Translations.DetectiveThunder;

/**
 * <blockquote><pre>
 * Spinner: BRIAN
 * Detektiv Thunder: WILL
 * Magritte: LILY
 *
 * Sprecher: ALICE
 * </pre>
 */

@SuppressWarnings("unused")
public final class DetektiveThunder {
    int Score = 0;
    public static boolean showScore = false, startedClose = false;

    public DetektiveThunder(boolean resizable, Point location, Dimension size) {
        startedVersion = Versions[6];
        SwingUtilities.invokeLater(() -> {
            windows[3] = new JFrame(Games[2] + Versions[6]);

            JButton erklaerung = new JButton(DetectiveThunder[0]);
            //160 Buchstaben max pro Zeile
            JLabel line1 = new JLabel(DetectiveThunder[1]);
            JLabel line2 = new JLabel(DetectiveThunder[2]);
            JLabel line3 = new JLabel(DetectiveThunder[3]);

            JButton start = new JButton(DetectiveThunder[4]);

            JButton[] entscheidungen = new JButton[4];
            entscheidungen[0] = new JButton(ButtonsDT[0]);
            entscheidungen[1] = new JButton(ButtonsDT[1]);
            entscheidungen[2] = new JButton(ButtonsDT[2]);
            entscheidungen[3] = new JButton(ButtonsDT[3]);

            Component[][] doStandardButtonStuff = {
                    entscheidungen,
                    {erklaerung, start}
            };
            for (Component[] componentArray : doStandardButtonStuff) {
                for (Component component : componentArray) {
                    ((JButton) component).setOpaque(false);
                    ((JButton) component).setContentAreaFilled(false);
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

            ImagePanel background = Textures[3][1].toImagePanel();

            AnimatedImagePanel Wache = Textures[3][2].toAnimatedImagePanel();
            ImagePanel Kunstgalerie = Textures[3][3].toImagePanel();
            AnimatedImagePanel FingerabdruckSuche = Textures[3][4].toAnimatedImagePanel();


            Component[] doStandardPanelStuff = {
                    Wache, Kunstgalerie, FingerabdruckSuche
            };

            for (Component component : doStandardPanelStuff) {
                windows[3].add(component);
                ((Container) component).setLayout(new BorderLayout());
                component.setSize(1330, 750);
                component.setVisible(false);
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

            JPanel panel = new JPanel();
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

            erklaerung.addActionListener(e -> {
                erklaerung.setVisible(false);
                line1.setVisible(true);
                line2.setVisible(true);
                line3.setVisible(true);
            });
            start.addActionListener(e -> {
                start.setVisible(false);
                erklaerung.setVisible(false);
                background.setVisible(false);
                line1.setVisible(false);
                line2.setVisible(false);
                line3.setVisible(false);
                Wache.setVisible(true);
                Wache.add(panel);
                timer(() -> {
                    Sounds[3][0].playSound();
                    timer(() -> entscheidungen[0].setVisible(true), 9500);
                }, 1000);
            });
            entscheidungen[0].addActionListener(e -> {
                entscheidungen[0].setVisible(false);
                Wache.setVisible(false);
                Kunstgalerie.setVisible(true);
                Kunstgalerie.add(panel);
                Sounds[3][1].playSound();
                timer(() -> {
                    Sounds[3][2].playSound();
                    timer(() -> {
                        Sounds[3][3].playSound();
                        timer(() -> {
                            for (int i = 1; i < 4; i++) {
                                entscheidungen[i].setVisible(true);
                            }
                        }, 3150);
                    }, 14700);
                }, 4500);
            });
            entscheidungen[1].addActionListener(e -> {
                entscheidungen[1].setVisible(false);
                entscheidungen[2].setVisible(false);
                entscheidungen[3].setVisible(false);
                Kunstgalerie.setVisible(false);
                Wache.setVisible(true);
                Wache.add(panel);
                Sounds[3][4].playSound();
                timer(() -> System.exit(ExitCodes.NO_ERROR), 11100);
            });
            entscheidungen[3].addActionListener(e -> {
                entscheidungen[1].setVisible(false);
                entscheidungen[2].setVisible(false);
                entscheidungen[3].setVisible(false);
                Sounds[3][5].playSound();
                timer(() -> {
                    Sounds[3][6].playSound();
                    timer(() -> System.exit(ExitCodes.NO_ERROR), 3000);
                }, 1500);
            });
            entscheidungen[2].addActionListener(e -> {
                entscheidungen[1].setVisible(false);
                entscheidungen[2].setVisible(false);
                entscheidungen[3].setVisible(false);
                Kunstgalerie.setVisible(false);
                FingerabdruckSuche.setVisible(true);
                FingerabdruckSuche.restartAnimation();
                FingerabdruckSuche.add(panel);
            });


            WindowOperations(3, resizable, size, location, Textures[3][0], background);
        });
    }
}
