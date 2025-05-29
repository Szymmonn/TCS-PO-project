package io.github.StoneDigger.model.Interfaces;

public interface ITile {
    boolean isWalkable();
    void onWalkBy(IEntity entity);
}
