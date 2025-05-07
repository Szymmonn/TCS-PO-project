package io.github.StoneDigger.BoardGenerators;

import io.github.StoneDigger.Obstacles.*;

public class Board {
    private final TileType[][] tiles;
    private final int startingPositionX;
    private final int startingPositionY;

    public Board(TileType[][] tiles, int startingPositionX, int startingPositionY) {
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
        this.tiles = tiles;
    }

    public int getWidth()  { return tiles.length; }
    public int getHeight() { return tiles[0].length; }

    public int getStartingPositionX() {return startingPositionX;}
    public int getStartingPositionY() {return startingPositionY;}

    public TileType get(int x, int y) {
        return tiles[x][y];
    }

    public TileType[][] getTiles() {
        int width = getWidth();
        int height = getHeight();
        TileType[][] newArr = new TileType[width][height];
        for(int i=0;i<width;i++) System.arraycopy(tiles[i], 0, newArr[i], 0, height);
        return newArr;
    }

    public Obstacle[][] getObstacleArray() {
        int width = getWidth();
        int height = getHeight();
        Obstacle[][] newArr = new Obstacle[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                    switch (tiles[i][j]) {
                        case DIRT:
                            newArr[i][j] = new DirtObstacle();
                            break;

                        case WALL:
                            newArr[i][j] = new WallObstacle();
                            break;

                        case ROCK:
                            newArr[i][j] = new RockObstacle();
                            break;

                        case DIAMOND:
                            newArr[i][j] = new DiamondObstacle();
                            break;

                        case START:
                            newArr[i][j] = new StartObstacle();
                            break;

                        case EXIT:
                            newArr[i][j] = new ExitObstacle();
                            break;

                        default:
                            break;
                    }
                }
            }

        return newArr;
    }

    public boolean set(int x, int y, TileType t) {
        if (x == 0 || y == 0
                || x == getWidth() - 1
                || y == getHeight() - 1
                || (x == getWidth() - 2 && y == getHeight() - 2)
        ) return false;

        tiles[x][y] = t;
        return true;
    }


}
