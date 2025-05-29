package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface IBoard {
    int getWidth();
    int getHeight();
    ITile getTile(GridPoint2 pos);
    void setTile(GridPoint2 pos, ITile tile);
    GridPoint2 getStartingPosition();
}
