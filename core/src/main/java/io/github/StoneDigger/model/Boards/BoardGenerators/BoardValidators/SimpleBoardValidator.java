package io.github.StoneDigger.model.Boards.BoardGenerators.BoardValidators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleBoardValidator implements IBoardValidator {

    @Override
    public boolean validate(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            throw new IllegalArgumentException("Board nie może być pusty ani null.");
        }

        int h = board.length;       // liczba wierszy
        int w = board[0].length;    // liczba kolumn

        // Znajdź pozycję startu 's'
        GridPoint2 start = null;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (board[y][x] == 's') {
                    start = new GridPoint2(x, y);
                    break;
                }
            }
            if (start != null) break;
        }
        if (start == null) {
            throw new IllegalArgumentException("Brak pola startu 's' na planszy.");
        }

        // Koordynaty pola wyjścia: (w-2, h-2)
        int endX = w - 2;
        int endY = h - 2;
        if (endX < 0 || endY < 0) {
            return false;
        }

        boolean[][] visited = new boolean[h][w];
        Queue<GridPoint2> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            GridPoint2 p = queue.remove();
            if (p.x == endX && p.y == endY) {
                return true;
            }
            for (EDirections dir : EDirections.values()) {
                int nx = p.x + dir.getDx();
                int ny = p.y + dir.getDy();
                if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
                if (visited[ny][nx]) continue;
                char ch = board[ny][nx];
                if (!isWalkableChar(ch)) continue;
                visited[ny][nx] = true;
                queue.add(new GridPoint2(nx, ny));
            }
        }
        return false;
    }

    private boolean isWalkableChar(char ch) {
        return !(ch == 'b' || ch == 'c' || ch == 'r');
    }
}
