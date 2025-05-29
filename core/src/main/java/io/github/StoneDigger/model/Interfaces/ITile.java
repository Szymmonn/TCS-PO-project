package io.github.StoneDigger.model.Interfaces;

public interface ITile {
    boolean isWalkable();
    void walkedOnBy(IEntity entity);
}
