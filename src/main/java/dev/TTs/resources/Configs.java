package dev.TTs.resources;

import dev.TTs.lang.Types;

import java.awt.event.KeyEvent;
import java.math.BigInteger;

public enum Configs {
    //Config
    LANGUAGE(Types.STRING, "language", "English"),
    DEV_VERSIONS(Types.BOOLEAN, "dev_versions", false),
    VOLUME(Types.INT, "volume", 100),
    USERNAME(Types.STRING, "username", "௹⨌{UsernameDe}"),
    AM_SIZE_MULTIPLIER(Types.FLOAT, "animal_master_size_multiplier", 1.0F),
    DE_SIZE_MULTIPLIER(Types.FLOAT, "detective_eagle_size_multiplier", 1.0F),
    SUBTITLES(Types.BOOLEAN, "subtitles", false),
    //Statistics
    AM_RECORD(Types.DOUBLE, "animal_master_time_record", -1.0),
    //PixelQuest
    FULLSCREEN(Types.BOOLEAN, "pixel_quest.fullscreen", true),
    V_SYNC(Types.BOOLEAN, "pixel_quest.vsync", true),
    MOVE_UP(Types.INT, "pixel_quest.keybindings.move_up",KeyEvent.VK_W),
    MOVE_DOWN(Types.INT, "pixel_quest.keybindings.move_down", KeyEvent.VK_S),
    MOVE_LEFT(Types.INT, "pixel_quest.keybindings.move_left",KeyEvent.VK_A),
    MOVE_RIGHT(Types.INT, "pixel_quest.keybindings.move_right", KeyEvent.VK_D),
    EAT(Types.INT, "pixel_quest.keybindings.action_use",KeyEvent.VK_F),
    SHOW_INVENTORY(Types.INT, "pixel_quest.keybindings.show_inventory", KeyEvent.VK_E),
    ADD_ITEM(Types.INT, "pixel_quest.keybindings.add_item",KeyEvent.VK_L),
    DAMAGE(Types.INT, "pixel_quest.keybindings.damage", KeyEvent.VK_M),
    DROP_ITEM(Types.INT, "pixel_quest.keybindings.drop_item",KeyEvent.VK_Q),
    TRANSFER_ITEM(Types.INT, "pixel_quest.keybindings.transfer_item", KeyEvent.VK_T),
    SELECT(Types.INT, "pixel_quest.keybindings.select", KeyEvent.VK_X),
    SPRINT(Types.INT, "pixel_quest.keybindings.sprint", KeyEvent.VK_CONTROL);



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
                case BIG_INTEGER -> {
                    new BigInteger(value);
                    yield true;
                }
                default -> value != null;
            };
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Type: %s", name(), type);
    }
}
