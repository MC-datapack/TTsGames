package dev.TTs.TTsGames.Games.PixelQuest.predicate;

@SuppressWarnings("all")
public interface Predicate<T> {
    boolean shouldApply(T entity);
}
