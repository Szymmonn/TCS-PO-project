package io.github.StoneDigger.model.Boards;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Interfaces.IBoard;

public class Board implements IBoard {
    private ATile[][] tiles;

    public Board(ATile[][] tiles) {
        this.tiles = tiles;
    }
    public int getWidth() { return tiles.length; }
    public int getHeight() { return tiles[0].length; }
    public ATile getTile(GridPoint2 pos) { return tiles[pos.x][pos.y]; }
    public void setTile(GridPoint2 pos, ATile tile) { tiles[pos.x][pos.y] = tile; }
    public void setTiles(ATile [][] tiles) {
        this.tiles = tiles;
    }
    public ATile[][] getTiles() {
        return tiles;
    }
}
