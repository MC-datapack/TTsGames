package dev.TTs.TTsGames.Games.PixelQuest.entity.tree;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public class OakTree extends Tree {
    public OakTree() {
        this(0, 0);
    }

    public OakTree(int x, int y) {
        super(x, y, 128, 128);
    }

    @Override
    public Identifier id() {
        return new Identifier("oak_tree");
    }
}
