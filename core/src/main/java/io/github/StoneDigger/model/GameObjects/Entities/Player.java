package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import io.github.StoneDigger.model.Directions.*;

import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Interfaces.IDestructable;
import io.github.StoneDigger.model.Interfaces.IPlayer;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class Player implements IPlayer {
    private GridPoint2 pos;
    private GridPoint2 startingPosition;
    private final BoardManager boardManager;
    private final LevelStats levelStats;
    private final UpdateManager updateManager;
    private final WhatChanged whatChanged;

    public Player(GridPoint2 start, BoardManager boardManager, LevelStats levelStats, UpdateManager updateManager, WhatChanged whatChanged) {
        pos = start;
        startingPosition = new GridPoint2(start);
        this.boardManager = boardManager;
        this.levelStats = levelStats;
        this.updateManager = updateManager;
        this.whatChanged = whatChanged;
    }

    @Override
    public void setOnStartingPosition() {
        pos = new GridPoint2(startingPosition);
    }

    @Override
    public void setStartingPosition(GridPoint2 startingPosition) {
        this.startingPosition = new GridPoint2(startingPosition);
    }

    @Override
    public GridPoint2 getPosition() {
        return pos;
    }

    @Override
    public GridPoint2 getStartingPosition() {
        return pos;
    }

    public void setPosition(GridPoint2 p){
        pos = p;
    }

    @Override
    public void move(EDirections dir) {
        ATile t = boardManager.getTile(new GridPoint2(pos.x+dir.getDx(),pos.y+dir.getDy()));
        if(!(t.isWalkable(dir))) return;

        /// Moving
        pos.add(dir.getDx(), dir.getDy());

        /// Actions
        if(t instanceof DirtTile) {
            ((DirtTile) t).onWalkBy(this,dir);
            whatChanged.playerMovedOnDirt();
        } else if (t instanceof EndTile) {
            ((EndTile) t).onWalkBy(this,dir);
            whatChanged.endedLevel();
        } else if (t instanceof RockTile) {
            ((RockTile) t).onWalkBy(this,dir);
        } else if (t instanceof DiamondTile) {
            ((DiamondTile) t).onWalkBy(this,dir);
        }
    }

    @Override
    public void update(float delta) {
        ATile currentTile = boardManager.getTile(pos);

        if(currentTile instanceof RockTile) {
            whatChanged.playerDied();
            destruct();
            setOnStartingPosition();
            levelStats.decreaseHP();
        } else if(currentTile instanceof DiamondTile) {
            whatChanged.diamondCollected();
            levelStats.collectDiamond();
            updateManager.removedFromUpdates((ISelfUpdate) currentTile);
            boardManager.setTile(pos, new EmptyTile(pos, boardManager));
        }
    }

    public void destruct() {
        for(int i= pos.x-1;i<=pos.x+1;i++) {
            for(int j = pos.y-1;j<= pos.y+1;j++) {
                ATile tile = boardManager.getTile(new GridPoint2(i,j));

                if(tile instanceof IDestructable) {
                    if (tile instanceof ISelfUpdate) {
                        updateManager.removedFromUpdates((ISelfUpdate) tile);
                    }
                    boardManager.setTile(tile.getPosition(), new EmptyTile(tile.getPosition(), boardManager));

                }
            }
        }
    }

    public void moveOnStart() {
        pos = startingPosition;
    }
}
