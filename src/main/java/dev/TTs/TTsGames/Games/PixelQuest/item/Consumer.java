package dev.TTs.TTsGames.Games.PixelQuest.item;

public interface Consumer {
    void consume(ItemStack item);
    void saturate(int i);
    void unSaturate(int i);
}
