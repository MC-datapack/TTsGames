package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.TTsGames.Games.PixelQuest.gui.BasicArrayInventory;
import dev.TTs.TTsGames.Games.PixelQuest.item.*;
import dev.TTs.TTsGames.Games.PixelQuest.main.KeyBindings;
import dev.TTs.TTsGames.Games.PixelQuest.main.Renderer;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.usable.CraftingTable;
import dev.TTs.TTsGames.Games.PixelQuest.usable.Furnace;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

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

public final class Player extends GameObject implements Consumer, Damageable {
    private final int speed;
    private transient Set<Integer> pressedKeys;
    public final BasicArrayInventory inventory;

    private static int maxHealth;
    private static int maxSaturation;
    private int health;
    private int saturation;

    private transient Image healthImage = Textures[4][1][0].toImage();
    private transient Image saturationImage = Textures[4][1][1].toImage();

    private transient boolean showInventory = false;
    private transient long lastSetShowInventory = 1000;
    private transient long lastDropped = 1000;
    private transient long lastEaten = 1000;
    private transient long lastSelected = 1000;

    private transient boolean moving = false;
    private transient DamageReason damageReason;

    private transient Color tintColor = new Color(0, 0, 0, 0);

    public Player(int x, int y) {
        super(x, y, 23, 19);
        this.speed = 5;
        this.pressedKeys = new HashSet<>();

        maxSaturation = 50;
        maxHealth = 50;

        this.saturation = 50;
        this.health = 50;

        inventory = new BasicArrayInventory(37)
                .customSlotPosition(36, new Point(350, 20));
        inventory.addItem(new ItemStack(Items.MUTTON, 5));
        inventory.addItem(new ItemStack(Items.WOOL, 5));
    }

    @Override
    public void update() {
        Renderer.CameraX = x;
        Renderer.CameraY = y;
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
        if (pressedKeys == null) pressedKeys = new HashSet<>();
        boolean moved = false;

        if (!died()) {
            if (pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_UP"))) {
                y -= speed;
                moved = true;
            }
            if (pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_DOWN"))) {
                y += speed;
                moved = true;
            }
            if (pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_LEFT"))) {
                x -= speed;
                moved = true;
            }
            if (pressedKeys.contains(KeyBindings.getKeyBinding("MOVE_RIGHT"))) {
                x += speed;
                moved = true;
            }

            moving = moved;

            if (pressedKeys.contains(KeyBindings.getKeyBinding("EAT"))) {
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
            if (pressedKeys.contains(KeyBindings.getKeyBinding("SHOW_INVENTORY"))) {
                if (System.currentTimeMillis() - lastSetShowInventory >= 250) {
                    showInventory = !showInventory;
                    lastSetShowInventory = System.currentTimeMillis();
                }
            }

            if (pressedKeys.contains(KeyBindings.getKeyBinding("DROP_ITEM"))) {
                if (System.currentTimeMillis() - lastDropped >= 250) {
                    PixelQuestGame.drop = true;
                    inventory.dropSelectedStack();
                    lastDropped = System.currentTimeMillis();
                }
            }

            if (pressedKeys.contains(KeyBindings.getKeyBinding("TRANSFER_ITEM"))) {
                transferItem();
            }

            if (pressedKeys.contains(KeyBindings.getKeyBinding("SELECT"))) {
                if (System.currentTimeMillis() - lastSelected >= 200) {
                    if (inventory.getSelectedStack() != null) {
                        if (inventory.getSelectedIndex() == 36) {
                            inventory.readdItem(inventory.getSelectedIndex());
                        } else {
                            inventory.moveItems(inventory.getSelectedIndex(), 36);
                        }
                    }
                    lastSelected = System.currentTimeMillis();
                }
            }
        }
    }

    public void transferItem() {
        ItemStack selectedItem = inventory.getSelectedStack();
        if (selectedItem != null) {
            if (PixelQuestGame.getFurnace().open) {
                Furnace furnace = PixelQuestGame.getFurnace();
                furnace.receiveItem(selectedItem);
                inventory.removeItems(selectedItem);
            } else if (PixelQuestGame.getCraftingTable().open) {
                CraftingTable craftingTable = PixelQuestGame.getCraftingTable();
                craftingTable.receiveItem(selectedItem);
                inventory.removeItems(selectedItem);
            }
        }
    }

    @Override
    public void consume(ItemStack item) {
        logger.debug("Before eating: ArrayInventory: %s", Arrays.toString(inventory.getItems()));
        inventory.removeItem(item);
        Sounds[4][0].playSound();
        logger.debug("After eating: ArrayInventory: %s", Arrays.toString(inventory.getItems()));
        if (item.getItem() instanceof FoodItem foodItem) {
            saturate(foodItem.saturation());
        }
    }

    @Override
    public void render(Graphics g, int x, int y) {
        if (!died()) {
            g.drawImage(id().getEntityTexture().tintImage(tintColor), x, y, null);

            for (int i = 0; i < health; i++) {
                g.drawImage(healthImage, 10 + (i * 10), 700, null);
            }

            for (int i = 0; i < saturation; i++) {
                g.drawImage(saturationImage, 10 + (i * 10), 730, null);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString(PixelQuest[2] + ": " + inventory.getWeight(), 690, 670);
            g.drawString(PixelQuest[0] + ": " + health, 690, 700);
            g.drawString(PixelQuest[1] + ": " + saturation, 690, 730);
            g.drawString("x: " + this.x + " y: " + this.y, 10, 55);

            inventory.drawSlot(g, 36);

            if (showInventory) {
                inventory.drawInventory(g);
            }
        }
    }


    @Override
    public void onCollision(GameObject with) {
        logger.info("Collision detected with object: %s", with.getClass().getName());

        if (with instanceof ItemEntity withI) {
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
        damage(i, DamageReason.UNKNOWN);
    }

    @Override
    public void damage(int i, DamageReason reason) {
        tintColor = Color.RED;
        health -= i;
        damageReason = reason;
        timer(() -> tintColor = new Color(0, 0,0, 0), 250);
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
        return damageReason.getMessage();
    }

    @Override
    public DamageReason deathReason() {
        return damageReason;
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.readObject();
        healthImage = Textures[4][1][0].toImage();
        saturationImage = Textures[4][1][1].toImage();
        tintColor = new Color(0, 0, 0, 0);
    }
}
