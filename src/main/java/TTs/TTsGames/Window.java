package TTs.TTsGames;

import TTs.TTsGames.Games.DetektivThunder.DetektivThunder;
import TTs.TTsGames.Games.AnimalMaster.AnimalMaster;
import TTs.TTsGames.Games.DetektivAdler.DetektivAdler;
import TTs.TTsGames.Logger.TTsLogger;
import TTs.TTsGames.Resources.Text;
import TTs.lang.Array;
import TTs.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static TTs.TTsGames.Main.*;
import static TTs.TTsGames.Main.Audio.setVolume;
import static TTs.TTsGames.Resources.Translations.TTsGames;
import static java.awt.Color.BLACK;

public class Window {
    static JComboBox<String> languageSelection = new JComboBox<>(Languages);
    boolean pressedTrue = false, pressedFalse = false;
    public Window(boolean resizable) {
        SwingUtilities.invokeLater(() -> {
            windows[0] = new JFrame("TTs Games " + Versions[7]);

            JTextField usernameInput = new JTextField(TTsGames[18], 25);
            JButton AnimalMaster = new JButton(TTsGames[0]);
            JButton Detektiv_Adler = new JButton(TTsGames[1]);
            JButton Detektiv_Thunder = new JButton(TTsGames[2]);
            JButton Settings = new JButton(TTsGames[3]);
            JSlider volumeSlider = new VolumeSlider(0, 100, volume, new Color(162, 247, 255), new Color(200, 0, 255));
            JButton Settings_Dev = new JButton(TTsGames[5]);
            JButton SDtrue = new JButton(TTsGames[6]);
            JButton SDfalse = new JButton(TTsGames[7]);
            JButton changeUsername = new JButton(TTsGames[26]);
            JButton Settings_Back = new JButton(TTsGames[4]);
            JButton Credits = new JButton("Credits");
            JLabel Credits1 = new JLabel(TTsGames[8]), Credits2 = new JLabel(TTsGames[9]), Credits3 = new JLabel(TTsGames[10]), Credits4 = new JLabel(TTsGames[11]), Credits5 = new JLabel(TTsGames[12]);
            JButton CreditsBack = new JButton(TTsGames[4]);
            BorderPanel games = new BorderPanel(TTsGames[13], new Color(68, 68, 68, 200), true);
            BorderPanel gameInfo = new BorderPanel(TTsGames[14], new Color(68, 68, 68, 200), true);
            JLabel iAnimalMaster = new JLabel(TTsGames[15]), iDetektivAdler = new JLabel(TTsGames[16]), iDetektivThunder = new JLabel(TTsGames[17]);
            JLabel placeholder = new JLabel();
            JButton statistics = new JButton(TTsGames[19]);
            JButton statisticsBack = new JButton(TTsGames[4]);
            BorderPanel statisticsPanel = new BorderPanel(TTsGames[23], new Color(68, 68, 68, 200), true);
            JLabel animalMasterTime = new JLabel(TTsGames[20] + checkTime(AnimalMasterTime[2]) + TTsGames[21]);
            BorderPanel hello = new BorderPanel(TTsGames[24], new Color(68, 68, 68, 200), true);
            BorderPanel sizeSettings = new BorderPanel(Text.translatable("common.size"), new Color(68, 68, 68, 200), true);

            //Resizable Settings AnimalMaster
            JButton[] sizes = {
                    new JButton("1"), new JButton("1.25"), new JButton("1.5"), new JButton("1.75"), new JButton("2")
            };



            if (!setUsername) {
                games.setVisible(false);
                gameInfo.setVisible(false);
                Settings.setVisible(false);
                Credits.setVisible(false);
                statistics.setVisible(false);
                hello.setVisible(false);
            } else {
                usernameInput.setVisible(false);
            }

            if (dev) {
                games.setPreferredSize(new Dimension(140, 135));
                gameInfo.setPreferredSize(new Dimension(200, 135));
                sizeSettings.setPreferredSize(new Dimension(65, 135));
            } else {
                games.setPreferredSize(new Dimension(140, 98));
                gameInfo.setPreferredSize(new Dimension(200, 98));
                sizeSettings.setPreferredSize(new Dimension(65, 98));
            }
            hello.setPreferredSize(new Dimension(350, 30));
            statisticsPanel.setPreferredSize(new Dimension(350, 200));
            Component[] Games = {
                    AnimalMaster, Detektiv_Adler, Detektiv_Thunder
            };
            for (Component component : Games) {
                games.add(component);
            }
            Component[] GameIno = {
                    iAnimalMaster, iDetektivAdler, iDetektivThunder
            };
            for (Component component : GameIno) {
                gameInfo.add(component);
            }
            for (Component component : sizes) {
                sizeSettings.add(component);
            }
            statisticsPanel.add(animalMasterTime);
            Component[] doStandardButtonstuff = {
                    AnimalMaster, Detektiv_Adler, Detektiv_Thunder, Settings, Settings_Back, Credits, CreditsBack,
                    Settings_Dev, SDtrue, SDfalse, statistics, statisticsBack, changeUsername
            };
            for (Component component : doStandardButtonstuff) {
                component.setForeground(BLACK);
                component.setBackground(new Color(68, 68, 68));
                component.setFocusable(false);
            }
            for (Component component : sizes) {
                component.setForeground(BLACK);
                component.setBackground(new Color(68, 68, 68));
                component.setFocusable(false);
            }

            volumeSlider.setOpaque(false);
            volumeSlider.setMajorTickSpacing(10);
            volumeSlider.setMinorTickSpacing(1);
            volumeSlider.setFocusable(false);
            volumeSlider.setForeground(new Color(68, 68, 68, 200));

            languageSelection.setFocusable(false);
            setLanguageSelection(language);
            languageSelection.setRenderer(new CustomComboBoxRenderer());
            if (!setUsername) {
                volumeSlider.setVisible(false);
                languageSelection.setVisible(false);
            }

            Component[] setVisibleFalse = {
                    languageSelection, Settings_Back, Credits1, Credits2, Credits3, Credits4, Credits5, CreditsBack, Settings_Dev, SDtrue, SDfalse, volumeSlider, statisticsBack,
                    statisticsPanel, changeUsername
            };
            for (Component component : setVisibleFalse) {
                component.setVisible(false);
            }

            Dimension buttonSize = new Dimension(118, 30);
            Component[] setPrefferendSizeButtonSize = {
                    Settings, languageSelection, Settings_Back, Credits, CreditsBack, statistics, statisticsBack
            };
            AnimalMaster.setPreferredSize(new Dimension(133, 30));
            Detektiv_Adler.setPreferredSize(new Dimension(133, 30));
            Detektiv_Thunder.setPreferredSize(new Dimension(133, 30));
            iAnimalMaster.setPreferredSize(new Dimension(190, 30));
            iDetektivAdler.setPreferredSize(new Dimension(190, 30));
            iDetektivThunder.setPreferredSize(new Dimension(190, 30));
            placeholder.setPreferredSize(new Dimension(400, 55));
            changeUsername.setPreferredSize(new Dimension(160, 30));
            Settings_Dev.setPreferredSize(new Dimension(200, 30));
            SDtrue.setPreferredSize(new Dimension(200, 30));
            SDfalse.setPreferredSize(new Dimension(200, 30));
            for (Component component : sizes) {
                component.setPreferredSize(new Dimension(58, 30));
            }
            for (Component component : setPrefferendSizeButtonSize) {
                component.setPreferredSize(buttonSize);
            }

            JPanel panel = getJPanel();

            ImagePanel Background = Textures[0][1].toImagePanel();
            Background.setLayout(new BorderLayout());
            Background.setSize(windows[0].getWidth(), windows[0].getHeight());
            Background.add(panel);

            Component[] addToPanel = {
                    hello, games, sizeSettings, gameInfo, placeholder, Settings, languageSelection, volumeSlider, changeUsername, Settings_Dev, SDtrue, SDfalse,
                    Settings_Back, Credits, Credits1, Credits2, Credits3, Credits4, Credits5, CreditsBack, usernameInput,
                    statistics, statisticsPanel,statisticsBack
            };
            for (Component component : addToPanel) {
                panel.add(component);
            }

            for (Component component : sizes) {
                component.setVisible(false);
            }

            if (a == 1.0) sizes[0].setVisible(true);
            else if (a == 1.25) sizes[1].setVisible(true);
            else if (a == 1.5) sizes[2].setVisible(true);
            else if (a == 1.75) sizes[3].setVisible(true);
            else if (a == 2.0) sizes[4].setVisible(true);


            AnimalMaster.addActionListener(e -> {
                if (!alreadyStarted[0] && !fAMReset) {
                    logger.setInstance(TTsLogger.Instance.ANIMAL_MASTER);
                    MainWindow = false;
                    new AnimalMaster(false, windows[0].getLocation(), new Dimension((int) (420 * a), (int) (210 * a)));
                    alreadyStarted[0] = true;
                    windows[0].setVisible(false);
                    started = "AnimalMaster";
                } else {
                    MainWindow = false;
                    windows[0].setVisible(false);
                }
                Main.AnimalMaster = true;
            });
            Detektiv_Adler.addActionListener(e -> {
                if (!alreadyStarted[1]) {
                    logger.setInstance(TTsLogger.Instance.DETEKTIV_ADLER);
                    MainWindow = false;
                    new DetektivAdler(false, windows[0].getLocation(), new Dimension(420, 210));
                    windows[0].setVisible(false);
                    started = " Detektiv Adler";
                    alreadyStarted[1] = true;
                } else {
                    MainWindow = false;
                    windows[0].setVisible(false);
                }
                Main.DetektivAdler = true;
            });
            if (dev) {
                Detektiv_Thunder.addActionListener(e -> {
                    logger.setInstance(TTsLogger.Instance.DETEKTIV_THUNDER);
                    MainWindow = false;
                    new DetektivThunder(false,
                            new Point(windows[0].getX() - 500, windows[0].getY() - 300), new Dimension(1330, 750));
                    windows[0].setVisible(false);
                    started = " Detektiv Thunder";
                });
            }
            Settings.addActionListener(e -> {
                languageSelection.setVisible(true);
                Settings_Dev.setVisible(true);
                games.setVisible(false);
                gameInfo.setVisible(false);
                sizeSettings.setVisible(false);
                placeholder.setVisible(false);
                Credits.setVisible(false);
                Settings.setVisible(false);
                Settings_Back.setVisible(true);
                volumeSlider.setVisible(true);
                changeUsername.setVisible(true);
                statistics.setVisible(false);
                hello.setVisible(false);
            });
            Settings_Back.addActionListener(e -> {
                languageSelection.setVisible(false);
                Settings_Dev.setVisible(false);
                SDtrue.setVisible(false);
                SDfalse.setVisible(false);
                Settings_Back.setVisible(false);
                games.setVisible(true);
                gameInfo.setVisible(true);
                sizeSettings.setVisible(true);
                placeholder.setVisible(true);
                Credits.setVisible(true);
                Settings.setVisible(true);
                volumeSlider.setVisible(false);
                changeUsername.setVisible(false);
                statistics.setVisible(true);
                hello.setVisible(true);
            });
            languageSelection.addActionListener(e -> {
                configLoader.setLanguage((String) languageSelection.getSelectedItem());
                language = (String) languageSelection.getSelectedItem();
            });
            Credits.addActionListener(e -> {
                Credits.setVisible(false);
                games.setVisible(false);
                gameInfo.setVisible(false);
                sizeSettings.setVisible(false);
                placeholder.setVisible(false);
                Settings.setVisible(false);
                Credits1.setVisible(true);
                Credits2.setVisible(true);
                Credits3.setVisible(true);
                Credits4.setVisible(true);
                Credits5.setVisible(true);
                CreditsBack.setVisible(true);
                statistics.setVisible(false);
                Background.setVisible(false);
                windows[0].add(panel);
                hello.setVisible(false);
            });
            CreditsBack.addActionListener(e -> {
                Background.setVisible(true);
                Background.add(panel);
                Credits.setVisible(true);
                games.setVisible(true);
                gameInfo.setVisible(true);
                sizeSettings.setVisible(true);
                placeholder.setVisible(true);
                Settings.setVisible(true);
                Credits1.setVisible(false);
                Credits2.setVisible(false);
                Credits3.setVisible(false);
                Credits4.setVisible(false);
                Credits5.setVisible(false);
                CreditsBack.setVisible(false);
                statistics.setVisible(true);
                hello.setVisible(true);
            });
            Settings_Dev.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Settings_Dev.setVisible(false);
                    if (configLoader.isDevVersionsEnabled()) {
                        SDtrue.setVisible(true);
                    } else {
                        SDfalse.setVisible(true);
                    }
                }
            });
            SDtrue.addActionListener(e -> {
                pressedTrue = true;
                configLoader.setDevVersions(false);
                dev = false;
                SDtrue.setVisible(false);
                SDfalse.setVisible(true);
                games.setPreferredSize(new Dimension(games.getWidth(), 100));
                gameInfo.setPreferredSize(new Dimension(gameInfo.getWidth(), 100));
            });
            SDtrue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!pressedTrue) {
                        SDtrue.setVisible(false);
                        Settings_Dev.setVisible(true);
                    } else {
                        pressedTrue = false;
                    }
                }
            });
            SDfalse.addActionListener(e -> {
                pressedFalse = true;
                configLoader.setDevVersions(true);
                dev = true;
                SDtrue.setVisible(true);
                SDfalse.setVisible(false);
                games.setPreferredSize(new Dimension(games.getWidth(), 135));
                gameInfo.setPreferredSize(new Dimension(gameInfo.getWidth(), 135));
            });
            SDfalse.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!pressedFalse) {
                        SDfalse.setVisible(false);
                        Settings_Dev.setVisible(true);
                    } else {
                        pressedFalse = false;
                    }
                }
            });
            changeUsername.addActionListener(e -> {
                games.setVisible(false);
                gameInfo.setVisible(false);
                Settings.setVisible(false);
                Credits.setVisible(false);
                statistics.setVisible(false);
                hello.setVisible(false);
                languageSelection.setVisible(false);
                volumeSlider.setVisible(false);
                Settings_Dev.setVisible(false);
                SDfalse.setVisible(false);
                SDtrue.setVisible(false);
                Settings_Back.setVisible(false);
                changeUsername.setVisible(false);
                usernameInput.setVisible(true);
            });
            volumeSlider.addChangeListener(e -> {
                configLoader.setVolume(volumeSlider.getValue());
                volume = volumeSlider.getValue();
                setVolume((float) volume);
            });
            usernameInput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        logger.info("Pressed Key");
                        if (Array.ContainsIgnoreCase(unerlaubteNamen, usernameInput.getText())
                                || usernameInput.getText().length() >= 20
                                || usernameInput.getText().length() <= 2) {
                            usernameInput.setText(TTsGames[25]);
                        } else {
                            logger.info(usernameInput.getText());
                            configLoader.setUsername(usernameInput.getText());
                            usernameInput.setVisible(false);
                            games.setVisible(true);
                            gameInfo.setVisible(true);
                            Settings.setVisible(true);
                            Credits.setVisible(true);
                            statistics.setVisible(true);
                            placeholder.setVisible(true);
                            hello.setVisible(true);
                        }
                    }
                }
            });
            statistics.addActionListener(e -> {
                statisticsPanel.setVisible(true);
                statisticsBack.setVisible(true);
                games.setVisible(false);
                gameInfo.setVisible(false);
                sizeSettings.setVisible(false);
                placeholder.setVisible(false);
                Credits.setVisible(false);
                Settings.setVisible(false);
                statistics.setVisible(false);
                hello.setVisible(false);
            });
            statisticsBack.addActionListener(e -> {
                statisticsPanel.setVisible(false);
                statisticsBack.setVisible(false);
                games.setVisible(true);
                gameInfo.setVisible(true);
                sizeSettings.setVisible(true);
                placeholder.setVisible(true);
                Credits.setVisible(true);
                Settings.setVisible(true);
                statistics.setVisible(true);
                hello.setVisible(true);
            });

            sizes[0].addActionListener(e -> {
                configLoader.setAnimal_master_size_multiplier(1.25);
                sizes[0].setVisible(false);
                sizes[1].setVisible(true);
            });
            sizes[1].addActionListener(e -> {
                configLoader.setAnimal_master_size_multiplier(1.5);
                sizes[1].setVisible(false);
                sizes[2].setVisible(true);
            });
            sizes[2].addActionListener(e -> {
                configLoader.setAnimal_master_size_multiplier(1.75);
                sizes[2].setVisible(false);
                sizes[3].setVisible(true);
            });
            sizes[3].addActionListener(e -> {
                configLoader.setAnimal_master_size_multiplier(2.0);
                sizes[3].setVisible(false);
                sizes[4].setVisible(true);
            });
            sizes[4].addActionListener(e -> {
                configLoader.setAnimal_master_size_multiplier(1.0);
                sizes[4].setVisible(false);
                sizes[0].setVisible(true);
            });


            WindowOperations(0, resizable, new Dimension(500, 400), new Point(800, 500), Textures[0][0], Background);
            timer(() -> {
                if (fAM) {
                    fAM = false;
                    fAMReset = true;
                    animalMasterTime.setText(TTsGames[20] + checkTime(AnimalMasterTime[2]) + TTsGames[21]);
                }
                windows[0].setVisible(MainWindow);
            }, 10, 10);
        });
    }

    private static JPanel getJPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        return panel;
    }

    private static void setLanguageSelection(String language) {
        for (int i = 0; i < languageSelection.getItemCount(); i++) {
            if (languageSelection.getItemAt(i).equals(language)) {
                languageSelection.setSelectedIndex(i);
                break;
            }
            //Trigger the action listener to load the appropriate language
            ActionEvent event = new ActionEvent(languageSelection, ActionEvent.ACTION_PERFORMED, null);
            for (ActionListener listener : languageSelection.getActionListeners()) {
                listener.actionPerformed(event);
            }
        }
    }
}
