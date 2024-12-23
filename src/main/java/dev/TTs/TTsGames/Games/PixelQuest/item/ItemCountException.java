package dev.TTs.TTsGames.Games.PixelQuest.item;

public class ItemCountException extends RuntimeException {
    public ItemCountException(String message, int count) {
      super(message + " " + count);
    }

    public ItemCountException(String message, int maxCount, int count) {
      super(message + " max count: " + maxCount + ", count: " + count);
    }
}
