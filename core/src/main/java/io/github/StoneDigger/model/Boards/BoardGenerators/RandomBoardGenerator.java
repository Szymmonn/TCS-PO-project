package io.github.StoneDigger.model.Boards.BoardGenerators;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Random;

public class RandomBoardGenerator implements IBoardGenerator {
    private static double wallDensity = 0.1;
    private static double rockDensity = 0.1;
    private static double diamondDensity = 0.1;
    private static Random random;

    public RandomBoardGenerator(double w, double r, double d) {
        wallDensity = w;
        rockDensity = r;
        diamondDensity = d;
        random = new Random();
    }

    public static char[][] generate(GridPoint2 size, GridPoint2 start) {
        int w = size.x;
        int h = size.y;
        char[][] board = new char[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                board[y][x] = 'd';
            }
        }

        for (int x = 0; x < w; x++) {
            board[0][x] = 'b';
            board[h - 1][x] = 'b';
        }
        for (int y = 0; y < h; y++) {
            board[y][0] = 'b';
            board[y][w - 1] = 'b';
        }

        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                double rv = random.nextDouble();
                if (rv < wallDensity) {
                    board[y][x] = 'c';
                } else if (rv < wallDensity + rockDensity) {
                    board[y][x] = 'r';
                } else if (rv < wallDensity + rockDensity + diamondDensity) {
                    board[y][x] = 'a';
                }
            }
        }

        board[start.y][start.x] = 's';
        board[h - 2][w - 2] = 'x';

        return board;
    }
}
