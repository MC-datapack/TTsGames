package dev.TTs.TTsGames.Games.PixelQuest.entity.mob;

import dev.TTs.TTsGames.Games.PixelQuest.entity.DamageReason;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;

import java.awt.*;

public abstract class Enemy extends Mob {
    private final int damage;
    private final int maxSmellDistance;

    public Enemy(int x, int y, int width, int height, int speed, int damage, int maxSmellDistance, int maxDamage) {
        super(x, y, width, height, speed, maxDamage);
        this.damage = damage;
        this.maxSmellDistance = maxSmellDistance;
    }

    @Override
    public void update() {
        tintColor = new Color(0,0,0,0);
        deathAction();
        int dx = WorldSaving.world.player.getLocation().x - x;
        int dy = WorldSaving.world.player.getLocation().y - y;

        int distance = (int) Math.sqrt(dx * dx + dy * dy);

        if (distance >= maxSmellDistance) {
            super.update();
        } else if (distance > speed) {
            x += (dx * speed) / distance; y += (dy * speed) / distance;
        } else {
            x = WorldSaving.world.player.getLocation().x;
            y = WorldSaving.world.player.getLocation().y;
        }
    }

    @Override
    public void onCollision(Entity with) {
        super.onCollision(with);
        if (with instanceof Player) {
            WorldSaving.world.player.damage(damage, DamageReason.ENEMY);
        }
    }
}
