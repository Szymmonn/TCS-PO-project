package io.github.StoneDigger.BoardGenerators;

import io.github.StoneDigger.Actors.BoardObserver;
import io.github.StoneDigger.Obstacles.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<BoardObserver> observers = new ArrayList<>(); // obserwatorzy boards, na razie do obserwator kamieni
    private final TileType[][] tiles;
    private final Obstacle[][] obstacles;
    private final int startingPositionX;
    private final int startingPositionY;

    public Board(TileType[][] tiles, int startingPositionX, int startingPositionY) {
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
        this.tiles = tiles;

        int width = getWidth();
        int height = getHeight();
        this.obstacles = new Obstacle[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                obstacles[x][y] = createObstacleForTile(tiles[x][y]);
            }
        }
    }

    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    public int getWidth()  { return tiles.length; }
    public int getHeight() { return tiles[0].length; }

    public int getStartingPositionX() { return startingPositionX; }
    public int getStartingPositionY() { return startingPositionY; }

    public TileType get(int x, int y) {
        return tiles[x][y];
    }

    public TileType[][] getTiles() {
        int width = getWidth();
        int height = getHeight();
        TileType[][] copy = new TileType[width][height];
        for (int x = 0; x < width; x++) {
            System.arraycopy(tiles[x], 0, copy[x], 0, height);
        }
        return copy;
    }

    public Obstacle[][] getObstacleArray() {
        int width = getWidth();
        int height = getHeight();
        Obstacle[][] copy = new Obstacle[width][height];
        for (int x = 0; x < width; x++) {
            System.arraycopy(obstacles[x], 0, copy[x], 0, height);
        }
        return copy;
    }

    public TileType[][] getOriginalTilesArray() {
        return tiles;
    }

    public boolean set(int x, int y, TileType t) {
        if (x == 0 || y == 0
                || x == getWidth() - 1
                || y == getHeight() - 1
                || (x == getWidth() - 2 && y == getHeight() - 2)) {
            return false;
        }
        TileType previousTile = get(x,y);

        tiles[x][y] = t;
        obstacles[x][y] = createObstacleForTile(t);

        for (BoardObserver observer : observers) {
            observer.onTileChanged(x, y, previousTile, this);
        }
        return true;
    }

    private Obstacle createObstacleForTile(TileType type) {
        switch (type) {
            case DIRT: return new DirtObstacle();
            case WALL: return new WallObstacle();
            case ROCK: return new RockObstacle();
            case DIAMOND: return new DiamondObstacle();
            case START: return new StartObstacle();
            case EXIT: return new ExitObstacle();
            default: return null;
        }
    }
}
