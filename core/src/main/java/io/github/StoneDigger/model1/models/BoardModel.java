package io.github.StoneDigger.model1.models;

public class BoardModel {
    private final TileType[][] tiles;
    private final int startingX;
    private final int startingY;
    private final int width;
    private final int height;

    public BoardModel(TileType[][] tiles, int startingX, int startingY) {
        this.tiles = tiles;
        this.startingX = startingX;
        this.startingY = startingY;
        width = tiles.length ;
        height = tiles[0].length;
    }

    public void updateTile(int x, int y, TileType newType) {
        tiles[x][y] = newType;
    }

    public TileType getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getStartingX() { return startingX; }
    public int getStartingY() { return startingY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
