package io.github.StoneDigger.model.TileChangers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Tiles.DiamondTile;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;
import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

public class DiamondTileChanger extends DiamondTile {
    private final WhatChanged whatChanged;
    public DiamondTileChanger(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, WhatChanged whatChanged) {
        super(start, boardManager, updateManager);
        this.whatChanged = whatChanged;
    }

    @Override
    public void update(float delta) {
        dropDiamondTimer += delta;
        if ((moved>0 && dropDiamondTimer >= 0.3f) || (moved==0 && dropDiamondTimer >=0.6f)) {
            processFallingDiamonds();
            dropDiamondTimer = 0;
        }
    }

    @Override
    protected void processFallingDiamonds() {
        int x = position.x;
        int y = position.y;

        if (super.tryFallDown(x, y)) {
            ((DiamondTile) boardManager.getTile(new GridPoint2(x, y - 1))).setMoved(moved+1);
            moved = 0;

        } else if (moved>0){
            whatChanged.diamondFell();
            int side = super.tryRollSideways(x, y);
            if(side!=0) {
                ((DiamondTile) boardManager.getTile(new GridPoint2(x + side, y))).setMoved(moved+1);
            }
            moved = 0;
        }
    }

    @Override
    public void moveWithUpdate(GridPoint2 from, GridPoint2 to) {
        DiamondTileChanger oldDiamond = (DiamondTileChanger) boardManager.getTile(from);
        DiamondTileChanger newDiamond = new DiamondTileChanger(to, boardManager, updateManager, whatChanged);

        boardManager.setTile(to, newDiamond);
        boardManager.setTile(from, new EmptyTile(from, boardManager));

        updateManager.removedFromUpdates(oldDiamond);
        updateManager.addToUpdates(newDiamond);
    }
}
