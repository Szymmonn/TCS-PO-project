package io.github.StoneDigger.model.GameObjects.Entities.Hunting;

import io.github.StoneDigger.model.GameObjects.Entities.IEntity;

public interface IHunting {
    void onKilled(IEntity killer);
}
