package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface IPlayer extends IMovable, ISelfUpdate {
    void setOnStartingPosition();
    void setStartingPosition(GridPoint2 startingPosition);
    GridPoint2 getPosition();
    GridPoint2 getStartingPosition();
}
