package dev.TTs.TTsGames.Games.PixelQuest.entity.mob;

import dev.TTs.TTsGames.Games.PixelQuest.entity.DamageReason;
import dev.TTs.TTsGames.Games.PixelQuest.entity.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;

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
        int dx = PixelQuestGame.game.player.getLocation().x - x;
        int dy = PixelQuestGame.game.player.getLocation().y - y;

        int distance = (int) Math.sqrt(dx * dx + dy * dy);

        if (distance >= maxSmellDistance) {
            super.update();
        } else if (distance > speed) {
            x += (dx * speed) / distance; y += (dy * speed) / distance;
        } else {
            x = PixelQuestGame.game.player.getLocation().x;
            y = PixelQuestGame.game.player.getLocation().y;
        }
    }

    @Override
    public void onCollision(GameObject with) {
        super.onCollision(with);
        if (with instanceof Player) {
            PixelQuestGame.game.player.damage(damage, DamageReason.ENEMY);
        }
    }
}
