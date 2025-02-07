package dev.TTs.TTsGames.datagen;

import dev.TTs.TTsGames.Main;

public class Datagen {
    public static void main(String[] args) {
        Main.load(new String[]{"noLog"});
        Pack pack = new Pack();

        pack.addAllProvider();

        Main.deMain(new String[]{"noLog"});
    }
}
