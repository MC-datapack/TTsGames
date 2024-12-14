package dev.TTs.resources;

import dev.TTs.lang.Types;

@SuppressWarnings("EnhancedSwitchMigration")
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
            switch (type) {
                case BOOLEAN:
                    return value.equals("true") || value.equals("false");
                case INT:
                    Integer.parseInt(value);
                    return true;
                case DOUBLE:
                    Double.parseDouble(value);
                    return true;
                case FLOAT:
                    Float.parseFloat(value);
                    return true;
                case LONG:
                    Long.parseLong(value);
                    return true;
                case SHORT:
                    Short.parseShort(value);
                    return true;
                case BYTE:
                    Byte.parseByte(value);
                    return true;
                default:
                    return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
