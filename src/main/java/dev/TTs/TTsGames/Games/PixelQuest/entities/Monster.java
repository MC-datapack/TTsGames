package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;
import dev.TTs.lang.ImageString;

import static dev.TTs.TTsGames.Main.Textures;

public class Monster extends Enemy {
    public Monster(int x, int y, int speed) {
        super(x, y, speed, 10, 500);
    }

    @Override
    public void dropItem() {

    }

    @Override
    public Identifier id() {
        return new Identifier("monster");
    }
}
