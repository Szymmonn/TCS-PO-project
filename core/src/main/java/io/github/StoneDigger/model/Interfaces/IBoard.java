package io.github.StoneDigger.model.Interfaces;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Tiles.ATile;

public interface IBoard {
    int getWidth();
    int getHeight();
    ATile getTile(GridPoint2 pos);
    void setTile(GridPoint2 pos, ATile tile);
    GridPoint2 getStartingPosition();
}
