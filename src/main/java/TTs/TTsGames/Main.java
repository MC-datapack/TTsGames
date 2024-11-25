package TTs.TTsGames;

import TTs.TTsGames.Games.DetektivThunder.Close;
import TTs.TTsGames.Resources.Json.JsonReader;
import TTs.TTsGames.Logger.TTsLogger;
import TTs.TTsGames.Resources.*;
import TTs.lang.ExitCodes;
import TTs.lang.ImageString;
import TTs.lang.SoundString;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.Timer;

import static TTs.TTsGames.Games.DetektivThunder.DetektivThunder.startedClose;
import static TTs.TTsGames.Resources.Translations.TTsGames;

@SuppressWarnings("unused")
public class Main {
    public static TTsLogger logger;
    public static Translations translations;
    public static ConfigLoader configLoader;
    public static JsonReader jsonReader;

    public static String language, started = null, startedVersion = null, Username;
    public static String[] Versions, Languages, unerlaubteNamen;
    public static SoundString[][] Sounds;
    public static ImageString[][] Textures;
    public static int volume = 0;
    public static float[] AnimalMasterTime = new float[3];
    public static boolean MainWindow = true, dev, AnimalMaster, DetektivAdler, setUsername, fAM = false, fAMReset = false;
    public static boolean[] alreadyStarted = {false, false};
    public static double a;

    public static URI uri;
    public static FloatControl volumeControl;
    public static Image noTexture;
    public static JFrame[] windows = new JFrame[6];
    public static Color[] buttonColors;

    public static void main(String[] args) {
        logger = new TTsLogger(TTsLogger.Instance.TTS_GAMES);
        logger.debug("Initialized Logger");

        jsonReader = new JsonReader();
        Versions = jsonReader.getVersions();
        Languages = jsonReader.getLanguages();
        Textures = jsonReader.getTextures();
        Sounds = jsonReader.getSounds();
        unerlaubteNamen = jsonReader.UnallowedUsernames();
        noTexture = new ImageString(jsonReader.getNoTextureFile()).toImage();
        buttonColors = jsonReader.getColors();
        logger.unimportant(unerlaubteNamen);
        logger.debug("Loaded JSON Files");

        configLoader = new ConfigLoader();
        volume = configLoader.getVolume();
        language = configLoader.getLanguage();
        dev = configLoader.isDevVersionsEnabled();
        Username = configLoader.getUsername();
        setUsername = !Objects.equals(Username, "௹⨌{UsernameDe}");
        AnimalMasterTime[2] = configLoader.getAnimalMasterTimeRecord();
        a = configLoader.getAnimal_master_size_multiplier();
        logger.debug("Loaded config");

        translations = new Translations();
        if (Objects.equals(Username, "௹⨌{UsernameDe}")) {
            Username = "↺";
        }
        translations.translate();
        logger.debug("Loaded translations");

        logger.info("TTs Games " + Versions[0] + " is starting");
        new Window(false);
        logger.debug("Opened Window");

        timer(() -> {
            if (started != null) {
                logger.check("Check  Started: " + started + " Version: " + startedVersion);
            } else {
                logger.check("Check  Started: " + "nothing");
            }
        }, 0,1000);
    }

