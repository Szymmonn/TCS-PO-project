package io.github.StoneDigger.models;

public class BoardModel {
    private final TileType[][] tiles;
    private final int startingX;
    private final int startingY;

    public BoardModel(TileType[][] tiles, int startingX, int startingY) {
        this.tiles = tiles;
        this.startingX = startingX;
        this.startingY = startingY;
    }

    public void updateTile(int x, int y, TileType newType) {
        tiles[x][y] = newType;
    }

    public TileType getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getStartingX() { return startingX; }
    public int getStartingY() { return startingY; }
}
