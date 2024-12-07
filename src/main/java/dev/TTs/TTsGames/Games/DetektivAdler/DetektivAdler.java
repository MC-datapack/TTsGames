package dev.TTs.TTsGames.Games.DetektivAdler;

import dev.TTs.swing.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.TTsGames.Resources.Translations.*;
import static dev.TTs.TTsGames.Resources.Translations.DetektivAdler;

public final class DetektivAdler {
    Timer timer;
    public DetektivAdler(boolean resizable, Point location, Dimension size) {
        startedVersion = Versions[3];
        SwingUtilities.invokeLater(() -> {
            windows[2] = new JFrame(Games[1] + Versions[3]);
            JLabel erkl = new JLabel(DetektivAdler[0]);
            JButton start = new JButton(DetektivAdler[1]);
            JLabel question0 = new JLabel(QuestionsD[0]);
            JButton answer01 = new JButton(ButtonsD[3]);
            JButton answer02 = new JButton(ButtonsD[4]);
            JButton answer03 = new JButton(ButtonsD[5]);
            JLabel question1 = new JLabel(QuestionsD[1]);
            JButton answer11 = new JButton(ButtonsD[6]);
            JButton answer12 = new JButton(ButtonsD[7]);
            JButton answer13 = new JButton(ButtonsD[8]);
            JLabel question2 = new JLabel(QuestionsD[2]);
            JButton answer20 = new JButton(ButtonsD[1]);
            JButton answer21 = new JButton(ButtonsD[2]);
            JLabel question3 = new JLabel(QuestionsD[3]);
            JButton answer31 = new JButton(Textures[2][3].toIcon());
            JButton answer32 = new JButton(Textures[2][4].toIcon());

            Component[] doStandardButtonstuff =  {
                    start, answer01, answer02, answer03, answer11, answer12, answer13, answer20, answer21, answer31, answer32
            };
            Component[] dostandardLabelStuff = {
                    erkl, question0, question1, question2, question3
            };
            for (Component component : doStandardButtonstuff) {
                ((JButton) component).setOpaque(false);
                ((JButton) component).setContentAreaFilled(false);
                component.setForeground(Color.WHITE);
                component.setFocusable(false);
            }
            for (Component component : dostandardLabelStuff) {
                component.setForeground(Color.WHITE);
            }


            Component[] setButtonBounds = {
                    start, answer20, answer01, answer11, answer12, answer13
            };
            for (Component component : setButtonBounds) {
                component.setBounds(10, 45, 118, 30);
            }
            answer02.setBounds(135, 45, 118, 30);
            answer03.setBounds(265, 45, 118, 30);
            answer12.setBounds(135, 45, 118, 30);
            answer13.setBounds(265, 45, 118, 30);
            answer21.setBounds(135, 45, 118, 30);
            answer31.setBounds(10, 45, 140, 140);
            answer32.setBounds(165, 45, 140, 140);
            Component[] setLabelBounds = {
                    erkl, question0, question1, question2, question3
            };
            for (Component component : setLabelBounds) {
                component.setBounds(10, 10, 320, 30);
            }



            ImagePanel Background = Textures[2][1].toImagePanel();
            Background.setLayout(new BorderLayout());
            Background.setPreferredSize(new Dimension(420, 171));
            Background.setBounds(0, 0, 420, 171);

            ImagePanel Fall1 = Textures[2][2].toImagePanel();
            Fall1.setLayout(new BorderLayout());
            Fall1.setPreferredSize(new Dimension(300, 171));
            Fall1.setVisible(true);
            Fall1.setBounds(0, 0, 300, 171);

            JLayeredPane Pane = new JLayeredPane();
            Pane.setPreferredSize(new Dimension(420, 171));

            Component[] addToPane = {
                    erkl, start, question0, question1, question2, question3, answer20, answer21, answer31, answer01, answer02, answer03,
                    answer11, answer12, answer13, answer32, Fall1, Background
            };
            for (Component component : addToPane) {
                Pane.add(component, JLayeredPane.PALETTE_LAYER);
            }

            Component[] setVisibleFalse = {
                    Fall1, question0, question1, question2, question3, answer01, answer02, answer03, answer11, answer12, answer13, answer20, answer21, answer31, answer32,
            };
            for (Component component : setVisibleFalse) {
                component.setVisible(false);
            }






            start.addActionListener(e-> {
                Fall1.setVisible(true);
                start.setVisible(false);
                erkl.setVisible(false);
                timer = timer(() -> {
                    Fall1.setVisible(false);
                    question0.setVisible(true);
                    answer01.setVisible(true);
                    answer02.setVisible(true);
                    answer03.setVisible(true);
                }, 5000);
            });
            answer01.addActionListener(e-> {
                System.exit(0); //TODO Verbessern nicht System.exit(0) sondern etwas anderes
            });
            answer02.addActionListener(e-> {
                question0.setVisible(false);
                answer01.setVisible(false);
                answer02.setVisible(false);
                answer03.setVisible(false);
                question1.setVisible(true);
                answer11.setVisible(true);
                answer12.setVisible(true);
                answer13.setVisible(true);
            });
            answer03.addActionListener(e-> {
                System.exit(0); //TODO Verbessern nicht System.exit(0) sondern etwas anderes
            });
            answer11.addActionListener(e-> {
                question1.setVisible(false);
                answer11.setVisible(false);
                answer12.setVisible(false);
                answer13.setVisible(false);
                question2.setVisible(true);
                answer20.setVisible(true);
                answer21.setVisible(true);
            });
            answer12.addActionListener(e-> {
                question1.setVisible(false);
                answer11.setVisible(false);
                answer12.setVisible(false);
                answer13.setVisible(false);
                question2.setVisible(true);
                answer20.setVisible(true);
                answer21.setVisible(true);
            });
            answer13.addActionListener(e-> {
                System.exit(0); //TODO Verbessern nicht System.exit(0) sondern etwas anderes
            });
            answer20.addActionListener(e-> {
                question2.setVisible(false);
                answer20.setVisible(false);
                answer21.setVisible(false);
                question3.setVisible(true);
                answer31.setVisible(true);
                answer32.setVisible(true);
            });
            answer21.addActionListener(e-> {
                System.exit(0); //TODO Verbessern nicht System.exit(0) sondern etwas anderes
            });
            answer31.addActionListener(e-> {
                question3.setVisible(false);
                answer31.setVisible(false);
                answer32.setVisible(false);
            });
            answer32.addActionListener(e-> {
                System.exit(0); //TODO Verbessern nicht System.exit(0) sondern etwas anderes
            });



            WindowOperations(2, resizable, size, location, Textures[2][0], Pane);
        });
    }
}
