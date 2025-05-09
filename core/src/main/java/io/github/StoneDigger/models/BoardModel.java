package io.github.StoneDigger.models;

public class BoardModel {
    TileType[][] tiles;

    public BoardModel(TileType[][] tiles) {
        this.tiles = tiles;
    }

    public void updateTile(int x, int y, TileType newType) {
        tiles[x][y] = newType;
    }

    public TileType getTile(int x, int y) {
        return tiles[x][y];
    }
}
