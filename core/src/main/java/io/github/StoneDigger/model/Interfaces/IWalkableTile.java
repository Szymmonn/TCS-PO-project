package io.github.StoneDigger.model.Interfaces;

import io.github.StoneDigger.model.Directions.EDirections;

public interface IWalkableTile {
    void onWalkBy(IEntity entity, EDirections dir);
}
