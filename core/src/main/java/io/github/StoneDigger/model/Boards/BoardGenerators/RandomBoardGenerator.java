package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Level.Managers.LevelManager;

import java.util.Random;

public class RandomBoardGenerator implements IBoardGenerator{
    private static double wallDensity;
    private static double rockDensity;
    private static double diamondDensity;
    private static Random random;

    public RandomBoardGenerator(double w, double r, double d) {
        wallDensity = w;
        rockDensity = r;
        diamondDensity = d;
        random = new Random();
    }

    public static Board generate(GridPoint2 size, GridPoint2 start) {
        int w = size.x, h = size.y;
        ATile[][] tiles = new ATile[w][h];
        for(int x = 0; x < w; x++)
            for(int y = 0; y < h; y++)
                tiles[x][y] = new DirtTile(new GridPoint2(x,y));

        for(int x = 0; x < w; x++){
            tiles[x][0] = new BorderTile(new GridPoint2(x, 0));
            tiles[x][h-1] = new BorderTile(new GridPoint2(x, h-1));
        }

        for(int y = 0; y < h; y++){
            tiles[0][y] = new BorderTile(new GridPoint2(0, y));
            tiles[w-1][y] = new BorderTile(new GridPoint2(w-1, y));
        }

        for(int x = 1; x < w - 1; x++) {
            for (int y = 1; y < h - 1; y++) {
                double rv = random.nextDouble();
                if (rv < wallDensity) tiles[x][y] = new BrickTile(new GridPoint2(x, y));
                else if (rv < wallDensity + rockDensity) tiles[x][y] = new RockTile(new GridPoint2(x, y));
                else if (rv < wallDensity + rockDensity + diamondDensity) tiles[x][y] = new DiamondTile(new GridPoint2(x,y));
            }
        }
        tiles[start.x][start.y] = new StartTile(new GridPoint2(start.x, start.y));
        tiles[w-2][h-2] = new DeactivatedEndTile(new GridPoint2(w-2, h-2));
        return new Board(tiles, start);
    }
}
