package dev.TTs.TTsGames;

import dev.TTs.lang.*;
import dev.TTs.TTsGames.Games.DetectiveThunder.Close;
import dev.TTs.resources.ConfigLoader;
import dev.TTs.resources.Json.JsonReader;
import dev.TTs.resources.Translations;
import dev.TTs.swing.FrameInformation;
import dev.TTs.swing.TFrame;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import java.util.Timer;

import static dev.TTs.TTsGames.Games.DetectiveThunder.DetectiveThunder.startedClose;

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
    public static boolean MainWindow = true, dev, AnimalMaster, DetektivAdler, setUsername, fAM = false, fAMReset = false, subtitles;
    public static boolean[] alreadyStarted = {false, false};
    public static double a;

    public static Image noTexture;
    public static TFrame[] windows = new TFrame[6];
    public static Color[] buttonColors;
    public static Timer checkLoop;

    public static void main(String[] args) {
        logger = new TTsLogger(System.getProperty("user.home") + "/AppData/Roaming/TTsGames/logs/",true);
        logger.error("args: %s", Arrays.toString(args));
        logger.debug("Initialized Logger");

        jsonReader = new JsonReader("");
        Versions = jsonReader.MainJSON.getVersions();
        Languages = jsonReader.MainJSON.getLanguages();
        Textures = jsonReader.MainJSON.getTextures();
        Sounds = jsonReader.MainJSON.getSounds();
        unerlaubteNamen = jsonReader.UnallowedUsernames();
        noTexture = jsonReader.MainJSON.getNoTextureFile().toImage();
        buttonColors = jsonReader.AMColors();
        logger.unimportant(Arrays.toString(unerlaubteNamen));
        logger.debug("Loaded JSON Files");

        configLoader = new ConfigLoader();
        volume = configLoader.getVolume();
        language = configLoader.getLanguage();
        dev = configLoader.isDevVersionsEnabled();
        Username = configLoader.getUsername();
        subtitles = configLoader.getSubtitles();
        setUsername = !Objects.equals(Username, "௹⨌{UsernameDe}");
        AMTime[2] = configLoader.getAlMTimeRecord();
        a = configLoader.getAnimal_master_size_multiplier();
        logger.debug("Loaded config");

        if (Objects.equals(Username, "௹⨌{UsernameDe}")) {
            Username = "↺";
        }

        translations = new Translations();
        logger.debug("Loaded translations: %s", translations.toString());

        logger.info("TTs Games %s is starting", Versions[0]);
        new Window(false);
        logger.debug("Opened Window");

        checkLoop = timer(() -> {
            if (started != null) logger.check("Check  Started: %s Version: %s", started, startedVersion);
            else logger.check("Check  Started: nothing");
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

    public static void shutDown() {
        checkLoop.cancel();
        logger.debug("Shutting Down");
        Arrays.stream(windows)
                .filter(Objects::nonNull)
                .forEach(JFrame::dispose);
        logger.debug("Closed Windows");
        logger.close();
        System.exit(ExitCodes.NO_ERROR);
    }


    public static void WindowOperations(int window, FrameInformation information, Component background) {
        WindowOperations(window, information);
        windows[window].add(background);
    }
    public static void WindowOperations(int window, FrameInformation information) {
        windows[window].closingOperation(() -> {
            switch (window) {
                case 0 -> shutDown();
                case 1 -> {
                    MainWindow = true;
                    windows[1].setVisible(false);
                    started = null;
                    AnimalMaster = false;
                    logger.setInstance(Instance.TTS_GAMES);
                    timer(() -> windows[1].setVisible(AnimalMaster), 0, 100);
                }
                case 2 -> {
                    MainWindow = true;
                    windows[2].setVisible(false);
                    started = null;
                    DetektivAdler = false;
                    logger.setInstance(Instance.TTS_GAMES);
                    timer(() -> windows[2].setVisible(DetektivAdler), 0, 100);
                }
                case 3 -> {
                    if (!startedClose) {
                        startedClose = true;
                        new Close(windows[3], windows[3].getLocation());
                    }
                }
                case 4 -> {
                    windows[4].dispose();
                    startedClose = false;
                }
                case 5 -> windows[5].dispose();
            }
        });
        windows[window].setResizable(information.resizable());
        windows[window].setSize(information.size());
        windows[window].setLocation(information.location());
        windows[window].setIconImage(information.icon().toImage());
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
            logger.warn("Failed to open webpage: %s", e);
        }
    }

    private static String ensureHttps(String urlString) {
        if (!urlString.startsWith("https://") && !urlString.startsWith("http://")) {
            urlString = "https://" + urlString; }
        return urlString;
    }

    public static String checkTime(float time, String fallback) {
        if (time == -1) return fallback;
        else return String.valueOf(time);
    }
}
