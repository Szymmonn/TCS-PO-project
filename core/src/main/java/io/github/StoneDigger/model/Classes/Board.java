package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.ITile;
import io.github.StoneDigger.model1.models.TileType;

public class Board {
    private final ITile[][] tiles;
    private final GridPoint2 startingPosition;
    private final GridPoint2 boardSize;

    public Board(ITile[][] tiles, GridPoint2 startingPosition) {
        this.tiles = tiles;
        this.startingPosition = startingPosition;
        boardSize = new GridPoint2(tiles.length,tiles[0].length);
    }

    public void updateTile(int x, int y, ITile newType) {
        tiles[x][y] = newType;
    }

    public ITile getTile(int x, int y) {
        return tiles[x][y];
    }

    public GridPoint2 getStartingX() { return startingPosition; }
    public GridPoint2 getBoardSize() { return boardSize; }
}
