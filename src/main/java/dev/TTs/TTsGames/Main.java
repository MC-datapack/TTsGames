package dev.TTs.TTsGames;

import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.lang.ImageString;
import dev.TTs.lang.*;
import dev.TTs.TTsGames.Games.DetectiveThunder.Close;
import dev.TTs.resources.ConfigLoader;
import dev.TTs.resources.Configs;
import dev.TTs.resources.Json.JsonReader;
import dev.TTs.resources.Json.Text;
import dev.TTs.resources.Translations;
import dev.TTs.swing.WindowInformation;
import dev.TTs.swing.TFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.net.*;
import java.util.*;
import java.util.Timer;

import static dev.TTs.TTsGames.Games.DetectiveThunder.DetectiveThunder.startedClose;

@SuppressWarnings("CanBeFinal")
public class Main {
    public static Logger logger;
    public static Translations translations;
    public static ConfigLoader configLoader;
    public static JsonReader jsonReader;

    public static String language, started = null, startedVersion = null, Username;
    public static String[] Versions, Languages, unerlaubteNamen;
    public static SoundString[][] Sounds;
    public static ImageString[][][] Textures;
    public static int volume = 0;
    public static double[] AMTime = new double[3];
    public static boolean MainWindow = true, dev, setUsername, fAM = false, fAMReset = false, subtitles;
    public static float a, de;

    public static BufferedImage noTexture;
    public static TFrame[] windows = new TFrame[8];
    public static Color[] buttonColors;
    public static Timer checkLoop;

    public static String javaVersion;

    public static void main(String[] args) {
        load(args);
        if (Objects.equals(Username, "௹⨌{UsernameDe}")) {
            Username = "↺";
        }

        translations = new Translations();
        logger.debug("Loaded translations: %s", translations.toString());

        logger.info("TTs Games %s is starting", Versions[0]);
        if (Array.dontContains(jsonReader.MainJSON.getSupportedJavaVersions(), javaVersion)) {
            new WrongJavaVersion();
            return;
        }

        new Window(false);
        logger.debug("Opened Window");

        checkLoop = timer(() -> {
            if (started != null) logger.check("Check  Java version: %s Started: %s Version: %s", javaVersion, started, startedVersion);
            else logger.check("Check  Java version: %s Started: nothing", javaVersion);
        }, 1000,1000);
    }

    public static void load(String[] args) {
        javaVersion = System.getProperty("java.version");

        logger = new TTsLogger(System.getProperty("user.home") + "/AppData/Roaming/TTsGames/logs/",true);
        if (args.length == 0 || !Objects.equals(args[0], "noLog")) {
            logger.error("Used Java version: %s args: %s", javaVersion, Arrays.toString(args));
            logger.debug("Initialized Logger");
        }

        jsonReader = new JsonReader("");
        Versions = jsonReader.MainJSON.getVersions();
        Languages = jsonReader.MainJSON.getLanguages();
        Textures = jsonReader.MainJSON.getTextures();
        Sounds = jsonReader.MainJSON.getSounds();
        unerlaubteNamen = jsonReader.UnallowedUsernames();
        noTexture = jsonReader.MainJSON.getNoTextureFile().toImage();
        buttonColors = jsonReader.AMColors();
        if (args.length == 0 || !Objects.equals(args[0], "noLog")) {
            logger.unimportant(Arrays.toString(unerlaubteNamen));
            logger.debug("Loaded JSON Files");
        }

        configLoader = new ConfigLoader();
        volume = configLoader.get(Configs.VOLUME);
        language = configLoader.get(Configs.LANGUAGE);
        dev = configLoader.get(Configs.DEV_VERSIONS);
        Username = configLoader.get(Configs.USERNAME);
        subtitles = configLoader.get(Configs.SUBTITLES);
        setUsername = !Objects.equals(Username, "௹⨌{UsernameDe}");
        AMTime[2] = configLoader.get(Configs.AM_RECORD);
        a = configLoader.get(Configs.AM_SIZE_MULTIPLIER);
        de = configLoader.get(Configs.DE_SIZE_MULTIPLIER);
        if (args.length == 0 || !Objects.equals(args[0], "noLog")) {
            logger.debug("Loaded config");
        }
    }

    public static void deMain(String[] args) {
        if (args.length == 0 || !Objects.equals(args[0], "noLog"))
            logger.error("Shutdown args: %s", Arrays.toString(args));
        PixelQuestGame.shutdown();
        if (checkLoop != null)
            checkLoop.cancel();
        if (args.length == 0 || !Objects.equals(args[0], "noLog"))
            logger.debug("Shutting Down");
        Arrays.stream(windows)
                .filter(Objects::nonNull)
                .forEach(TFrame::dispose);
        if (args.length == 0 || !Objects.equals(args[0], "noLog"))
            logger.debug("Closed Windows");
        logger.close();
        System.exit(ExitCodes.NO_ERROR);
    }

    @Buggy(reason = "Infinite Windows are created", fixVersion = "2.2", notFullFunction = true)
    public static void langChange() {
        logger.debug("Lang changed");
        Text.langChanged();
        translations.retranslate();
        //Arrays.stream(windows)
        //        .filter(Objects::nonNull)
        //        .forEach(TFrame::dispose);
        //windows = new TFrame[8];
        //windows[0] = new TFrame("TTsGames " + Versions[1]);
        //new Window(false);
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


    public static void WindowOperations(int window, WindowInformation information, Component background) {
        WindowOperations(window, information);
        windows[window].add(background);
    }

    @SuppressWarnings("MagicConstant")
    public static void WindowOperations(int window, WindowInformation information) {
        if (information.defaultCloseOperation() == 0) {
            windows[window].addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    switch (window) {
                        case 0 -> deMain(new String[0]);
                        case 1, 2 -> {
                            MainWindow = true;
                            windows[window].dispose();
                            started = null;
                            logger.setInstance(Instance.TTS_GAMES);
                        }
                        case 3 -> {
                            if (!startedClose) {
                                startedClose = true;
                                new Close(windows[3], windows[3].getLocation());
                            }
                        }
                        case 4 -> {
                            windows[window].dispose();
                            startedClose = false;
                        }
                        case 5 -> windows[window].dispose();
                        case 7 -> {
                            PixelQuestGame.game.stop();
                            MainWindow = true;
                            windows[window].dispose();
                            started = null;
                            logger.setInstance(Instance.TTS_GAMES);
                        }
                    }
                }
            });
        }
        windows[window].setDefaultCloseOperation(information.defaultCloseOperation());
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

    public static String checkTime(double time, String fallback) {
        if (time == -1) return fallback;
        else return String.valueOf(time);
    }
}
