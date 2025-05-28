package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface IBoard {
    GridPoint2 getStartingPoint();
    GridPoint2 getSize();
    ITile getTile(GridPoint2 point2);
}
