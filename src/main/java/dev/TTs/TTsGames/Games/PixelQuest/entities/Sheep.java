package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

public class Sheep extends Mob {
    public Sheep(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    public void dropItem() {
        PixelQuestGame.game.addMultipleGameObjects(PixelQuestJsonReader.readLootTable(this).dropAll(x, y));
    }

    @Override
    public Identifier id() {
        return new Identifier("sheep");
    }

    @Override
    protected int width() {
        return 57;
    }

    @Override
    protected int height() {
        return 57;
    }

}
