package io.github.StoneDigger.BoardGenerators.BoardValidators;

import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.models.TileType;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleBoardValidator implements IBoardValidator {
    private static class Pair {
        public final int x;
        public final int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean isPossibleToGo(TileType tile) {
        if (tile == TileType.WALL || tile == TileType.ROCK) return false;
        return true;
    }

    private static final Pair[] directions = {
        new Pair( 1,  0),
        new Pair(-1,  0),
        new Pair( 0,  1),
        new Pair( 0, -1)
    };

    @Override
    public boolean validate(Board board) {
        int width  = board.getWidth();
        int height = board.getHeight();
        int sx = board.getStartingPositionX();
        int sy = board.getStartingPositionY();

        boolean[][] visited = new boolean[width][height];
        Queue<Pair> q = new ArrayDeque<>();

        q.add(new Pair(sx, sy));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Pair cur = q.remove();
            int x = cur.x;
            int y = cur.y;
            if (x == width - 1 && y == height - 1) {
                return true;
            }

            for (Pair d : directions) {
                int nx = x + d.x;
                int ny = y + d.y;
                if (nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
                if (visited[nx][ny]) continue;
                TileType t = board.get(nx, ny);

                if (!isPossibleToGo(t)) continue;

                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }
        return false;
    }
}
