package io.github.StoneDigger.BoardGenerators;

import java.util.Objects;
import java.util.Random;

public class RandomBoardGenerator implements IBoardGenerator {
    private final double wallDensity;
    private final double rockDensity;
    private final double diamondDensity;
    private final Random random;

    private void validateDensities(double wall, double rock, double diamond) {
        if (wall < 0 || rock < 0 || diamond < 0)
            throw new IllegalArgumentException("Densities must be non-negative");
        double sum = wall + rock + diamond;
        if (sum > 1.0)
            throw new IllegalArgumentException(String.format("Sum of densities must be <= 1.0, but was %.2f", sum));

    }

    private void validateGenerateFunctionArguments(int startingPositionX, int startingPositionY, int height, int width) {
        if(startingPositionX >= width - 1)
            throw new IllegalArgumentException("startingPositionX must be between 0 and " + (width - 1));
        if(startingPositionY >= height - 1)
            throw new IllegalArgumentException("startingPositionY must be between 0 and " + (height - 1));
        if(width < 4) throw new IllegalArgumentException("width must be at least 4");
        if(height < 4) throw new IllegalArgumentException("height must be at least 4");
        if(startingPositionX <= 0 || startingPositionY <= 0)
            throw new IllegalArgumentException("startingPositions must be non-negative");
    }

    private void fillWithDirt(TileType[][] board) {
        int width = board[0].length;
        int height = board.length;

        for (int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                board[y][x] = TileType.DIRT;
            }
        }
    }

    private void placeBorders(TileType[][] board) {
        int width = board[0].length;
        int height = board.length;

        for (int x = 0; x < width; x++) {
            board[0][x] = TileType.WALL;
            board[height-1][x] = TileType.WALL;
        }
        for (int y = 0; y < height; y++) {
            board[y][0] = TileType.WALL;
            board[y][width-1] = TileType.WALL;
        }
    }

    private void scatterObstacles(TileType[][] board) {
        int width = board[0].length;
        int height = board.length;

        for (int y = 1; y < height-1; y++) {
            for (int x = 1; x < width-1; x++) {
                double r = random.nextDouble();
                if (r < wallDensity) {
                    board[y][x] = TileType.WALL;
                } else if (r < wallDensity + rockDensity) {
                    board[y][x] = TileType.ROCK;
                } else if (r < wallDensity + rockDensity + diamondDensity) {
                    board[y][x] = TileType.DIAMOND;
                }
                // else pozostaje dirtem
            }
        }
    }

    private void placeStartAndExit(TileType[][] board, int sx, int sy) {
        int width = board[0].length;
        int height = board.length;

        board[sy][sx] = TileType.START;
        board[height-2][width-2] = TileType.EXIT;
    }

    // dziala to tak, ze kazda ze zmienncyh oznacza szanse na wystapienie danego obstacle i
    // musi byc wallDensity (wD) + rockDensity (rD) + diamondDensity (dD) < 1, bo
    // jak np wD = 0.1, rD = 0.2, dD = 0.3, to losuje liczbe r z przedzialu [0, 1] i jesli
    // r nalezy do [0, 0.1], to jest wall, jesli do [0.1, 0.1 + 0.2], to jest rock,
    // jesli do [0.1 + 0.2, 0.1 + 0.2 + 0.3], to jest diamond, a inaczej pozostaje dirt
    public RandomBoardGenerator(double wallDensity, double rockDensity, double diamondDensity, Random random) {
        validateDensities(wallDensity, rockDensity, diamondDensity);
        this.wallDensity = wallDensity;
        this.rockDensity = rockDensity;
        this.diamondDensity = diamondDensity;
        this.random = Objects.requireNonNull(random);
    }

    @Override
    public TileType[][] generate(int width, int height, int startingPositionX, int startingPositionY) {
        validateGenerateFunctionArguments(startingPositionX, startingPositionY, height, width);
        TileType[][] board = new TileType[height][width];

        // wypelnia pustymi polami
        fillWithDirt(board);

        // ustawia pole start i end
        placeStartAndExit(board, startingPositionX, startingPositionY);

        // daje sciany na obwodzie
        placeBorders(board);

        // losowo wrzuca skaly i diamwnty
        scatterObstacles(board);

        return board;
    }
}
