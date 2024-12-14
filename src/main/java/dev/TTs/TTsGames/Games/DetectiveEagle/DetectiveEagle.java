package dev.TTs.TTsGames.Games.DetectiveEagle;

import dev.TTs.lang.ExitCodes;
import dev.TTs.swing.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.*;
import static dev.TTs.resources.Translations.DetectiveEagle;

@SuppressWarnings("SpellCheckingInspection")
public class DetectiveEagle {
    Timer timer;
    public DetectiveEagle(WindowInformation inf) {
        startedVersion = Versions[7];
        SwingUtilities.invokeLater(() -> {
            windows[2] = new TFrame(Games[1] + Versions[7]);
            TLabel erkl = new TLabel(DetectiveEagle[0]);
            TButton start = new TButton(DetectiveEagle[1]);
            TLabel question0 = new TLabel(QuestionsD[0]);
            TButton answer01 = new TButton(ButtonsD[3]);
            TButton answer02 = new TButton(ButtonsD[4]);
            TButton answer03 = new TButton(ButtonsD[5]);
            TLabel question1 = new TLabel(QuestionsD[1]);
            TButton answer11 = new TButton(ButtonsD[6]);
            TButton answer12 = new TButton(ButtonsD[7]);
            TButton answer13 = new TButton(ButtonsD[8]);
            TLabel question2 = new TLabel(QuestionsD[2]);
            TButton answer20 = new TButton(ButtonsD[1]);
            TButton answer21 = new TButton(ButtonsD[2]);
            TLabel question3 = new TLabel(QuestionsD[3]);
            TButton answer31 = new TButton(Textures[2][3].toIcon());
            TButton answer32 = new TButton(Textures[2][4].toIcon());

            TButton[] doStandardButtonstuff =  {
                    start, answer01, answer02, answer03, answer11, answer12, answer13, answer20, answer21, answer31, answer32
            };
            Component[] dostandardLabelStuff = {
                    erkl, question0, question1, question2, question3
            };
            for (TButton component : doStandardButtonstuff) {
                component.setOpaqueF();
                component.setContentAreaFilled(false);
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

            TImage Background = Textures[2][1].toTImage();
            Background.setLayout(new BorderLayout());
            Background.setPreferredSize(new Dimension(420, 171));
            Background.setBounds(0, 0, 420, 171);

            TImage Fall1 = Textures[2][2].toTImage();
            Fall1.setLayout(new BorderLayout());
            Fall1.setPreferredSize(new Dimension(300, 171));
            Fall1.Show();
            Fall1.setBounds(0, 0, 300, 171);

            TLayeredPane Pane = new TLayeredPane();
            Pane.setPreferredSize(new Dimension(420, 171));

            Component[] addToPane = {
                    erkl, start, question0, question1, question2, question3, answer20, answer21, answer31, answer01, answer02, answer03,
                    answer11, answer12, answer13, answer32, Fall1, Background
            };
            for (Component component : addToPane) {
                Pane.add(component, TLayeredPane.PALETTE_LAYER);
            }

            Component[] setVisibleFalse = {
                    Fall1, question0, question1, question2, question3, answer01, answer02, answer03, answer11, answer12, answer13, answer20, answer21, answer31, answer32,
            };
            for (Component component : setVisibleFalse) {
                component.setVisible(false);
            }

            start.clickAction( () -> {
                Fall1.Show();
                start.Hide();
                erkl.Hide();
                timer = timer(() -> {
                    Fall1.Hide();
                    question0.Show();
                    answer01.Show();
                    answer02.Show();
                    answer03.Show();
                }, 5000);
            });
            answer01.clickAction( () -> System.exit(ExitCodes.NO_ERROR));
            answer02.clickAction( () -> {
                question0.Hide();
                answer01.Hide();
                answer02.Hide();
                answer03.Hide();
                question1.Show();
                answer11.Show();
                answer12.Show();
                answer13.Show();
            });
            answer03.clickAction( () -> System.exit(ExitCodes.NO_ERROR));
            answer11.clickAction( () -> {
                question1.Hide();
                answer11.Hide();
                answer12.Hide();
                answer13.Hide();
                question2.Show();
                answer20.Show();
                answer21.Show();
            });
            answer12.clickAction( () -> {
                question1.Hide();
                answer11.Hide();
                answer12.Hide();
                answer13.Hide();
                question2.Show();
                answer20.Show();
                answer21.Show();
            });
            answer13.clickAction( () -> System.exit(ExitCodes.NO_ERROR));
            answer20.clickAction( () -> {
                question2.Hide();
                answer20.Hide();
                answer21.Hide();
                question3.Show();
                answer31.Show();
                answer32.Show();
            });
            answer21.clickAction( () -> System.exit(ExitCodes.NO_ERROR));
            answer31.clickAction( () -> {
                question3.Hide();
                answer31.Hide();
                answer32.Hide();
            });
            answer32.clickAction( () -> System.exit(ExitCodes.NO_ERROR));

            WindowOperations(2, inf, Pane);
        });
    }
}
