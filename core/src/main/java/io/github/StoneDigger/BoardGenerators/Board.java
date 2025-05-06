package io.github.StoneDigger.BoardGenerators;

public class Board {
    private final TileType[][] tiles;
    private final int startingPositionX;
    private final int startingPositionY;

    public int getWidth() {return tiles[0].length;}
    public int getHeight() {return tiles.length;}
    public int getStartingPositionX() {return startingPositionX;}
    public int getStartingPositionY() {return startingPositionY;}

    public TileType get(int x, int y) {
        return tiles[x][y];
    }

    public boolean set(int x, int y, TileType t) {
        if(x == 0 || y == 0 || x == getWidth() - 1 || y == getWidth() - 1 ||
                (x == getWidth() - 2 && y == getWidth() - 2)) return false;
        tiles[x][y] = t;
        return true;
    }

    public Board(TileType[][] tiles, int startingPositionX, int startingPositionY) {
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
        this.tiles = tiles;
    }
}
