package dev.TTs.TTsGames.Games.PixelQuest.entity.tree;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public class SpruceTree extends Tree {
    public SpruceTree() {
        this(0, 0);
    }

    public SpruceTree(int x, int y) {
        super(x, y, 118, 191);
    }

    @Override
    public Identifier id() {
        return new Identifier("spruce_tree");
    }
}
