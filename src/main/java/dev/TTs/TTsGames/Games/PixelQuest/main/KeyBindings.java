package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.resources.Configs;

import java.util.HashMap;
import java.util.Map;

import static dev.TTs.TTsGames.Main.configLoader;

public class KeyBindings {
    private static final Map<String, Integer> keyBindings = new HashMap<>();

    public static void loadBindings() {
        keyBindings.put("MOVE_UP", configLoader.get(Configs.MOVE_UP));
        keyBindings.put("MOVE_DOWN", configLoader.get(Configs.MOVE_DOWN));
        keyBindings.put("MOVE_LEFT", configLoader.get(Configs.MOVE_LEFT));
        keyBindings.put("MOVE_RIGHT", configLoader.get(Configs.MOVE_RIGHT));
        keyBindings.put("EAT", configLoader.get(Configs.EAT));
        keyBindings.put("SHOW_INVENTORY", configLoader.get(Configs.SHOW_INVENTORY));
        keyBindings.put("DROP_ITEM", configLoader.get(Configs.DROP_ITEM));
        keyBindings.put("TRANSFER_ITEM", configLoader.get(Configs.TRANSFER_ITEM));
        keyBindings.put("SELECT", configLoader.get(Configs.SELECT));
        keyBindings.put("SPRINT", configLoader.get(Configs.SPRINT));
    }

    public static void setKeyBinding(String action, int keyCode) {
        keyBindings.put(action, keyCode);
        switch (action) {
            case "MOVE_UP" -> configLoader.set(Configs.MOVE_UP, keyCode);
            case "MOVE_DOWN" -> configLoader.set(Configs.MOVE_DOWN, keyCode);
            case "MOVE_LEFT" -> configLoader.set(Configs.MOVE_LEFT, keyCode);
            case "MOVE_RIGHT" -> configLoader.set(Configs.MOVE_RIGHT, keyCode);
            case "EAT" -> configLoader.set(Configs.EAT, keyCode);
            case "SHOW_INVENTORY" -> configLoader.set(Configs.SHOW_INVENTORY, keyCode);
            case "DROP_ITEM" -> configLoader.set(Configs.DROP_ITEM, keyCode);
            case "TRANSFER_ITEM" -> configLoader.set(Configs.TRANSFER_ITEM, keyCode);
            case "SELECT" -> configLoader.set(Configs.SELECT, keyCode);
            case "SPRINT" -> configLoader.set(Configs.SPRINT, keyCode);
        }
    }

    public static int getKeyBinding(String action) {
        return keyBindings.getOrDefault(action, -1);
    }
}
