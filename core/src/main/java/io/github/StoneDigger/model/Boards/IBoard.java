package io.github.StoneDigger.model.Boards;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;

public interface IBoard {
    int getWidth();
    int getHeight();
    ATile getTile(GridPoint2 pos);
    void setTile(GridPoint2 pos, ATile tile);
    public void setTiles(ATile [][] tiles);
    public ATile[][] getTiles();
}
