package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;

import java.util.Random;

public class RandomBoardGenerator implements IBoardGenerator {
    private final double wallDensity, rockDensity, diamondDensity;
    private final Random random;
    LevelManager levelManager;

    public RandomBoardGenerator(double w, double r, double d, LevelManager levelManager) {
        this.wallDensity = w;
        this.rockDensity = r;
        this.diamondDensity = d;
        this.random = new Random();
        this.levelManager = levelManager;
    }

    @Override
    public Board generate(GridPoint2 size, GridPoint2 start) {
        int w = size.x, h = size.y;
        ATile[][] tiles = new ATile[w][h];
        for(int x = 0; x < w; x++) for(int y = 0; y < h; y++) tiles[x][y] = new DirtTile(levelManager);
        for(int x = 0; x < w; x++){ tiles[x][0] = new BorderTile(levelManager); tiles[x][h-1] = new BorderTile(levelManager); }
        for(int y = 0; y < h; y++){ tiles[0][y] = new BorderTile(levelManager); tiles[w-1][y] = new BorderTile(levelManager); }
        for(int x = 1; x < w - 1; x++) for(int y = 1; y < h - 1; y++){
            double rv = random.nextDouble();
            if(rv < wallDensity) tiles[x][y] = new BorderTile(levelManager);
            else if(rv < wallDensity + rockDensity) tiles[x][y] = new RockTile(levelManager);
            else if(rv < wallDensity + rockDensity + diamondDensity) tiles[x][y] = new DiamondTile(levelManager);
        }
        tiles[start.x][start.y] = new StartTile(levelManager);
        tiles[w-2][h-2] = new EndTile(levelManager);
        return new Board(tiles, start);
    }
}
