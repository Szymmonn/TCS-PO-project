package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ITile;
import io.github.StoneDigger.model1.models.TileType;

public class Board implements IBoard {
    private final ITile[][] tiles;
    private final GridPoint2 start;

    public Board(ITile[][] tiles, GridPoint2 start) {
        this.tiles = tiles;
        this.start = start;
    }

    @Override public int getWidth() { return tiles.length; }
    @Override public int getHeight() { return tiles[0].length; }
    @Override public ITile getTile(GridPoint2 pos) { return tiles[pos.x][pos.y]; }
    @Override public void setTile(GridPoint2 pos, ITile tile) { tiles[pos.x][pos.y] = tile; }
    @Override public GridPoint2 getStartingPosition() { return start; }
}
