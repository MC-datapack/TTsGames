package dev.TTs.TTsGames.Games.PixelQuest.entity.mob;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.util.Identifier;

public class Monster extends Enemy {

    public Monster() {
        this(0, 0, 0);
    }
    public Monster(int x, int y, int speed) {
        super(x, y, 16, 20, speed, 1, 250, 10);
    }

    @Override
    public Identifier id() {
        return Identifier.ofPixelQuest("monster");
    }
}
