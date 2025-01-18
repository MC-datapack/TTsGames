package dev.TTs.TTsGames.Games.PixelQuest.entity.mob;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public class Fox extends Mob {
    public Fox() {
        this(0, 0, 0);
    }

    public Fox(int x, int y, int speed) {
        super(x, y, 21, 21, speed, 5);
    }

    @Override
    public Identifier id() {
        return new Identifier("fox");
    }

    @Override
    public Damageable get() {
        return this;
    }
}
