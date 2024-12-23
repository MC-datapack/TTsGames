package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public class Monster extends Enemy {
    public Monster(int x, int y, int speed) {
        super(x, y, speed, 1, 250);
    }

    @Override
    public void dropItem() {

    }

    @Override
    public Identifier id() {
        return new Identifier("monster");
    }
}
