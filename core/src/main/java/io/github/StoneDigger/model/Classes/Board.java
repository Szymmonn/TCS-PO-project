package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ITile;
import io.github.StoneDigger.model1.models.TileType;

public class Board {
    private static ITile[][] tiles;
    private static IEntity[][] entities;

    private static GridPoint2 startingPosition;
    private static GridPoint2 boardSize;

    public Board(ITile[][] tiles, IEntity[][] entites, GridPoint2 startingPosition) {
        Board.tiles = tiles;
        Board.entities = entites;
        Board.startingPosition = startingPosition;
        boardSize = new GridPoint2(tiles.length,tiles[0].length);
    }

    public static void setTile(GridPoint2 pos, ITile newTile) {
        tiles[pos.x][pos.y] = newTile;
    }

    public static ITile getTile(GridPoint2 position) {
        return tiles[position.x][position.y];
    }

    public static GridPoint2 getStartingPosition() { return startingPosition; }
    public static GridPoint2 getBoardSize() { return boardSize; }
}
