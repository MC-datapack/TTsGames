package dev.TTs.TTsGames.Games.PixelQuest.predicate;

import dev.TTs.TTsGames.Games.PixelQuest.entity.Entity;

public record InRangePredicate(Integer minX, Integer maxX, Integer minY,
                               Integer maxY) implements Predicate<Entity> {

    @Override
    public boolean shouldApply(Entity entity) {
        return (minX == null || entity.x >= minX) &&
                (maxX == null || entity.x <= maxX) &&
                (minY == null || entity.y >= minY) &&
                (maxY == null || entity.y <= maxY);
    }
}
