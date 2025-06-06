package io.github.StoneDigger.model.Interfaces;

import io.github.StoneDigger.model.Directions.EDirections;

public interface IMovable extends IEntity {
    //boolean canMove(EDirections dir);
    void move(EDirections dir);
}
