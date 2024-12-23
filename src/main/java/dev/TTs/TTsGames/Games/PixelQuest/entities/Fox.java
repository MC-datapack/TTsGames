package dev.TTs.TTsGames.Games.PixelQuest.entities;

import dev.TTs.TTsGames.Games.PixelQuest.json.PixelQuestJsonReader;
import dev.TTs.TTsGames.Games.PixelQuest.main.PixelQuestGame;
import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.util.List;

public class Fox extends Mob {
    public Fox(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    public void dropItem() {
        PixelQuestGame.game.addMultipleGameObjects(List.of(PixelQuestJsonReader.readLootTable(this).dropAll(x, y)));
    }

    @Override
    public Identifier id() {
        return new Identifier("fox");
    }
}
