package io.github.StoneDigger.models;

public class BoardModel {
    TileType[][] tiles;

    BoardModel(TileType[][] tiles) {
        this.tiles = tiles;
    }

    public void updateTile(int x, int y, TileType newType) {
        tiles[x][y] = newType;
    }
}
