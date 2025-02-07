package dev.TTs.lang;

public non-sealed class DebugLogger extends TTsLogger {
    private final String name;

    public DebugLogger(String name) {
        super(System.getProperty("user.home") + "/AppData/Roaming/TTsGames/logs/", true);
        this.name = name;
        setInstance(Instance.TTS_GAMES);
    }

    @Override
    public String formatMessage(LogLevel level, String message, Object... args) {
        return String.format("%s[%s] <%c> %s<%s> %s%s[%s] %s%s",
                level.getColorCode(), getTime(), level.getImportance(),
                getInstance().getColorCode(), name, "\u001B[0m",
                level.getColorCode(), level.getName(), String.format(message, args), "\u001B[0m");
    }
}
