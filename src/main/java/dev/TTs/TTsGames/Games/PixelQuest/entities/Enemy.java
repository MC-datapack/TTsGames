package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;

public abstract class Enemy extends Mob {
    private final int damage;
    private final int maxSmellDistance;

    public Enemy(int x, int y, int speed, int damage, int maxSmellDistance) {
        super(x, y, speed);
        this.damage = damage;
        this.maxSmellDistance = maxSmellDistance;
    }

    @Override
    public void update() {
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
        if (with instanceof Player) {
            PixelQuestGame.game.player.damage(damage, DamageReason.ENEMY);
        }
    }
}
