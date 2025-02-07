package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.TTsGames.Games.PixelQuest.gui.BasicArrayInventory;
import dev.TTs.TTsGames.Games.PixelQuest.item.*;
import dev.TTs.TTsGames.Games.PixelQuest.main.Renderer;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;
import dev.TTs.util.Identifier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.PixelQuest;

public final class Player extends Entity implements Consumer, Damageable, InventoryOwner {
    int speed;
    transient Set<Integer> pressedKeys;
    public BasicArrayInventory inventory;

    private static final int maxHealth = 50;
    private static final int maxSaturation = 50;
    private int health;
    private int saturation;

    private transient Image healthImage = Textures[4][1][0].toImage();
    private transient Image saturationImage = Textures[4][1][1].toImage();

    transient boolean showInventory = false;
    transient boolean moving = false;
    private transient DamageReason damageReason;
    private transient Color tintColor = new Color(0, 0, 0, 0);

    public Player(int x, int y) {
        super(x, y, 23, 19);
        this.speed = 5;
        this.pressedKeys = new HashSet<>();

        this.saturation = 50;
        this.health = 50;

        inventory = new BasicArrayInventory(37)
                .customSlotPosition(36, new Point(350, 20));
        //inventory.addItem(new ItemStack(Items.MUTTON, 5));
        //inventory.addItem(new ItemStack(Items.WOOL, 5));
    }

    @Override
    public void update() {
        Renderer.CameraX = x;
        Renderer.CameraY = y;
        PlayerKeyBoardActions.keyActions(this);
        if (!died()) {
            if (health < maxHealth && saturation > (maxSaturation - 10)) {
                if (ThreadLocalRandom.current().nextInt(250) == 50) {
                    heal(1);
                    unSaturate(ThreadLocalRandom.current().nextInt(0, 2));
                }
            }

            if (moving && saturation > 0 && ThreadLocalRandom.current().nextInt(500) == 50) {
                unSaturate(1);
            } else if (saturation > 0 && ThreadLocalRandom.current().nextInt(2000) == 50) {
                unSaturate(1);
            }

            if (saturation == 0 && ThreadLocalRandom.current().nextInt(100) == 50) {
                damage(1, DamageReason.STARVATION);
            }
        } else if (pressedKeys.contains(KeyEvent.VK_R)) {
            logger.warn("Respawning!!!!!!!!!!!!!!!!!!!!!!!!!");
            health = maxHealth;
            saturation = maxSaturation;
        }
    }

    @Override
    public void consume(ItemStack item) {
        if (item.getItem() instanceof FoodItem foodItem) {
            logger.debug("Before eating: ArrayInventory: %s", Arrays.toString(inventory.getItems()));
            inventory.removeItem(item);
            Sounds[4][0].playSound();
            logger.debug("After eating: ArrayInventory: %s", Arrays.toString(inventory.getItems()));
            saturate(logger.debug(foodItem.saturation()));
        } else {
            logger.warn("Tried eating non FoodItem item");
        }
    }

    @Override
    public boolean opened() {
        return showInventory;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        if (!died()) {
            g.drawImage(id().getEntityTexture().tintImage(tintColor), x, y, null);

            for (int i = 0; i < health; i++) {
                g.drawImage(healthImage, 10 + (i * 10), PixelQuestGame.height - 100, null);
            }

            for (int i = 0; i < saturation; i++) {
                g.drawImage(saturationImage, 10 + (i * 10), PixelQuestGame.height - 70, null);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString(PixelQuest[2] + ": " + inventory.getWeight(), 690, PixelQuestGame.height - 130);
            g.drawString(PixelQuest[0] + ": " + health, 690, PixelQuestGame.height - 100);
            g.drawString(PixelQuest[1] + ": " + saturation, 690, PixelQuestGame.height - 70);
            g.drawString("x: " + this.x + " y: " + this.y, 10, 55);

            inventory.drawSlot(g, 36);

            if (showInventory) {
                inventory.drawInventory(g);
            }
        }
    }

    @Override
    public void onCollision(Entity with) {
        logger.info("Collision detected with object: %s", with.getClass().getName());

        if (with instanceof ItemEntity withI) {
            ItemStack itemStack = withI.getStack();

            if (itemStack != null) {
                logger.info("ItemEntity stack: %s", itemStack);
                inventory.addItem(itemStack);
                WorldSaving.world.removeGameObject(withI);
                logger.info("Added ItemStack to inventory: %s", itemStack);
            } else {
                logger.error("ItemStack is null in ItemEntity collision");
            }
        } else {
            logger.warn("Collision with non-ItemEntity object: %s", with.getClass().getName());
        }
    }

    @Override
    public void dropItem() {}

    @Override
    public Identifier id() {
        return Identifier.ofPixelQuest("player");
    }

    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    public void setPressedKeys(Set<Integer> pressed) {
        pressedKeys = pressed;
    }

    @Override
    public void damage(int i) {
        damage(i, DamageReason.UNKNOWN);
    }

    @Override
    public void damage(int i, DamageReason reason) {
        tintColor = Color.RED;
        health -= i;
        damageReason = reason;
        timer(() -> tintColor = new Color(0, 0, 0, 0), 250);
    }

    @Override
    public void heal(int i) {
        health = Math.min(maxHealth, health + i);
    }

    @Override
    public void saturate(int i) {
        saturation = Math.min(maxSaturation, saturation + i);
    }

    @Override
    public void unSaturate(int i) {
        saturation = Math.max(0, saturation - i);
        if (saturation == 0) {
            damage(1, DamageReason.STARVATION);
        }
    }

    @Override
    public boolean died() {
        return health <= 0;
    }

    @Override
    public String deathMessage() {
        if (damageReason == null) return null;
        return damageReason.getMessage();
    }

    @Override
    public DamageReason deathReason() {
        return damageReason;
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        pressedKeys = new HashSet<>();
        healthImage = Textures[4][1][0].toImage();
        saturationImage = Textures[4][1][1].toImage();
        tintColor = new Color(0, 0, 0, 0);
    }
}
