package io.github.StoneDigger.model.Classes.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.IBoardGenerator;
import io.github.StoneDigger.model.Classes.Tiles.*;

import java.util.Random;

public class RandomBoardGenerator implements IBoardGenerator {
    private final double wallDensity, rockDensity, diamondDensity;
    private final Random random;

    public RandomBoardGenerator(double w, double r, double d) {
        this.wallDensity = w;
        this.rockDensity = r;
        this.diamondDensity = d;
        this.random = new Random();
    }

    @Override
    public IBoard generate(GridPoint2 size, GridPoint2 start) {
        int w = size.x, h = size.y;
        ATile[][] tiles = new ATile[w][h];
        for(int x = 0; x < w; x++) for(int y = 0; y < h; y++) tiles[x][y] = new DirtTile();
        for(int x = 0; x < w; x++){ tiles[x][0] = new BorderTile(); tiles[x][h-1] = new BorderTile(); }
        for(int y = 0; y < h; y++){ tiles[0][y] = new BorderTile(); tiles[w-1][y] = new BorderTile(); }
        for(int x = 1; x < w - 1; x++) for(int y = 1; y < h - 1; y++){
            double rv = random.nextDouble();
            if(rv < wallDensity) tiles[x][y] = new BorderTile();
            else if(rv < wallDensity + rockDensity) tiles[x][y] = new RockTile();
            else if(rv < wallDensity + rockDensity + diamondDensity) tiles[x][y] = new DiamondTile();
        }
        tiles[start.x][start.y] = new StartTile();
        tiles[w-2][h-2] = new EndTile();
        return new Board(tiles, start);
    }
}
