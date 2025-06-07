package io.github.StoneDigger.model.TileChangers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Directions.EDirections;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model.GameObjects.Tiles.*;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class PlayerChanger extends Player {
    private final WhatChanged whatChanged;

    public PlayerChanger(GridPoint2 start, BoardManager boardManager, ILevelStats levelStats, UpdateManager updateManager, WhatChanged whatChanged) {
        super(start, boardManager, levelStats, updateManager);
        this.whatChanged = whatChanged;
    }

    @Override
    public void update(float delta) {
        ATile currentTile = boardManager.getTile(pos);
        if(currentTile instanceof RockTile) {
            whatChanged.playerDied();

            setOnStartingPosition();
            levelStats.decreaseHP();
        } else if(currentTile instanceof DiamondTile) {
            whatChanged.diamondCollected();

            levelStats.collectDiamond();
            updateManager.removedFromUpdates((ISelfUpdate) currentTile);
            boardManager.setTile(pos, new EmptyTile(pos, boardManager));
        }
    }

    @Override public void move(EDirections dir) {
        ATile t = boardManager.getTile(new GridPoint2(pos.x+dir.getDx(),pos.y+dir.getDy()));
        if(!(t.isWalkable(dir))) return;

        /// moving
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
}
