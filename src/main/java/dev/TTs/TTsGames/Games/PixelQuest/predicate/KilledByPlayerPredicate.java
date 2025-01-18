package dev.TTs.TTsGames.Games.PixelQuest.predicate;

import dev.TTs.TTsGames.Games.PixelQuest.entity.DamageReason;
import dev.TTs.TTsGames.Games.PixelQuest.entity.Damageable;

public class KilledByPlayerPredicate implements Predicate<Damageable> {
    @Override
    public boolean shouldApply(Damageable entity) {
        return entity.deathReason() == DamageReason.PLAYER;
    }
}
