package io.github.StoneDigger.model.Interfaces;

public interface IMovable extends IEntity {
    boolean canMove(EDirections dir);
    void move(EDirections dir);
}
