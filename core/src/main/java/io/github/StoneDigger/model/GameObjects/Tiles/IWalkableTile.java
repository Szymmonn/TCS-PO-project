package io.github.StoneDigger.model.GameObjects.Tiles;

import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.IEntity;

public interface IWalkableTile {
    void onWalkBy(IEntity entity, EDirections dir);
}
