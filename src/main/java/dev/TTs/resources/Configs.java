package dev.TTs.resources;

import dev.TTs.lang.Types;

public enum Configs {
    //Config
    LANGUAGE(Types.STRING, "language", "English"),
    DEV_VERSIONS(Types.BOOLEAN, "dev_versions", false),
    VOLUME(Types.INT, "volume", 100),
    USERNAME(Types.STRING, "username", "௹⨌{UsernameDe}"),
    AM_SIZE_MULTIPLIER(Types.DOUBLE, "animal_master_size_multiplier", 1.0),
    SUBTITLES(Types.BOOLEAN, "subtitles", false),
    //Statistics
    AM_RECORD(Types.DOUBLE, "animal_master_time_record", -1.0);

    private final Types type;
    private final String key;
    private final String defaultValue;

    Configs(Types type, String key, Object defaultValue) {
        this.type = type;
        this.key = key;
        this.defaultValue = String.valueOf(defaultValue);
    }

    public Types getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isValid(String value) {
        try {
            return switch (type) {
                case BOOLEAN -> value.equals("true") || value.equals("false");
                case INT -> {
                    Integer.parseInt(value);
                    yield true;
                }
                case DOUBLE -> {
                    Double.parseDouble(value);
                    yield true;
                }
                case FLOAT -> {
                    Float.parseFloat(value);
                    yield true;
                }
                case LONG -> {
                    Long.parseLong(value);
                    yield true;
                }
                case SHORT -> {
                    Short.parseShort(value);
                    yield true;
                }
                case BYTE -> {
                    Byte.parseByte(value);
                    yield true;
                }
                default -> true;
            };
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
