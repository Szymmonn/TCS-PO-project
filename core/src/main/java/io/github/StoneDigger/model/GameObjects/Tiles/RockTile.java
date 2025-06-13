package io.github.StoneDigger.model.GameObjects.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.Interfaces.IDestructable;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Interfaces.IWalkableTile;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class RockTile extends ATile implements ISelfUpdate, IWalkableTile, IDestructable {
    protected float rockDropTimer = 0f;
    protected int moved = 0;

    protected UpdateManager updateManager;
    protected PlayerManager playerManager;
    protected LevelStats levelStats;
    protected WhatChanged whatChanged;

    public RockTile(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager,
                    PlayerManager playerManager, LevelStats levelStats, WhatChanged whatChanged) {
        this.position = start;
        this.boardManager = boardManager;
        this.updateManager = updateManager;
        this.playerManager = playerManager;
        this.levelStats = levelStats;
        this.whatChanged = whatChanged;
    }

    @Override
    public boolean isWalkable(EDirections dir) {
        if (dir == EDirections.UP || moved > 0) return false;

        GridPoint2 newPos = new GridPoint2(position.x + dir.getDx(), position.y + dir.getDy());
        return boardManager.getTile(newPos) instanceof EmptyTile;
    }

    @Override
    public void onWalkBy(IEntity entity, EDirections dir) {
        if (entity instanceof Player) {
            move(dir);
        }
    }

    @Override
    public void update(float delta) {
        rockDropTimer += delta;

        boolean shouldProcess = (moved > 0 && rockDropTimer >= 0.3f) ||
            (moved == 0 && rockDropTimer >= 0.6f);

        if (shouldProcess) {
            processFallingRocks();
            rockDropTimer = 0f;
        }
    }

    protected void processFallingRocks() {
        int x = position.x;
        int y = position.y;

        if (playerStops(x, y)) {
            rockDropTimer = 0;
            return;
        }

        if (tryFallDown(x, y)) {
            RockTile below = (RockTile) boardManager.getTile(new GridPoint2(x, y - 1));
            below.setMoved(moved + 1);
            moved = 0;
        } else if (moved > 0) {
            whatChanged.rockFell();
            int side = tryRollSideways(x, y);
            if (side != 0) {
                RockTile rolledTo = (RockTile) boardManager.getTile(new GridPoint2(x + side, y));
                rolledTo.setMoved(moved + 1);
            }
            moved = 0;
        }
    }

    protected boolean playerStops(int x, int y) {
        GridPoint2 playerPos = playerManager.getPlayer().getPosition();

        if (playerPos.x == x && playerPos.y + 1 == y && moved < 1) {
            return true;
        }

        boolean belowBlocked = !(boardManager.getTile(new GridPoint2(x, y - 1)) instanceof EmptyTile);

        if (belowBlocked) {
            boolean rightFree = tryFallRight(x, y);
            boolean leftFree = tryFallLeft(x, y);

            if (rightFree && playerPos.x == x + 1 && playerPos.y == y) {
                if (!leftFree) {
                    moved = 0;
                    return true;
                }
            }

            if (leftFree && playerPos.x == x - 1 && playerPos.y == y) {
                if (!rightFree) {
                    moved = 0;
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean tryFallDown(int x, int y) {
        if (y <= 0) return false;

        GridPoint2 to = new GridPoint2(x, y - 1);
        if (boardManager.getTile(to) instanceof EmptyTile) {
            moveWithUpdate(position, to);
            return true;
        }

        return false;
    }

    public boolean tryFallRight(int x, int y) {
        return x < boardManager.getWidth() - 1 &&
            boardManager.getTile(new GridPoint2(x + 1, y)) instanceof EmptyTile &&
            boardManager.getTile(new GridPoint2(x + 1, y - 1)) instanceof EmptyTile;
    }

    public boolean tryFallLeft(int x, int y) {
        return x > 0 &&
            boardManager.getTile(new GridPoint2(x - 1, y)) instanceof EmptyTile &&
            boardManager.getTile(new GridPoint2(x - 1, y - 1)) instanceof EmptyTile;
    }

    protected int tryRollSideways(int x, int y) {
        GridPoint2 playerPos = playerManager.getPlayer().getPosition();
        boolean belowBlocked = !(boardManager.getTile(new GridPoint2(x, y - 1)) instanceof EmptyTile);

        if (belowBlocked) {
            if (tryFallRight(x, y) && !(playerPos.x == x + 1 && playerPos.y == y)) {
                moveWithUpdate(position, new GridPoint2(x + 1, y));
                return 1;
            }

            if (tryFallLeft(x, y) && !(playerPos.x == x - 1 && playerPos.y == y)) {
                moveWithUpdate(position, new GridPoint2(x - 1, y));
                return -1;
            }
        }

        return 0;
    }

    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        RockTile newRock = new RockTile(to, boardManager, updateManager, playerManager, levelStats, whatChanged);

        boardManager.setTile(to, newRock);
        boardManager.setTile(from, new EmptyTile(from, boardManager));

        updateManager.removedFromUpdates(this);
        updateManager.addToUpdates(newRock);
    }

    public void move(EDirections dir) {
        GridPoint2 from = getPosition();
        GridPoint2 to = new GridPoint2(from.x + dir.getDx(), from.y + dir.getDy());
        moveWithUpdate(from, to);
    }

    public void setMoved(int flag) {
        this.moved = flag;
    }
}
