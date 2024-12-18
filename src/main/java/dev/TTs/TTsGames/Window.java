package dev.TTs.TTsGames;

import dev.TTs.TTsGames.Games.DetectiveThunder.DetectiveThunder;
import dev.TTs.TTsGames.Games.AnimalMaster.AnimalMaster;
import dev.TTs.TTsGames.Games.DetectiveEagle.DetectiveEagle;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuest;
import dev.TTs.lang.SoundString;
import dev.TTs.resources.Configs;
import dev.TTs.resources.Translations;
import dev.TTs.lang.Instance;
import dev.TTs.lang.Array;
import dev.TTs.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.*;
import static dev.TTs.lang.SoundString.setVolume;
import static java.awt.Color.BLACK;

@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
public class Window {
    private static TComboBox<String> languageSelection = new TComboBox<>(Languages);
    boolean SDpressedTrue = false, SDpressedFalse = false;
    boolean SubpressedTrue = false, SubpressedFalse = false;
    private static TSubtitles SubtitlesScr;

    public Window(boolean resizable) {
        SwingUtilities.invokeLater(() -> {
            SubtitlesScr = new TSubtitles(new Point(0, 0));
            if (subtitles) {
                setSubtitlesVisible(true);
            }
            windows[0] = new TFrame("TTsGames " + Versions[1]);

            TButton settings = new TButton(Settings[0]);
            TButton Settings_Back = new TButton(Settings[1]);
            TButton Settings_Dev = new TButton(Settings[2]);
            TButton SDtrue = new TButton(Settings[6]);
            TButton SDfalse = new TButton(Settings[7]);
            TTextField usernameInput = new TTextField(Settings[4], 25);
            TButton changeUsername = new TButton(Settings[3]);
            TBorderPanel sizeSettings = new TBorderPanel(Settings[5], new Color(68, 68, 68, 200), true);
            TVolumeSlider volumeSlider = new TVolumeSlider(0, 100, volume, new Color(162, 247, 255), new Color(200, 0, 255));

            TButton Subtitles = new TButton(Settings[8]);
            TButton SubDtrue = new TButton(Settings[6]);
            TButton SubDfalse = new TButton(Settings[7]);

            TButton[] sizes = {
                    new TButton("1"), new TButton("1.25"), new TButton("1.5"), new TButton("1.75"), new TButton("2")
            };

            TButton credits = new TButton(Credits[0]);
            TButton CreditsBack = new TButton(Credits[1]);
            TBorderPanel CreditBorder = new TBorderPanel(true);
            TLabel Credits1 = new TLabel(Credits[2]), Credits2 = new TLabel(Credits[3]), Credits3 = new TLabel(Credits[4]),
                    Credits4 = new TLabel(Credits[5]), Credits5 = new TLabel(Credits[6]);
            CreditBorder.setPreferredSize(new Dimension(350, 200));
            CreditBorder.add(Credits1, Credits2, Credits3, Credits4, Credits5);

            TButton statistics = new TButton(Statistics[0]);
            TButton statisticsBack = new TButton(Statistics[1]);
            TBorderPanel statisticsPanel = new TBorderPanel(Statistics[2], new Color(68, 68, 68, 200), true);
            TLabel animalMasterTime = new TLabel(Statistics[3] + checkTime(AMTime[2], Statistics[5]) + Statistics[4]);

            TButton AnimalMaster = new TButton(Games[0]), Detective_Eagle = new TButton(Games[1]), Detective_Thunder = new TButton(Games[2]), PixelQuest = new TButton(Games[3]);
            TLabel iAnimalMaster = new TLabel(Games[4]), iDetektivAdler = new TLabel(Games[5]), iDetektivThunder = new TLabel(Games[6]), iPixelQuest = new TLabel(Games[7]);

            TBorderPanel games = new TBorderPanel(TTsGames[0], new Color(68, 68, 68, 200), true);
            TBorderPanel gameInfo = new TBorderPanel(TTsGames[1], new Color(68, 68, 68, 200), true);
            TLabel placeholder = new TLabel();
            TBorderPanel hello = new TBorderPanel(TTsGames[2], new Color(68, 68, 68, 200), true);

            if (!setUsername) {
                games.Hide();
                gameInfo.Hide();
                sizeSettings.Hide();
                settings.Hide();
                credits.Hide();
                statistics.Hide();
                hello.Hide();
            } else {
                usernameInput.Hide();
            }

            if (dev) {
                games.setPreferredSize(new Dimension(140, 165));
                gameInfo.setPreferredSize(new Dimension(200, 165));
                sizeSettings.setPreferredSize(new Dimension(65, 165));
            } else {
                games.setPreferredSize(new Dimension(140, 100));
                gameInfo.setPreferredSize(new Dimension(200, 100));
                sizeSettings.setPreferredSize(new Dimension(65, 100));
            }
            hello.setPreferredSize(new Dimension(350, 30));
            statisticsPanel.setPreferredSize(new Dimension(350, 200));
            Component[] Games = {
                    AnimalMaster, Detective_Eagle, Detective_Thunder, PixelQuest
            };
            for (Component component : Games) {
                games.add(component);
            }
            Component[] GameIno = {
                    iAnimalMaster, iDetektivAdler, iDetektivThunder, iPixelQuest
            };
            for (Component component : GameIno) {
                gameInfo.add(component);
            }
            for (TButton component : sizes) {
                sizeSettings.add(component);
            }
            statisticsPanel.add(animalMasterTime);
            TComponent[] doStandardButtonstuff = {
                    AnimalMaster, Detective_Eagle, Detective_Thunder, PixelQuest,settings, Settings_Back, credits, CreditsBack,
                    Settings_Dev, SDtrue, SDfalse, statistics, statisticsBack, changeUsername, Subtitles, SubDtrue, SubDfalse
            };
            for (TComponent component : doStandardButtonstuff) {
                component.setColor(Region.FOREGROUND, BLACK);
                component.setColor(Region.BACKGROUND, new Color(68, 68, 68));
                component.setFocusableF();
            }
            for (TComponent component : sizes) {
                component.setColor(Region.FOREGROUND, BLACK);
                component.setColor(Region.BACKGROUND, new Color(68, 68, 68));
                component.setFocusableF();
            }

            volumeSlider.setOpaque(false);
            volumeSlider.setMajorTickSpacing(10);
            volumeSlider.setMinorTickSpacing(1);
            volumeSlider.setFocusable(false);
            volumeSlider.setForeground(new Color(68, 68, 68, 200));

            languageSelection.setFocusable(false);
            setLanguageSelection(language);
            languageSelection.setRenderer(new LangSelRenderer());
            if (!setUsername) {
                volumeSlider.Hide();
                languageSelection.Hide();
            }

            Component[] setVisibleFalse = {
                    languageSelection, Settings_Back, CreditBorder, CreditsBack, Settings_Dev, SDtrue, SDfalse, volumeSlider, statisticsBack,
                    statisticsPanel, changeUsername, Subtitles, SubDtrue, SubDfalse
            };
            for (Component component : setVisibleFalse) {
                component.setVisible(false);
            }

            Dimension buttonSize = new Dimension(118, 30);
            Component[] setPrefferendSizeButtonSize = {
                    settings, languageSelection, Settings_Back, credits, CreditsBack, statistics, statisticsBack, Subtitles, SubDtrue, SubDfalse
            };
            AnimalMaster.setPreferredSize(new Dimension(133, 30));
            Detective_Eagle.setPreferredSize(new Dimension(133, 30));
            Detective_Thunder.setPreferredSize(new Dimension(133, 30));
            PixelQuest.setPreferredSize(new Dimension(133, 30));
            iAnimalMaster.setPreferredSize(new Dimension(190, 30));
            iDetektivAdler.setPreferredSize(new Dimension(190, 30));
            iDetektivThunder.setPreferredSize(new Dimension(190, 30));
            iPixelQuest.setPreferredSize(new Dimension(190, 30));
            placeholder.setPreferredSize(new Dimension(400, 55));
            changeUsername.setPreferredSize(new Dimension(160, 30));
            Settings_Dev.setPreferredSize(new Dimension(200, 30));
            SDtrue.setPreferredSize(new Dimension(200, 30));
            SDfalse.setPreferredSize(new Dimension(200, 30));
            for (TButton component : sizes) {
                component.setPreferredSize(new Dimension(58, 30));
            }
            for (Component component : setPrefferendSizeButtonSize) {
                component.setPreferredSize(buttonSize);
            }

            TPanel panel = getTPanel();

            TImage Background = Textures[0][1].toTImage();
            Background.setLayout(new BorderLayout());
            Background.setSize(windows[0].getWidth(), windows[0].getHeight());
            Background.add(panel);

            Component[] addToPanel = {
                    hello, games, sizeSettings, gameInfo, placeholder, settings, languageSelection, volumeSlider, changeUsername, Settings_Dev, SDtrue, SDfalse,
                    Subtitles, SubDtrue, SubDfalse, Settings_Back, credits, CreditBorder, CreditsBack, usernameInput,
                    statistics, statisticsPanel,statisticsBack
            };
            for (Component component : addToPanel) {
                panel.add(component);
            }

            for (TButton component : sizes) {
                component.Hide();
            }

            if (a == 1.0) sizes[0].Show();
            else if (a == 1.25) sizes[1].Show();
            else if (a == 1.5) sizes[2].Show();
            else if (a == 1.75) sizes[3].Show();
            else if (a == 2.0) sizes[4].Show();


            AnimalMaster.event( () -> {
                if (!alreadyStarted[0] && !fAMReset) {
                    logger.setInstance(Instance.ANIMAL_MASTER);
                    MainWindow = false;
                    new AnimalMaster(new WindowInformation(false, windows[0].getLocation(), new Dimension((int) (420 * a), (int) (210 * a)), Textures[1][37],
                            Translations.Games[0] + Versions[4]));
                    alreadyStarted[0] = true;
                    windows[0].Hide();
                    started = "AnimalMaster";
                } else {
                    MainWindow = false;
                    windows[0].Hide();
                }
                Main.AnimalMaster = true;
            });
            Detective_Eagle.event( () -> {
                if (!alreadyStarted[1]) {
                    logger.setInstance(Instance.DETEKTIV_ADLER);
                    MainWindow = false;
                    new DetectiveEagle(new WindowInformation(false, windows[0].getLocation(), new Dimension(420, 210), Textures[2][0]));
                    windows[0].Hide();
                    started = " Detektiv Adler";
                    alreadyStarted[1] = true;
                } else {
                    MainWindow = false;
                    windows[0].Hide();
                }
                Main.DetektivAdler = true;
            });
            if (dev) {
                Detective_Thunder.event( () -> {
                    logger.setInstance(Instance.DETEKTIV_THUNDER);
                    MainWindow = false;
                    new DetectiveThunder(new WindowInformation(false, new Point(windows[0].getX() - 500, windows[0].getY() - 300),
                            new Dimension(1330, 750), Textures[3][0]));
                    windows[0].Hide();
                    started = " Detektiv Thunder";
                });
                PixelQuest.event(() -> {
                    logger.setInstance(Instance.PIXEL_QUEST);
                    MainWindow = false;
                    new PixelQuest().create(new WindowInformation(false, new Point(windows[0].getX() - 300, windows[0].getY() - 300), new Dimension(800, 800),
                            Textures[4][0], Translations.Games[3] + Versions[13]));
                    windows[0].Hide();
                    started = " Pixel Quest";
                });
            }
            settings.event( () -> {
                languageSelection.Show();
                Settings_Dev.Show();
                Subtitles.Show();
                games.Hide();
                gameInfo.Hide();
                sizeSettings.Hide();
                placeholder.Hide();
                credits.Hide();
                settings.Hide();
                Settings_Back.Show();
                volumeSlider.Show();
                changeUsername.Show();
                statistics.Hide();
                hello.Hide();
            });
            Settings_Back.event( () -> {
                languageSelection.Hide();
                Settings_Dev.Hide();
                Subtitles.Hide();
                SDtrue.Hide();
                SDfalse.Hide();
                Settings_Back.Hide();
                games.Show();
                gameInfo.Show();
                sizeSettings.Show();
                placeholder.Show();
                credits.Show();
                settings.Show();
                volumeSlider.Hide();
                changeUsername.Hide();
                statistics.Show();
                hello.Show();
            });
            languageSelection.addActionListener( () -> {
                configLoader.set(Configs.LANGUAGE, languageSelection.getSelectedItem());
                language = (String) languageSelection.getSelectedItem();
            });
            //TODO improve
            credits.event( () -> {
                credits.Hide();
                games.Hide();
                gameInfo.Hide();
                sizeSettings.Hide();
                placeholder.Hide();
                settings.Hide();
                CreditBorder.Show();
                CreditsBack.Show();
                statistics.Hide();
                hello.Hide();
            });
            CreditsBack.event( () -> {
                credits.Show();
                games.Show();
                gameInfo.Show();
                sizeSettings.Show();
                placeholder.Show();
                settings.Show();
                CreditBorder.Hide();
                CreditsBack.Hide();
                statistics.Show();
                hello.Show();
            });
            Settings_Dev.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Settings_Dev.Hide();
                    if (configLoader.get(Configs.DEV_VERSIONS)) {
                        SDtrue.Show();
                    } else {
                        SDfalse.Show();
                    }
                }
            });
            SDtrue.event( () -> {
                SDpressedTrue = true;
                configLoader.set(Configs.DEV_VERSIONS, false);
                dev = false;
                SDtrue.Hide();
                SDfalse.Show();
                games.setPreferredSize(new Dimension(games.getWidth(), 100));
                gameInfo.setPreferredSize(new Dimension(gameInfo.getWidth(), 100));
                sizeSettings.setPreferredSize(new Dimension(sizeSettings.getWidth(), 100));
            });
            SDtrue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!SDpressedTrue) {
                        SDtrue.Hide();
                        Settings_Dev.Show();
                    } else {
                        SDpressedTrue = false;
                    }
                }
            });
            SDfalse.event( () -> {
                SDpressedFalse = true;
                configLoader.set(Configs.DEV_VERSIONS, true);
                dev = true;
                SDtrue.Show();
                SDfalse.Hide();
                games.setPreferredSize(new Dimension(games.getWidth(), 165));
                gameInfo.setPreferredSize(new Dimension(gameInfo.getWidth(), 165));
                sizeSettings.setPreferredSize(new Dimension(sizeSettings.getWidth(), 165));
            });
            SDfalse.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!SDpressedFalse) {
                        SDfalse.Hide();
                        Settings_Dev.Show();
                    } else {
                        SDpressedFalse = false;
                    }
                }
            });
            changeUsername.event( () -> {
                games.Hide();
                gameInfo.Hide();
                settings.Hide();
                credits.Hide();
                statistics.Hide();
                hello.Hide();
                languageSelection.Hide();
                volumeSlider.Hide();
                Settings_Dev.Hide();
                Subtitles.Hide();
                SDfalse.Hide();
                SDtrue.Hide();
                Settings_Back.Hide();
                changeUsername.Hide();
                usernameInput.Show();
            });
            volumeSlider.addChangeListener(e -> {
                configLoader.set(Configs.VOLUME, volumeSlider.getValue());
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
                            usernameInput.setText(TTsGames[3]);
                        } else {
                            logger.info(usernameInput.getText());
                            configLoader.set(Configs.USERNAME, usernameInput.getText());
                            usernameInput.Hide();
                            games.Show();
                            gameInfo.Show();
                            settings.Show();
                            credits.Show();
                            statistics.Show();
                            placeholder.Show();
                            sizeSettings.Show();
                            hello.Show();
                        }
                    }
                }
            });
            statistics.event( () -> {
                statisticsPanel.Show();
                statisticsBack.Show();
                games.Hide();
                gameInfo.Hide();
                sizeSettings.Hide();
                placeholder.Hide();
                credits.Hide();
                settings.Hide();
                statistics.Hide();
                hello.Hide();
            });
            statisticsBack.event( () -> {
                statisticsPanel.Hide();
                statisticsBack.Hide();
                games.Show();
                gameInfo.Show();
                sizeSettings.Show();
                placeholder.Show();
                credits.Show();
                settings.Show();
                statistics.Show();
                hello.Show();
            });

            sizes[0].event( () -> {
                configLoader.set(Configs.AM_SIZE_MULTIPLIER, 1.25);
                sizes[0].Hide();
                sizes[1].Show();
            });
            sizes[1].event( () -> {
                configLoader.set(Configs.AM_SIZE_MULTIPLIER, 1.5);
                sizes[1].Hide();
                sizes[2].Show();
            });
            sizes[2].event( () -> {
                configLoader.set(Configs.AM_SIZE_MULTIPLIER, 1.75);
                sizes[2].Hide();
                sizes[3].Show();
            });
            sizes[3].event( () -> {
                configLoader.set(Configs.AM_SIZE_MULTIPLIER, 2.0);
                sizes[3].Hide();
                sizes[4].Show();
            });
            sizes[4].event( () -> {
                configLoader.set(Configs.AM_SIZE_MULTIPLIER, 1.0);
                sizes[4].Hide();
                sizes[0].Show();
            });

            Subtitles.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Subtitles.Hide();
                    if (subtitles) {
                        SubDtrue.Show();
                    } else {
                        SubDfalse.Show();
                    }
                }
            });
            SubDtrue.event(() -> {
                SubpressedTrue = true;
                configLoader.set(Configs.SUBTITLES, false);
                subtitles = false;
                Subtitles.Hide();
                setSubtitlesVisible(false);
                SubDtrue.Hide();
                SubDfalse.Show();
            });
            SubDtrue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!SubpressedTrue) {
                        SubDtrue.Hide();
                        Subtitles.Show();
                    } else {
                        SubpressedTrue = false;
                    }
                }
            });
            SubDfalse.event( () -> {
                SubpressedFalse = true;
                configLoader.set(Configs.SUBTITLES, true);
                subtitles = true;
                Subtitles.Show();
                setSubtitlesVisible(true);
                SubDtrue.Show();
                SubDfalse.Hide();
            });
            SubDfalse.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!SubpressedFalse) {
                        SubDfalse.Hide();
                        Subtitles.Show();
                    } else {
                        SubpressedFalse = false;
                    }
                }
            });


            WindowOperations(0, new WindowInformation(resizable, new Point(800, 500), new Dimension(500, 400), Textures[0][0]), Background);
            timer(() -> {
                if (fAM) {
                    fAM = false;
                    fAMReset = true;
                    animalMasterTime.setText(Statistics[3] + checkTime(AMTime[2], Statistics[5]) + Statistics[4]);
                }
                windows[0].setVisible(MainWindow);
            }, 10, 10);
        });
    }

    private TPanel getTPanel() {
        TPanel panel = new TPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };
        panel.setOpaqueF();
        panel.setBorder(10, 10, 20, 10);
        return panel;
    }

    private void setLanguageSelection(String language) {
        for (int i = 0; i < languageSelection.getItemCount(); i++) {
            if (languageSelection.getItemAt(i).equals(language)) {
                languageSelection.setSelectedIndex(i);
                break;
            }
            ActionEvent event = new ActionEvent(languageSelection, ActionEvent.ACTION_PERFORMED, null);
            for (ActionListener listener : languageSelection.getActionListeners()) {
                listener.actionPerformed(event);
            }
        }
    }

    private void setSubtitlesVisible(boolean bool) {
        SubtitlesScr.setVisible(bool);
        if (SubtitlesScr == null) {
            SubtitlesScr = new TSubtitles(new Point(0, 0));
        }
        for (SoundString[] soundArray : Sounds) {
            for (SoundString sound : soundArray) {
                sound.addSubtitles(SubtitlesScr);
            }
        }
    }
}
