package io.github.StoneDigger.model.Classes.BoardGenerators.BoardValidators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model.Classes.Tiles.BorderTile;
import io.github.StoneDigger.model.Classes.Tiles.RockTile;
import io.github.StoneDigger.model.Interfaces.IBoardValidator;
import io.github.StoneDigger.model.Interfaces.ITile;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleBoardValidator implements IBoardValidator {

    private boolean isPossibleToGo(ITile tile) {
        return !(tile instanceof BorderTile) && !(tile instanceof RockTile);
    }

    private static final GridPoint2[] DIRECTIONS = {
        new GridPoint2( 1,  0),
        new GridPoint2(-1,  0),
        new GridPoint2( 0,  1),
        new GridPoint2( 0, -1)
    };

    @Override
    public boolean validate(Board board) {
        GridPoint2 size = board.getBoardSize();
        int width  = size.x;
        int height = size.y;

        GridPoint2 start = board.getStartingPosition();
        int sx = start.x;
        int sy = start.y;

        boolean[][] visited = new boolean[width][height];
        Queue<GridPoint2> queue = new ArrayDeque<>();

        visited[sx][sy] = true;
        queue.add(new GridPoint2(sx, sy));

        while (!queue.isEmpty()) {
            GridPoint2 current = queue.remove();
            int x = current.x;
            int y = current.y;
            // reached exit corner
            if (x == width - 2 && y == height - 2) {
                return true;
            }
            for (GridPoint2 dir : DIRECTIONS) {
                int nx = x + dir.x;
                int ny = y + dir.y;
                if (nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
                if (visited[nx][ny]) continue;
                ITile tile = board.getTile(nx, ny);
                if (!isPossibleToGo(tile)) continue;
                visited[nx][ny] = true;
                queue.add(new GridPoint2(nx, ny));
            }
        }
        return false;
    }
}
