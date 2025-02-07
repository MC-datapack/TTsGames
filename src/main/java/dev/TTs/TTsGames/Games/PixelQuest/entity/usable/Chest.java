package dev.TTs.TTsGames.Games.PixelQuest.entity.usable;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;
import dev.TTs.lang.ImageString;
import dev.TTs.util.Identifier;
import dev.TTs.util.RandomUtil;

import java.awt.*;

public class Chest extends Entity {
    private final Identifier lootTable;
    private final Variants variant;

    public Chest(int x, int y, Identifier lootTable) {
        this(x, y, lootTable, RandomUtil.getRandom(Variants.values()));
    }

    public Chest(int x, int y, Identifier lootTable, Variants variant) {
        super(x, y, 32, 32);
        this.lootTable = lootTable;
        this.variant = variant;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(variant.getTexture().toImage(), x, y, width, height, null);
    }

    @Override
    public void onCollision(Entity with) {
        if (with instanceof Player) {
            dropItem();
            WorldSaving.world.removeGameObject(this);
        }
    }

    @Override
    public void dropItem() {
        WorldSaving.world.addMultipleGameObjects(PixelQuestJsonReader.readLootTable(lootTable).dropAll(x, y, this));
    }

    @Override
    public Identifier id() {
        return Identifier.of("chest");
    }

    public enum Variants {
        OAK(Identifier.of("oak")),
        SPRUCE(Identifier.of("spruce"));

        private final Identifier name;

        Variants(Identifier name) {
            this.name = name;
        }

        public ImageString getTexture() {
            return new ImageString("assets/textures/" + name.project() + "/entity/chest/" + name.name() + ".png");
        }
    }
}
