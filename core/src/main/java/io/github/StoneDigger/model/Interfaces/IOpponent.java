package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface IOpponent extends IMovable, ISelfUpdate {
    void setStartingPosition(GridPoint2 d);
    void setOnStartingPosition();
    boolean isActive();
}
