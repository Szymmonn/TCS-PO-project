package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayDeque;
import java.util.Queue;

import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;
import io.github.StoneDigger.model.Interfaces.IDestructable;
import io.github.StoneDigger.model.Interfaces.IMovable;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class OpponentAI implements IOpponent, IMovable, ISelfUpdate {

    private boolean active = true;
    private GridPoint2 startingPosition;
    private float opponentMoveTime = 0;

    private final BoardManager boardManager;
    private final UpdateManager updateManager;
    private final PlayerManager playerManager;
    private final LevelStats levelStats;

    private GridPoint2 pos;

    public OpponentAI(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager,
                      PlayerManager playerManager, LevelStats levelStats) {
        this.pos = new GridPoint2(start);
        this.startingPosition = new GridPoint2(start);
        this.boardManager = boardManager;
        this.updateManager = updateManager;
        this.playerManager = playerManager;
        this.levelStats = levelStats;
    }

    @Override
    public GridPoint2 getPosition() {
        return pos;
    }

    @Override
    public void setStartingPosition(GridPoint2 startingPosition) {
        this.startingPosition = new GridPoint2(startingPosition);
    }

    @Override
    public void setOnStartingPosition() {
        pos = new GridPoint2(startingPosition);
    }

    @Override
    public EDirections determineMovement() {
        EDirections[] DIRS = {EDirections.RIGHT, EDirections.DOWN, EDirections.LEFT, EDirections.UP};
        boolean[][] visited = new boolean[boardManager.getHeight()][boardManager.getWidth()];

        Queue<Node> queue = new ArrayDeque<>();
        visited[pos.y][pos.x] = true;

        // Start BFS from neighbors of current position
        for (EDirections dir : DIRS) {
            int nx = pos.x + dir.getDx();
            int ny = pos.y + dir.getDy();

            if (!isInsideBoard(nx, ny)) continue;

            ATile tile = boardManager.getTile(new GridPoint2(nx, ny));
            if (!(tile instanceof EmptyTile)) continue;

            visited[ny][nx] = true;
            queue.add(new Node(new GridPoint2(nx, ny), dir));
        }

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            GridPoint2 current = node.pos;
            EDirections firstMove = node.firstMove;

            if (current.equals(playerManager.getPosition())) {
                return firstMove;
            }

            for (EDirections dir : DIRS) {
                int nx = current.x + dir.getDx();
                int ny = current.y + dir.getDy();

                if (!isInsideBoard(nx, ny) || visited[ny][nx]) continue;

                ATile tile = boardManager.getTile(new GridPoint2(nx, ny));
                if (!(tile instanceof EmptyTile)) continue;

                visited[ny][nx] = true;
                queue.add(new Node(new GridPoint2(nx, ny), firstMove));
            }
        }

        return null;
    }

    private boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < boardManager.getWidth() && y >= 0 && y < boardManager.getHeight();
    }

    private static class Node {
        GridPoint2 pos;
        EDirections firstMove;

        Node(GridPoint2 pos, EDirections firstMove) {
            this.pos = pos;
            this.firstMove = firstMove;
        }
    }

    @Override
    public void move(EDirections dir) {
        pos.add(dir.getDx(), dir.getDy());
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void destruct() {
        for (int i = pos.x - 1; i <= pos.x + 1; i++) {
            for (int j = pos.y - 1; j <= pos.y + 1; j++) {
                if (!isInsideBoard(i, j)) continue;

                GridPoint2 targetPos = new GridPoint2(i, j);
                ATile tile = boardManager.getTile(targetPos);

                if (tile instanceof IDestructable) {
                    if (tile instanceof ISelfUpdate) {
                        updateManager.removeFromUpdates((ISelfUpdate) tile);
                    }
                    tile.destroy();
                }
            }
        }
        updateManager.removeFromUpdates(this);
        active = false;
    }

    @Override
    public void update(float delta) {
        GridPoint2 playerPos = playerManager.getPosition();

        /// Killing opponent
        ATile currentTile = boardManager.getTile(pos);
        if (currentTile instanceof RockTile) {
            destruct();
        }

        /// Killing player
        if (playerPos.equals(pos)) {
            playerManager.movePlayerToStart();
            levelStats.decreaseHP();
        }


        /// Move opponent
        opponentMoveTime += delta;
        if (opponentMoveTime > 0.4f) {
            EDirections moveDirection = determineMovement();
            if (moveDirection != null) {
                move(moveDirection);
            }
            opponentMoveTime = 0;
        }
    }
}
