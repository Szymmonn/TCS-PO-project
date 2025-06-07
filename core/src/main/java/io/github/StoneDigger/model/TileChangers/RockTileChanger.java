package io.github.StoneDigger.model.TileChangers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.GameObjects.Tiles.RockTile;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class RockTileChanger extends RockTile {
    private final WhatChanged whatChanged;

    public RockTileChanger(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, PlayerManager playerManager, WhatChanged whatChanged) {
        super(start, boardManager, updateManager, playerManager);
        this.whatChanged = whatChanged;
    }

    @Override
    public void update(float delta) {
        rockDropTimer += delta;
        if ((moved>0 && rockDropTimer >= 0.3f) || (moved==0 && rockDropTimer>=0.6f)) {
            processFallingRocks();
            rockDropTimer = 0;
        }
    }

    @Override
    protected void processFallingRocks() {
        int x = position.x;
        int y = position.y;

        if(playerStops(x,y)) {
            rockDropTimer = 0;
            return;
        }

        if (tryFallDown(x, y)) {
            ((RockTile) boardManager.getTile(new GridPoint2(x, y - 1))).setMoved(moved+1);
            moved = 0;

        } else if (moved>0){
            whatChanged.rockFell();
            int side = tryRollSideways(x, y);
            if(side!=0) {
                ((RockTile) boardManager.getTile(new GridPoint2(x + side, y))).setMoved(moved+1);
            }
            moved = 0;
        }
    }

    @Override
    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        RockTile oldRock = (RockTile) boardManager.getTile(from);
        RockTileChanger newRock = new RockTileChanger(to, boardManager, updateManager, playerManager, whatChanged);

        boardManager.setTile(to, newRock);
        boardManager.setTile(from, new EmptyTile(from, boardManager));

        updateManager.removedFromUpdates(oldRock);
        updateManager.addToUpdates(newRock);
    }
}
