package dev.TTs.TTsGames.Games.PixelQuest.entity.tree;

import dev.TTs.TTsGames.Games.PixelQuest.entity.GameObject;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Player;
import dev.TTs.TTsGames.Games.PixelQuest.item.Droppable;
import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;

import java.awt.*;
import java.util.List;

public abstract class Tree extends GameObject implements Droppable {
    public Tree(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(id().getEntityTexture().toImage(), x, y, width, height,null);
    }

    @Override
    public void onCollision(GameObject with) {
        if (with instanceof Player) {
            dropItem();
            PixelQuestGame.game.removeGameObject(this);
        }
    }

    @Override
    public void dropItem() {
        try {
            PixelQuestGame.game.addMultipleGameObjects(List.of(PixelQuestJsonReader.readLootTable(this).dropAll(x, y)));
        } catch (NullPointerException ignored) {}
    }
}
