package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.item.Droppable;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;

import java.awt.*;

public abstract class Mob extends GameObject implements Droppable {
    protected final int speed;
    protected int direction;

    public Mob(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.direction = 0;
    }

    @Override
    public void update() {
        // Simple AI logic: move in a fixed direction and change direction on collision
        switch (direction) {
            case 0 -> {
                if (y >= 0) {
                    y -= speed;
                }
            }
            case 1 -> {
                if (y <= 750) {
                    y += speed;
                }
            }
            case 2 -> {
                if (x >= 0) {
                    x -= speed;
                }
            }
            case 3 -> {
                if (x <= 750) {
                    x += speed;
                }
            }
        }
        // Change the direction occasionally
        if (Math.random() < 0.02) {
            direction = (int) (Math.random() * 4);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(id().getEntityTexture().toImage(), x, y, null);
    }

    @Override
    public void onCollision(GameObject with) {
        if (with instanceof Player) {
            dropItem();
            PixelQuestGame.game.removeGameObject(this);
        } else if (with instanceof Mob) {
            direction = (int) (Math.random() * 4);
        }
    }
}
