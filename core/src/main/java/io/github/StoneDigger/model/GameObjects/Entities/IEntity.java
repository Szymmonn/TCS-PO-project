package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

public interface IEntity {
    GridPoint2 getPosition();
    void setPosition(GridPoint2 pos);
}
