package dev.TTs.TTsGames;

import dev.TTs.lang.*;
import dev.TTs.TTsGames.Games.DetektivThunder.Close;
import dev.TTs.TTsGames.Resources.ConfigLoader;
import dev.TTs.TTsGames.Resources.Json.JsonReader;
import dev.TTs.TTsGames.Resources.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.Timer;

import static dev.TTs.TTsGames.Games.DetektivThunder.DetektivThunder.startedClose;

@SuppressWarnings("unused")
public final class Main {
    public static TTsLogger logger;
    public static Translations translations;
    public static ConfigLoader configLoader;
    public static JsonReader jsonReader;

    public static String language, started = null, startedVersion = null, Username;
    public static String[] Versions, Languages, unerlaubteNamen;
    public static SoundString[][] Sounds;
    public static ImageString[][] Textures;
    public static int volume = 0;
    public static float[] AMTime = new float[3];
    public static boolean MainWindow = true, dev, AnimalMaster, DetektivAdler, setUsername, fAM = false, fAMReset = false;
    public static boolean[] alreadyStarted = {false, false};
    public static double a;

    public static URI uri;
    public static Image noTexture;
    public static JFrame[] windows = new JFrame[6];
    public static Color[] buttonColors;
    public static Timer checkLoop;

    @Run(name = "main")
    public static void main(String[] args) throws TTsException {
        logger = new TTsLogger(Instance.TTS_GAMES, true);
        logger.error("args: " + Arrays.toString(args));
        logger.debug("Initialized Logger");

        jsonReader = new JsonReader("");
        Versions = jsonReader.MainJSON.getVersions();
        Languages = jsonReader.MainJSON.getLanguages();
        Textures = jsonReader.MainJSON.getTextures();
        Sounds = jsonReader.MainJSON.getSounds();
        unerlaubteNamen = jsonReader.UnallowedUsernames();
        noTexture = jsonReader.MainJSON.getNoTextureFile().toImage();
        buttonColors = jsonReader.AMColors();
        logger.unimportant(unerlaubteNamen);
        logger.debug("Loaded JSON Files");

        configLoader = new ConfigLoader();
        volume = configLoader.getVolume();
        language = configLoader.getLanguage();
        dev = configLoader.isDevVersionsEnabled();
        Username = configLoader.getUsername();
        setUsername = !Objects.equals(Username, "௹⨌{UsernameDe}");
        AMTime[2] = configLoader.getAnimalMasterTimeRecord();
        a = configLoader.getAnimal_master_size_multiplier();
        logger.debug("Loaded config");

        if (Objects.equals(Username, "௹⨌{UsernameDe}")) {
            Username = "↺";
        }
        translations = new Translations();
        logger.debug("Loaded translations");

        logger.info("TTs Games " + Versions[0] + " is starting");
        new Window(false);
        logger.debug("Opened Window");

        checkLoop = timer(() -> {
            if (started != null) logger.check("Check  Started: " + started + " Version: " + startedVersion);
            else logger.check("Check  Started: " + "nothing");
        }, 1000,1000);
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
                .filter(Objects::nonNull)
                .forEach(JFrame::dispose);
        logger.debug("exited Window");
        System.exit(ExitCodes.NO_ERROR);
    }


    public static void WindowOperations(int window, boolean resizable, Dimension size, Point location, ImageString icon, Component background) {
        WindowOperations(window, resizable, size, location, icon);
        windows[window].add(background);
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
                        logger.setInstance(Instance.TTS_GAMES);
                        timer(() -> windows[window].setVisible(AnimalMaster), 0, 100);
                    }
                    case 2 -> {
                        MainWindow = true;
                        windows[window].setVisible(false);
                        started = null;
                        DetektivAdler = false;
                        logger.setInstance(Instance.TTS_GAMES);
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
        windows[window].setVisible(true);
    }

    public static void openWebpage(String urlString) {
        try {
            URI uri = new URI(ensureHttps(urlString));
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            }
        } catch (Exception e) {
            logger.warn("Failed to open webpage", e);
        }
    }

    private static String ensureHttps(String urlString) {
        if (!urlString.startsWith("https://") && !urlString.startsWith("http://")) {
            urlString = "https://" + urlString; }
        return urlString;
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

    public static String checkTime(float time, String fallback) {
        if (time == -1) return fallback;
        else return String.valueOf(time);
    }
}
