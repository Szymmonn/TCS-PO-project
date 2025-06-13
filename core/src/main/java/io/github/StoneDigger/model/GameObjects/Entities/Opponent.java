package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;
import io.github.StoneDigger.model.Interfaces.IDestructable;
import io.github.StoneDigger.model.Interfaces.IMovable;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

public class Opponent implements IOpponent, IMovable, ISelfUpdate, IDestructable {
    private boolean active = true;
    private GridPoint2 startingPosition;
    private float opponentMoveTime = 0;
    private EDirections moveDirection = EDirections.RIGHT;

    private final BoardManager boardManager;
    private final UpdateManager updateManager;
    private final PlayerManager playerManager;
    private final LevelStats levelStats;

    private GridPoint2 pos;

    public Opponent(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, PlayerManager playerManager, LevelStats levelStats) {
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


    private ATile safeGetTile(GridPoint2 p) {
        if (p == null) return null;
        if (p.x < 0 || p.x >= boardManager.getWidth() || p.y < 0 || p.y >= boardManager.getHeight())
            return null;
        return boardManager.getTile(p);
    }


    @Override
    public EDirections determineMovement() {
        GridPoint2 left, next, right;

        switch (moveDirection) {
            case UP:
                right = new GridPoint2(pos.x + 1, pos.y);
                left = new GridPoint2(pos.x - 1, pos.y);
                next = new GridPoint2(pos.x, pos.y + 1);
                break;
            case DOWN:
                right = new GridPoint2(pos.x - 1, pos.y);
                left = new GridPoint2(pos.x + 1, pos.y);
                next = new GridPoint2(pos.x, pos.y - 1);
                break;
            case RIGHT:
                right = new GridPoint2(pos.x, pos.y - 1);
                left = new GridPoint2(pos.x, pos.y + 1);
                next = new GridPoint2(pos.x + 1, pos.y);
                break;
            case LEFT:
                right = new GridPoint2(pos.x, pos.y + 1);
                left = new GridPoint2(pos.x, pos.y - 1);
                next = new GridPoint2(pos.x - 1, pos.y);
                break;
            default:
                // Domyślnie idź w prawo
                right = new GridPoint2(pos.x, pos.y - 1);
                left = new GridPoint2(pos.x, pos.y + 1);
                next = new GridPoint2(pos.x + 1, pos.y);
        }

        ATile leftTile = safeGetTile(left);
        ATile nextTile = safeGetTile(next);
        ATile rightTile = safeGetTile(right);

        EDirections[] directionsOrder = { EDirections.UP, EDirections.LEFT, EDirections.DOWN, EDirections.RIGHT };
        int currentDirIndex = 0;
        for (int i = 0; i < directionsOrder.length; i++) {
            if (moveDirection == directionsOrder[i]) {
                currentDirIndex = i;
                break;
            }
        }

        if (!(leftTile instanceof EmptyTile)) {
            if (nextTile instanceof EmptyTile) {
                return moveDirection;
            } else if (rightTile instanceof EmptyTile) {
                return directionsOrder[(currentDirIndex + 3) % 4]; // skręt w prawo
            } else {
                return directionsOrder[(currentDirIndex + 2) % 4]; // zawróć
            }
        } else {
            return directionsOrder[(currentDirIndex + 1) % 4]; // skręt w lewo
        }
    }

    @Override
    public void move(EDirections dir) {
        pos.add(dir.getDx(), dir.getDy());
    }

    @Override
    public void destruct() {
        for (int i = pos.x - 1; i <= pos.x + 1; i++) {
            for (int j = pos.y - 1; j <= pos.y + 1; j++) {
                GridPoint2 checkPos = new GridPoint2(i, j);
                ATile tile = safeGetTile(checkPos);
                if (tile instanceof IDestructable) {
                    if (tile instanceof ISelfUpdate) {
                        updateManager.removedFromUpdates((ISelfUpdate) tile);
                    }
                    tile.destroy();
                }
            }
        }
        updateManager.removedFromUpdates(this);
        active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void update(float delta) {
        GridPoint2 playerPos = playerManager.getPosition();

        /// Killing opponent
        ATile currentTile = safeGetTile(pos);
        if (currentTile instanceof RockTile) {
            destruct();
            return;
        }

        /// Killing player
        if (playerPos.equals(pos)) {
            if (playerPos.equals(playerManager.getStartingPosition())) {
                levelStats.decreaseHP();
            }
            playerManager.movePlayerToStart();
        }

        opponentMoveTime += delta;

        if (opponentMoveTime > 0.3f) {
            if (emptyAroundOpponent()) {
                move(EDirections.RIGHT);
            } else if (!blocksAroundOpponent()) {
                moveDirection = determineMovement();
                move(moveDirection);
            }
            opponentMoveTime = 0;
        }
    }

    public boolean emptyAroundOpponent() {
        return checkSurroundingTiles(true);
    }

    public boolean blocksAroundOpponent() {
        return checkSurroundingTiles(false);
    }

    private boolean checkSurroundingTiles(boolean shouldBeEmpty) {
        int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : directions) {
            GridPoint2 checkPos = new GridPoint2(pos.x + dir[0], pos.y + dir[1]);
            ATile tile = safeGetTile(checkPos);
            boolean isEmpty = tile instanceof EmptyTile;
            if (isEmpty != shouldBeEmpty) {
                return false;
            }
        }
        return true;
    }
}
