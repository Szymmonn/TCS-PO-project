package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;

public interface IOpponent extends IMovable, ISelfUpdate {
    void setStartingPosition(GridPoint2 d);
    void setOnStartingPosition();
    boolean isActive();
    void destruct();
    EDirections determineMovement();
}
