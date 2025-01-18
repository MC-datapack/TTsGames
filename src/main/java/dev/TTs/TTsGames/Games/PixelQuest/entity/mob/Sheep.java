package dev.TTs.TTsGames.Games.PixelQuest.entity.mob;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.awt.*;

public class Sheep extends Mob {

    public Sheep() {
        this(0, 0, 0);
    }

    public Sheep(int x, int y, int speed) {
        super(x, y, 59, 59, speed, 5);
    }

    @Override
    public Identifier id() {
        return new Identifier("sheep");
    }

    @Override
    public Damageable get() {
        return this;
    }
}
