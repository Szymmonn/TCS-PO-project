package io.github.StoneDigger.model.Classes.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model.Classes.Tiles.BorderTile;
import io.github.StoneDigger.model.Classes.Tiles.DiamondTile;
import io.github.StoneDigger.model.Classes.Tiles.DirtTile;
import io.github.StoneDigger.model.Classes.Tiles.EndTile;
import io.github.StoneDigger.model.Classes.Tiles.RockTile;
import io.github.StoneDigger.model.Classes.Tiles.StartTile;
import io.github.StoneDigger.model.Interfaces.IBoardGenerator;
import io.github.StoneDigger.model.Interfaces.ITile;
import io.github.StoneDigger.model.Interfaces.IEntity;

import java.util.Objects;
import java.util.Random;

public class RandomBoardGenerator implements IBoardGenerator {
    private final double wallDensity;
    private final double rockDensity;
    private final double diamondDensity;
    private final Random random;

    public RandomBoardGenerator(double wallDensity, double rockDensity, double diamondDensity, Random random) {
        validateDensities(wallDensity, rockDensity, diamondDensity);
        this.wallDensity = wallDensity;
        this.rockDensity = rockDensity;
        this.diamondDensity = diamondDensity;
        this.random = Objects.requireNonNull(random);
    }

    public RandomBoardGenerator(double wallDensity, double rockDensity, double diamondDensity) {
        this(wallDensity, rockDensity, diamondDensity, new Random());
    }

    @Override
    public Board generate(GridPoint2 boardSize, GridPoint2 startingPosition) {
        validateGenerateFunctionArguments(startingPosition, boardSize);
        int width = boardSize.x;
        int height = boardSize.y;

        ITile[][] tiles = new ITile[width][height];
        IEntity[][] entities = new IEntity[width][height];

        fillWithDirt(tiles);
        placeBorders(tiles);
        scatterObstacles(tiles);

        // Start and Exit
        tiles[startingPosition.x][startingPosition.y] = new StartTile();
        tiles[width - 2][height - 2] = new EndTile();

        // Create board instance
        return new Board(tiles, entities, startingPosition);
    }

    private void fillWithDirt(ITile[][] tiles) {
        int w = tiles.length;
        int h = tiles[0].length;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                tiles[x][y] = new DirtTile();
            }
        }
    }

    private void placeBorders(ITile[][] tiles) {
        int w = tiles.length;
        int h = tiles[0].length;
        // Top and bottom
        for (int x = 0; x < w; x++) {
            tiles[x][0] = new BorderTile();
            tiles[x][h - 1] = new BorderTile();
        }
        // Left and right
        for (int y = 0; y < h; y++) {
            tiles[0][y] = new BorderTile();
            tiles[w - 1][y] = new BorderTile();
        }
    }

    private void scatterObstacles(ITile[][] tiles) {
        int w = tiles.length;
        int h = tiles[0].length;
        for (int x = 1; x < w - 1; x++) {
            for (int y = 1; y < h - 1; y++) {
                double r = random.nextDouble();
                if (r < wallDensity) {
                    tiles[x][y] = new BorderTile();
                } else if (r < wallDensity + rockDensity) {
                    tiles[x][y] = new RockTile();
                } else if (r < wallDensity + rockDensity + diamondDensity) {
                    tiles[x][y] = new DiamondTile();
                }
                // else remains DirtTile
            }
        }
    }

    private void validateDensities(double wall, double rock, double diamond) {
        if (wall < 0 || rock < 0 || diamond < 0)
            throw new IllegalArgumentException("Densities must be non-negative");
        double sum = wall + rock + diamond;
        if (sum > 1.0)
            throw new IllegalArgumentException(String.format(
                "Sum of densities must be <= 1.0, but was %.2f", sum));
    }

    private void validateGenerateFunctionArguments(GridPoint2 start, GridPoint2 size) {
        int sx = start.x;
        int sy = start.y;
        int w = size.x;
        int h = size.y;
        if (sx <= 0 || sy <= 0) {
            throw new IllegalArgumentException("startingPositions must be positive");
        }
        if (w < 4 || h < 4) {
            throw new IllegalArgumentException("Board dimensions must be at least 4x4");
        }
        if (sx >= w - 1) {
            throw new IllegalArgumentException(
                "startingPositionX must be between 0 and " + (w - 1));
        }
        if (sy >= h - 1) {
            throw new IllegalArgumentException(
                "startingPositionY must be between 0 and " + (h - 1));
        }
    }
}
