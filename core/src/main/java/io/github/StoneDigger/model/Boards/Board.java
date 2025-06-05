package io.github.StoneDigger.model.Boards;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;

public class Board implements IBoard {
    private ATile[][] tiles;

    public Board(ATile[][] tiles) {
        this.tiles = tiles;
    }

//    public void move(ATile tile, EDirections directions) {
//        GridPoint2 oldPosition = new GridPoint2(tile.getPosition().x,tile.getPosition().y);
//        GridPoint2 newPosition = new GridPoint2(tile.getPosition().x+directions.dx,tile.getPosition().y+directions.dy);
//
//        ATile newTile = new EmptyTile();
//        newTile.setBoard();
//        setTile(oldPosition,newTile);
//        setTile(newPosition,tile);
//    }

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
