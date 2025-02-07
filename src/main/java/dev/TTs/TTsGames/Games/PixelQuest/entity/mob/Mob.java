package dev.TTs.TTsGames.Games.PixelQuest.entity.mob;

import dev.TTs.TTsGames.Games.PixelQuest.entity.DamageReason;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.item.WeaponItem;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;

import java.awt.*;

import static dev.TTs.TTsGames.Main.timer;

public abstract class Mob extends Entity implements Damageable {
    protected final int speed;
    protected int direction;

    private final int maxHealth;
    private int health;
    private transient DamageReason damageReason;

    protected transient Color tintColor = new Color(0, 0, 0, 0);

    public Mob(int x, int y, int width, int height, int speed, int maxHealth) {
        super(x, y, width, height);
        this.speed = speed;
        this.direction = 0;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    @Override
    public void update() {
        if (tintColor == null) {
            tintColor = new Color(0,0,0,0);
        }
        deathAction();

        switch (direction) {
            case 0 -> y -= speed;
            case 1 -> y += speed;
            case 2 -> x -= speed;
            case 3 -> x += speed;
        }
        if (Math.random() < 0.02) {
            direction = (int) (Math.random() * 4);
        }
    }

    public void deathAction() {
        if (died()) {
            dropItem();
            WorldSaving.world.removeGameObject(this);
        }
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(id().getEntityTexture().tintImage(tintColor, 0.4F), x, y, width, height, null);
    }

    @Override
    public void onCollision(Entity with) {
        if (with instanceof Player player) {
            if (!died()) {
                damage(player.inventory.getItem(36) instanceof WeaponItem weaponItem ? weaponItem.damage() : 1, DamageReason.PLAYER);
            }
        } else if (with instanceof Mob) {
            direction = (int) (Math.random() * 4);
        }
    }

    @Override
    public void dropItem() {
        WorldSaving.world.addMultipleGameObjects(PixelQuestJsonReader.readLootTable(this).dropAll(x, y, this));
    }

    @Override
    public void damage(int i) {
        damage(i, DamageReason.UNKNOWN);
    }

    @Override
    public void heal(int i) {
        health = Math.min(maxHealth, health + i);
    }

    @Override
    public boolean died() {
        return health <= 0;
    }

    @Override
    public void damage(int i, DamageReason reason) {
        damageReason = reason;
        tintColor = Color.RED;
        health -= i;
        timer(() -> tintColor = new Color(0, 0, 0, 0), 250);
    }

    @Override
    public String deathMessage() {
        return damageReason.getMessage();
    }

    @Override
    public DamageReason deathReason() {
        return damageReason;
    }
}
