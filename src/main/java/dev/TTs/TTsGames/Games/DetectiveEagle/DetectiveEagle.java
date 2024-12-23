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
            TButton answer31 = new TButton(Textures[2][0][3].toIcon());
            TButton answer32 = new TButton(Textures[2][0][4].toIcon());

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
                component.setBounds((int) (10 * de), (int) (45 * de), (int) (118 * de), (int) (30 * de));
            }
            answer02.setBounds((int) (135 * de), (int) (45 * de), (int) (118 * de), (int) (30 * de));
            answer03.setBounds((int) (265 * de), (int) (45 * de), (int) (118 * de), (int) (30 * de));
            answer12.setBounds((int) (135 * de), (int) (45 * de), (int) (118 * de), (int) (30 * de));
            answer13.setBounds((int) (265 * de), (int) (45 * de), (int) (118 * de), (int) (30 * de));
            answer21.setBounds((int) (135 * de), (int) (45 * de), (int) (118 * de), (int) (30 * de));
            answer31.setBounds((int) (10 * de), (int) (45 * de), (int) (140 * de), (int) (140 * de));
            answer32.setBounds((int) (165 * de), (int) (45 * de), (int) (140 * de), (int) (140 * de));
            Component[] setLabelBounds = {
                    erkl, question0, question1, question2, question3
            };
            for (Component component : setLabelBounds) {
                component.setBounds((int) (10 * de), (int) (10 * de), (int) (320 * de), (int) (30 * de));
            }

            TImage Background = Textures[2][0][1].toTImage();
            Background.setLayout(new BorderLayout());
            Background.setPreferredSize(new Dimension((int) (420 * de), (int) (210 * de)));
            Background.setBounds(0, 0, (int) (420 * de), (int) (210 * de));

            TImage Fall1 = Textures[2][0][2].toTImage();
            Fall1.setLayout(new BorderLayout());
            Fall1.setPreferredSize(new Dimension((int) (300 * de), (int) (210 * de)));
            Fall1.Show();
            Fall1.setBounds(0, 0, (int) (300 * de), (int) (210 * de));

            TLayeredPane Pane = new TLayeredPane();
            Pane.setPreferredSize(new Dimension((int) (420 * de), (int) (210 * de)));

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

            start.event( () -> {
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
            answer01.event( () -> System.exit(ExitCodes.NO_ERROR));
            answer02.event( () -> {
                question0.Hide();
                answer01.Hide();
                answer02.Hide();
                answer03.Hide();
                question1.Show();
                answer11.Show();
                answer12.Show();
                answer13.Show();
            });
            answer03.event( () -> System.exit(ExitCodes.NO_ERROR));
            answer11.event( () -> {
                question1.Hide();
                answer11.Hide();
                answer12.Hide();
                answer13.Hide();
                question2.Show();
                answer20.Show();
                answer21.Show();
            });
            answer12.event( () -> {
                question1.Hide();
                answer11.Hide();
                answer12.Hide();
                answer13.Hide();
                question2.Show();
                answer20.Show();
                answer21.Show();
            });
            answer13.event( () -> System.exit(ExitCodes.NO_ERROR));
            answer20.event( () -> {
                question2.Hide();
                answer20.Hide();
                answer21.Hide();
                question3.Show();
                answer31.Show();
                answer32.Show();
            });
            answer21.event( () -> System.exit(ExitCodes.NO_ERROR));
            answer31.event( () -> {
                question3.Hide();
                answer31.Hide();
                answer32.Hide();
            });
            answer32.event( () -> System.exit(ExitCodes.NO_ERROR));

            WindowOperations(2, inf, Pane);
        });
    }
}
