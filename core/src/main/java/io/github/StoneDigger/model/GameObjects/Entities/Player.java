package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Interfaces.IDestructable;
import io.github.StoneDigger.model.Interfaces.IEntity;
import io.github.StoneDigger.model.Interfaces.IMovable;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class Player implements IMovable, ISelfUpdate {

    private GridPoint2 pos;
    private GridPoint2 startingPosition;

    private final BoardManager boardManager;
    private final LevelStats levelStats;
    private final UpdateManager updateManager;
    private final WhatChanged whatChanged;

    public Player(GridPoint2 start, BoardManager boardManager, LevelStats levelStats, UpdateManager updateManager, WhatChanged whatChanged) {
        this.pos = start;
        this.startingPosition = new GridPoint2(start);
        this.boardManager = boardManager;
        this.levelStats = levelStats;
        this.updateManager = updateManager;
        this.whatChanged = whatChanged;
    }

    public void setOnStartingPosition() {
        pos = new GridPoint2(startingPosition);
    }

    public void setStartingPosition(GridPoint2 startingPosition) {
        this.startingPosition = new GridPoint2(startingPosition);
    }

    public GridPoint2 getPosition() {
        return pos;
    }

    public GridPoint2 getStartingPosition() {
        return startingPosition;
    }

    @Override
    public void move(EDirections dir) {
        GridPoint2 targetPos = new GridPoint2(pos.x + dir.getDx(), pos.y + dir.getDy());
        ATile targetTile = boardManager.getTile(targetPos);

        if (!targetTile.isWalkable(dir)) return;

        /// Move player
        pos.add(dir.getDx(), dir.getDy());

        /// Trigger tile-specific effects
        if (targetTile instanceof DirtTile) {
            DirtTile dirtTile = (DirtTile) targetTile;
            dirtTile.onWalkBy(this, dir);
            whatChanged.playerMovedOnDirt();
        } else if (targetTile instanceof EndTile) {
            EndTile endTile = (EndTile) targetTile;
            endTile.onWalkBy(this, dir);
            whatChanged.endedLevel();
        } else if (targetTile instanceof RockTile) {
            RockTile rockTile = (RockTile) targetTile;
            rockTile.onWalkBy(this, dir);
        } else if (targetTile instanceof DiamondTile) {
            DiamondTile diamondTile = (DiamondTile) targetTile;
            diamondTile.onWalkBy(this, dir);
        }
    }

    @Override
    public void update(float delta) {
        ATile currentTile = boardManager.getTile(pos);

        if (currentTile instanceof RockTile) {
            whatChanged.playerDied();
            destruct();
            setOnStartingPosition();
            levelStats.decreaseHP();
        } else if (currentTile instanceof DiamondTile) {
            whatChanged.diamondCollected();
            levelStats.collectDiamond();

            updateManager.removeFromUpdates((ISelfUpdate) currentTile);
            boardManager.setTile(pos, new EmptyTile(pos, boardManager));
        }
    }

    public void destruct() {
        for (int i = pos.x - 1; i <= pos.x + 1; i++) {
            for (int j = pos.y - 1; j <= pos.y + 1; j++) {
                GridPoint2 target = new GridPoint2(i, j);
                ATile tile = boardManager.getTile(target);

                if (tile instanceof IDestructable) {
                    if (tile instanceof ISelfUpdate) {
                        updateManager.removeFromUpdates((ISelfUpdate) tile);
                    }
                    boardManager.setTile(tile.getPosition(), new EmptyTile(tile.getPosition(), boardManager));
                }
            }
        }
    }

    public void moveOnStart() {
        pos = new GridPoint2(startingPosition);
    }
}
