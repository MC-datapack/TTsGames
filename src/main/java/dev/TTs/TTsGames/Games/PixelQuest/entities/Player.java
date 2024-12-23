package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.gui.BasicInventory;
import dev.TTs.TTsGames.Games.PixelQuest.item.*;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import static dev.TTs.TTsGames.Main.*;
import static dev.TTs.resources.Translations.PixelQuest;

public class Player extends GameObject implements Consumer, Damageable {
    private final int speed;
    private Set<Integer> pressedKeys;
    public final BasicInventory inventory;

    private final int maxHealth = 50;
    private final int maxSaturation = 50;
    private int health = maxHealth;
    private int saturation = maxSaturation;

    private final Image healthImage = Textures[4][1][0].toImage();
    private final Image saturationImage = Textures[4][1][1].toImage();

    private boolean showInventory = false;
    private long lastSetShowInventory = 1000;
    private long lastDropped = 1000;
    private long lastEaten = 1000;

    private boolean moving = false;
    private DamageReason damageReason;

    public Player(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.pressedKeys = new HashSet<>();

        inventory = new BasicInventory(36);
    }

    @Override
    public void update() {
        keyActions();
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
            health = maxHealth;
            saturation = maxSaturation;
        }
    }

    public void keyActions() {
        boolean moved = false;

        if ((pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_UP)) && y >= 0) {
            y -= speed;
            moved = true;
        }
        if ((pressedKeys.contains(KeyEvent.VK_S) || pressedKeys.contains(KeyEvent.VK_DOWN)) && y <= 700 - 32) {
            y += speed;
            moved = true;
        }
        if ((pressedKeys.contains(KeyEvent.VK_A) || pressedKeys.contains(KeyEvent.VK_LEFT)) && x >= 0) {
            x -= speed;
            moved = true;
        }
        if ((pressedKeys.contains(KeyEvent.VK_D) || pressedKeys.contains(KeyEvent.VK_RIGHT)) && x <= 730 - 32) {
            x += speed;
            moved = true;
        }

        moving = moved;

        if (pressedKeys.contains(KeyEvent.VK_F)) {
            if (System.currentTimeMillis() - lastEaten >= 1000) {
                for (int i = 0; i < 27; i++) {
                    ItemStack currentItem = inventory.getItemStack(i);
                    if (currentItem != null && currentItem.getItem() instanceof FoodItem) {
                        consume(currentItem);
                        break;
                    }
                }
                lastEaten = System.currentTimeMillis();
            }
        }
        if (pressedKeys.contains(KeyEvent.VK_E)) {
            if (System.currentTimeMillis() - lastSetShowInventory >= 250) {
                showInventory = !showInventory;
                lastSetShowInventory = System.currentTimeMillis();
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_L)) {
            inventory.addItem(new ItemStack(Items.COOKED_FOX));
        }

        if (pressedKeys.contains(KeyEvent.VK_Q)) {
            if (System.currentTimeMillis() - lastDropped >= 250) {
                inventory.dropSelectedStack();
                lastDropped = System.currentTimeMillis();
            }
        }
    }


    @Override
    public void consume(ItemStack item) {
        logger.debug("Before eating: Inventory: %s", Arrays.toString(inventory.getItems()));
        inventory.removeItem(item);
        Sounds[4][0].playSound();
        logger.debug("After eating: Inventory: %s", Arrays.toString(inventory.getItems()));
        if (item.getItem() instanceof FoodItem foodItem) {
            saturate(foodItem.saturation());
        }
    }

    @Override
    public void render(Graphics g) {
        if (!died()) {
            // Draw the player
            g.drawImage(id().getEntityTexture().toImage(), x, y, null);

            // Draw health points
            for (int i = 0; i < health; i++) {
                g.drawImage(healthImage, 10 + (i * 10), 700, null);
            }

            // Draw saturation points
            for (int i = 0; i < saturation; i++) {
                g.drawImage(saturationImage, 10 + (i * 10), 730, null);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.drawString(PixelQuest[2] + ": " + inventory.getWeight(), 690, 670);
            g.drawString(PixelQuest[0] + ": " + health, 690, 700);
            g.drawString(PixelQuest[1] + ": " + saturation, 690, 730);

            if (showInventory) {
                inventory.paintInventory(g);
            }
        }
    }


    @Override
    public void onCollision(GameObject with) {
        logger.info("Collision detected with object: %s", with.getClass().getName());

        if (with instanceof ItemEntity) {
            ItemEntity withI = (ItemEntity) with;
            ItemStack itemStack = withI.getStack();

            if (itemStack != null) {
                logger.info("ItemEntity stack: %s", itemStack);
                inventory.addItem(itemStack);
                PixelQuestGame.game.removeGameObject(withI);
                logger.info("Added ItemStack to inventory: %s", itemStack);
            } else {
                logger.error("ItemStack is null in ItemEntity collision");
            }
        } else {
            logger.warn("Collision with non-ItemEntity object: %s", with.getClass().getName());
        }
    }

    @Override
    public Identifier id() {
        return new Identifier("player");
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
        health -= i;
        damageReason = DamageReason.UNKNOWN;
    }

    @Override
    public void damage(int i, DamageReason reason) {
        health -= i;
        damageReason = reason;
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
    public String deathReason() {
        return damageReason.getMessage();
    }
}
