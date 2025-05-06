package io.github.StoneDigger.BoardGenerators;

public class Board {
    private final TileType[][] tiles;

    public int getWidth() {return tiles[0].length;}
    public int getHeight() {return tiles.length;}

    public TileType get(int x, int y) {
        return tiles[y][x];
    }

    public Board(TileType[][] tiles) {
        this.tiles = tiles;
    }
}
