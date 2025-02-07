package dev.TTs.TTsGames.Games.PixelQuest.entity;

import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.WorldSaving;
import dev.TTs.lang.ImageString;
import dev.TTs.util.Identifier;
import dev.TTs.util.RandomUtil;

import java.awt.*;

public class Tree extends Entity {
    private final Variants variant;

    public Tree(int x, int y) {
        this(x, y, RandomUtil.getRandom(Variants.values()));
    }

    public Tree(int x, int y, Variants variant) {
        super(x, y, variant.width, variant.height);
        this.variant = variant;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(variant.getTexture().toImage(), x, y, width, height,null);
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
        WorldSaving.world.addMultipleGameObjects(PixelQuestJsonReader.readLootTable(variant.lootTablePath()).dropAll(x, y, this));
    }

    @Override
    public Identifier id() {
        return Identifier.ofPixelQuest("tree");
    }

    public enum Variants {
        OAK(Identifier.of("oak"), 128, 128),
        SPRUCE(Identifier.of("spruce"), 118, 191);

        private final Identifier name;
        private final int width, height;

        Variants(Identifier name, int width, int height) {
            this.name = name;
            this.width = width;
            this.height = height;
        }

        public ImageString getTexture() {
            return new ImageString("assets/textures/" + name.project() + "/entity/tree/" + name.name() + ".png");
        }

        public Identifier lootTablePath() {
            return Identifier.of(name.project(), "tree/" + name.name());
        }
    }
}