    public static Timer timer(Runnable task, long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay);
        return timer;
    }
    public static Timer timer(Runnable task, long delay, long period) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay, period);
        return timer;
    }

    public static void close() {
        logger.debug("Shutting Down");
        Arrays.stream(windows)
                .filter(Objects :: nonNull)
                .forEach(JFrame::dispose);
        logger.debug("exited Window");
        System.exit(ExitCodes.NO_ERROR);
    }


    public static void WindowOperations(int window, boolean resizable, Dimension size, Point location, ImageString icon, Component background) {
        windows[window].addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                switch (window) {
                    case 0 -> close();
                    case 1 -> {
                        MainWindow = true;
                        windows[window].setVisible(false);
                        started = null;
                        AnimalMaster = false;
                        logger.setInstance(TTsLogger.Instance.TTS_GAMES);
                        timer(() -> windows[window].setVisible(AnimalMaster), 0, 100);
                    }
                    case 2 -> {
                        MainWindow = true;
                        windows[window].setVisible(false);
                        started = null;
                        DetektivAdler = false;
                        logger.setInstance(TTsLogger.Instance.TTS_GAMES);
                        timer(() -> windows[window].setVisible(DetektivAdler), 0, 100);
                    }
                    case 3 -> {
                        if (!startedClose) {
                            startedClose = true;
                            new Close(windows[window], windows[window].getLocation());
                        }
                    }
                    case 4 -> {
                        windows[window].dispose();
                        startedClose = false;
                    }
                    case 5 -> windows[window].dispose();
                }
            }
        });
        windows[window].setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        windows[window].setResizable(resizable);
        windows[window].setSize(size);
        windows[window].setLocation(location);
        windows[window].setIconImage(icon.toImage());
        windows[window].getContentPane().setBackground(Color.LIGHT_GRAY);
        windows[window].setLayout(new BorderLayout());
        windows[window].add(background);
        windows[window].setVisible(true);
    }
    public static void WindowOperations(int window, boolean resizable, Dimension size, Point location, ImageString icon) {
        windows[window].addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                switch (window) {
                    case 0 -> close();
                    case 1 -> {
                        MainWindow = true;
                        windows[window].setVisible(false);
                        started = null;
                        AnimalMaster = false;
                        logger.setInstance(TTsLogger.Instance.TTS_GAMES);
                        timer(() -> windows[window].setVisible(AnimalMaster), 0, 100);
                    }
                    case 2 -> {
                        MainWindow = true;
                        windows[window].setVisible(false);
                        started = null;
                        DetektivAdler = false;
                        logger.setInstance(TTsLogger.Instance.TTS_GAMES);
                        timer(() -> windows[window].setVisible(DetektivAdler), 0, 100);
                    }
                    case 3 -> {
                        if (!startedClose) {
                            startedClose = true;
                            new Close(windows[window], windows[window].getLocation());
                        }
                    }
                    case 4 -> {
                        windows[window].setVisible(false);
                        startedClose = false;
                    }
                }
            }
        });
        windows[window].setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        windows[window].setResizable(resizable);
        windows[window].setSize(size);
        windows[window].setLocation(location);
        windows[window].setIconImage(icon.toImage());
        windows[window].getContentPane().setBackground(Color.LIGHT_GRAY);
        windows[window].setLayout(new BorderLayout());
        windows[window].setVisible(true);
    }

    public static void openWebpage(String url) {
        if (!url.startsWith("https://")) {
            url = "https://" + url;
        }
        try {
            uri = new URI(url);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(uri);
                }
            }
        } catch (Exception e) {
            logger.warn("Failed to open Webpage " + url + " Error code: ", e);
        }
    }

    public static String[] IntS(int min, int max) {
        int[] Ints = new int[max - min + 1];
        for (int i = 0; i < max - min + 1; i++) {
            Ints[i] = i;
        }
        String[] ints = new String[Ints.length];
        for (int i = 0; i < Ints.length; i++) {
            ints[i] = String.valueOf(Ints[i]);
        }
        return ints;
    }
    public static int[] Int(int min, int max) {
        int[] Ints = new int[max - min + 1];
        for (int i = min; i < max + 1; i++) {
            Ints[i] = i;
        }
        return Ints;
    }

    public static String checkTime(float time) {
        if (time == -1) return TTsGames[22];
        else return String.valueOf(time);
    }


    public static class Audio {
        public static void setVolume(float volume) {
            if (volumeControl != null) {
                float min = volumeControl.getMinimum();
                float max = volumeControl.getMaximum();
                volume = min + (volume / 100) * (max - min);
                if (volumeControl != null) volumeControl.setValue(Math.min(Math.max(volume, min), max));
            }
        }
    }
}
